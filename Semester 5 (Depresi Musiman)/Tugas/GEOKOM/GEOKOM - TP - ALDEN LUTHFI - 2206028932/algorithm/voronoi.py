from __future__ import annotations

from algorithm.beachline import BeachLine
from constants import HEIGHT, WIDTH
from decimal import Decimal as D
from algorithm.event import Event, SiteEvent, CircleEvent
from geometry.point import Point
from geometry.edge import Edge
from queue import PriorityQueue

class Voronoi:
    def __init__(self, sites: set[Point]) -> None:
        self.sites: set[Point] = sites
        self.beachline: BeachLine = BeachLine()
        self._edges: set[Edge] = set()
        self.delaunay: set[Edge] = set()
        self.events: PriorityQueue[Event] = PriorityQueue()
        self.next_visible: Event | None = None
        self.empty_circle: CircleEvent | None = None

    @property
    def edges(self) -> set[Edge]:
        return {e for e in self._edges if not (e.border_edge or e.point_edge)}

    @edges.setter
    def edges(self, value: set[Edge]) -> None:
        self._edges = value

    def visible(self, event: Event) -> bool:
        cond = True

        if isinstance(event, CircleEvent):
            x, y = event.center
            cond &= event.arc.on_beachline

        else:
            x, y = event.point

        cond &= (-WIDTH // 2) <= x <= (WIDTH // 2)
        cond &= (-HEIGHT // 2) <= y <= (HEIGHT // 2)

        return cond

    def voronoi(self, y: D | None=None) -> set[Edge]:

        for site in self.sites:
            self.events.put(SiteEvent(site.y, site.x))

        while not self.events.empty():
            ed: set[Edge] = set()
            dl: set[Edge] = set()
            ev: list[CircleEvent] = []

            event: Event = self.events.get()

            if y is not None and event.point.y < y:

                while not self.visible(event) and not self.events.empty():
                    event = self.events.get()

                if self.visible(event):
                    self.next_visible = event

                break

            if isinstance(event, SiteEvent):
                ev, dl = self.beachline.site(event)

            if isinstance(event, CircleEvent):

                if event.arc.on_beachline:
                    if not self.empty_circle or event.r > self.empty_circle.r:
                        self.empty_circle = event

                ed, ev, dl = self.beachline.circle(event)

                self.edges |= ed

            self.delaunay |= dl

            for e in ev:
                self.events.put(e)

        if y is None:
            self.edges |= self.beachline.cleanup()

        return self.edges
