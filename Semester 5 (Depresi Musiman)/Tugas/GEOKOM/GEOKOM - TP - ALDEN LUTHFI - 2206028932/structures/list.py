from __future__ import annotations

from geometry.arc import Arc
from geometry.point import Point

class DoublyLinkedList:

    def __init__(self):
        self.head = None
        self.tail = None
        self.length = 0

    def __len__(self) -> int:
        return self.length

    def __str__(self) -> str:
        current = self.head
        index = 0
        result = ""
        while current:
            result += f"{index}: {str(current)}\n"
            current = current.next
            index += 1
        return result

    def insert(self, arc: Arc, pos: Arc | None) -> Arc:

        self.length += 1

        if pos is None:
            arc.next = self.head
            self.head = arc

            if not self.tail:
                self.tail = arc

            if arc.next:
                arc.next.prev = arc

            return arc

        assert isinstance(arc, Arc)
        assert isinstance(pos, Arc)

        arc.prev = pos
        arc.next = pos.next

        if pos.next:
            pos.next.prev = arc

        pos.next = arc

        if pos == self.tail:
            self.tail = arc

        return arc

    def delete(self, arc: Arc) -> Arc:

        self.length -= 1

        assert isinstance(arc, Arc)

        if arc.prev:
            arc.prev.next = arc.next
        if arc.next:
            arc.next.prev = arc.prev
        if arc == self.head:
            self.head = arc.next
        if arc == self.tail:
            self.tail = arc.prev

        return arc

    def search(self, point: Point) -> Arc | None:

        if self.head is None:
            return None

        current: Arc | None = self.head

        while current is not None and current.e2.b.x < point.x:
            current.update(point.y)
            current = current.next

        return current