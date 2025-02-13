from __future__ import annotations

from constants import HEIGHT
from decimal import Decimal as D
from algorithm.event import SiteEvent, CircleEvent
from geometry.edge import Edge
from geometry.arc import Arc
from geometry.point import Point
from math import inf
from structures.tree import AVLTree
from structures.list import DoublyLinkedList

class BeachLine:
    def __init__(self) -> None:
        self.tree: AVLTree = AVLTree()
        self.list: DoublyLinkedList = DoublyLinkedList()

    def delete_arc(self, arc: Arc, y: D) -> Arc:
        self.list.delete(arc)
        self.tree.delete(arc, y)
        arc.on_beachline = False

        return arc

    def cleanup(self) -> set[Edge]:
        edges: set[Edge] = set()

        arc: Arc | None = self.list.head

        while arc:
            arc.update(D(-8*HEIGHT))

            if not arc.e1.border_edge:
                edges |= {arc.e1.extend()}
            if not arc.e2.border_edge:
                edges |= {arc.e2.extend()}

            arc = arc.next

        return edges

    def site(self, event: SiteEvent) -> tuple[list[CircleEvent], set[Edge]]:
        events: list[CircleEvent] = []

        _, y = event.point

        if self.tree.root is None and len(self.list) == 0:
            e1 = Edge(Point(-inf, inf), Point(-inf, inf))
            e2 = Edge(Point(inf, inf), Point(inf, inf))
            arc: Arc = Arc(event.point, e1, e2)

            self.list.insert(arc, self.list.head)
            self.tree.insert(arc)

            return events, set()

        a0: Arc | None = self.tree.search(Arc.dummy(event.point))

        if a0 is None:
            a0 = self.list.search(event.point)

        assert a0 is not None

        if a0.focus.y == y:
            a1: Arc = Arc.dummy(event.point)
            intersection: Point | None = Arc.intersect(a0, a1, y)

            assert intersection is not None

            a1.e1 = Edge(intersection, intersection)
            a1.e2 = a0.e2
            a0.e2 = a1.e1

            a1.e2.finished = True
            a1.e1.finished = True

            self.list.insert(a1, a0)
            self.tree.insert(a1)

            a1.e2.finished = False
            a1.e1.finished = False

            return events, {Edge(a0.focus, a1.focus)}

        a1: Arc = Arc.dummy(event.point)
        a2: Arc = Arc.dummy(a0.focus)

        intersection: Point | None = Arc.intersect(a0, a1, y)

        assert intersection is not None

        a2.e2 = a0.e2
        a0.e2 = Edge(intersection, intersection,)
        a1.e2 = Edge(intersection, intersection)
        a1.e1 = a0.e2
        a2.e1 = a1.e2

        self.list.insert(a1, a0)
        self.list.insert(a2, a1)

        a1.right = a2
        a1.height = 2

        a1.e1.finished = True
        a1.e2.finished = True
        a2.e1.finished = True
        a2.e2.finished = True

        self.tree.insert(a1)

        a1.e1.finished = False
        a1.e2.finished = False
        a2.e1.finished = False
        a2.e2.finished = False

        if ev := self.update_arc_event(a0, event.point):
            events.append(ev)
        if ev := self.update_arc_event(a2, event.point):
            events.append(ev)

        return events, {Edge(a0.focus, a1.focus)}

    def circle(self, event: CircleEvent) -> tuple[set[Edge], list[CircleEvent], set[Edge]]:
        edges: set[Edge] = set()
        events: list[CircleEvent] = []

        if not event.arc.on_beachline:
            return edges, events, set()

        assert event.arc.prev is not None
        assert event.arc.next is not None

        arc = event.arc

        a0: Arc = event.arc.prev
        a1: Arc = event.arc.next

        arc.e1.b = event.center
        arc.e2.b = event.center

        arc.e1.finished = True
        arc.e2.finished = True

        edges |= {arc.e1.bound(), arc.e2.bound()}

        self.delete_arc(arc, event.point.y)

        new_edge: Edge = Edge(event.center, event.center)

        a0.e2 = new_edge
        a1.e1 = new_edge

        if ev := self.update_arc_event(a0, event.point):
            events.append(ev)
        if ev := self.update_arc_event(a1, event.point):
            events.append(ev)

        return edges, events, {Edge(a0.focus, a1.focus)}

    def update_arc_event(self, arc: Arc, point: Point) -> CircleEvent | None:

        if arc.prev is None or arc.next is None:
            return

        o, r = Point.empty_circle(arc.prev.focus, arc.focus, arc.next.focus)

        if o is not None and o.y - r <= point.y:
            return CircleEvent(o.y - r, o, arc, r)



