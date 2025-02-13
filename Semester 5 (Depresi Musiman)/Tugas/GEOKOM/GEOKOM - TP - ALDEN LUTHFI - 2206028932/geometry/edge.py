from __future__ import annotations
from math import inf
from constants import HEIGHT, WIDTH
from geometry.point import Point
from decimal import Decimal as D

class Edge:
    def __init__(self, a: Point, b: Point, finished: bool = False) -> None:
        self.a = a
        self.b = b
        self.finished = finished

    def __eq__(self, other: object) -> bool:
        if not isinstance(other, Edge):
            return False

        return self.a == other.a and self.b == other.b

    def __str__ (self) -> str:
        return f"Edge({self.a}, {self.b})"

    def __repr__(self) -> str:
        return str(self)

    def __hash__(self) -> int:
        return hash(repr(self))

    @property
    def border_edge(self) -> bool:
        return self.a.x == inf or self.a.x == -inf and self.point_edge

    @property
    def point_edge(self) -> bool:
        return self.a == self.b

    @property
    def visible_edge(self) -> bool:
        cond1: bool = -WIDTH < self.a.x < WIDTH and -HEIGHT < self.a.y < HEIGHT
        cond2: bool = -WIDTH < self.b.x < WIDTH and -HEIGHT < self.b.y < HEIGHT

        return cond1 or cond2

    @property
    def slope(self) -> D:
        if self.a == self.b:
            return D("nan")

        if self.a.x == self.b.x:
            return D("inf")

        return (self.a.y - self.b.y) / (self.a.x - self.b.x)

    def f(self, x: D | int | float) -> D:
        return self.slope * (D(x) - self.a.x) + self.a.y

    def extend(self) -> Edge:
        if not self.visible_edge:
            return self

        if self.slope == D("nan"):
            return self

        if self.slope == D("inf"):
            if self.a.y == D("inf"):
                self.a.y = HEIGHT

            self.b.y = -HEIGHT if self.b.y < self.a.y else HEIGHT

        elif self.b.x < self.a.x:
            self.b.x, self.b.y = -WIDTH, self.f(-WIDTH)

        elif self.b.x > self.a.x:
            self.b.x, self.b.y = WIDTH, self.f(WIDTH)

        return self

    def bound(self) -> Edge:
        for point in [self.a, self.b]:
            if point.x == point.y == inf or point.x == point.y == -inf:
                continue

            if point.x == inf:
                point.x = WIDTH
            elif point.x == -inf:
                point.x = -WIDTH

            if point.y == inf:
                point.y = HEIGHT
            elif point.y == -inf:
                point.y = -HEIGHT

        return self