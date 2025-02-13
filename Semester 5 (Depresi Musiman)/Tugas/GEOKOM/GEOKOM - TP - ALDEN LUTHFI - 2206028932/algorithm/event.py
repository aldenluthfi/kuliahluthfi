from __future__ import annotations

from geometry.point import Point
from geometry.arc import Arc
from decimal import Decimal as D

class Event:
    def __init__(self,point: Point) -> None:
        self.point = point

    def __lt__(self, other: Event) -> bool:
        if self.point == other.point:
            return type(self) == CircleEvent and not type(other) == CircleEvent
        return self.point < other.point

    def __eq__(self, other: object) -> bool:
        if not isinstance(other, Event):
            return False
        return self.point == other.point

    def __repr__(self):
        return str(self)

class SiteEvent(Event):
    def __init__(self, y: D, x: D) -> None:
        super().__init__(Point(x, y))

    def __str__(self) -> str:
        return f"SiteEvent{self.point}"

class CircleEvent(Event):
    def __init__(self, y: D, center: Point, arc: Arc, r: D) -> None:
        super().__init__(Point(center.x, y))

        self.center: Point = center
        self.arc: Arc = arc
        self.r = r

    def __str__(self) -> str:
        return f"CircleEvent{self.center}"