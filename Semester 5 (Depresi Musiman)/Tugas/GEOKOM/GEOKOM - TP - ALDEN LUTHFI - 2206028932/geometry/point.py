from __future__ import annotations
from constants import EPSILON
from decimal import Decimal as D
from typing import Iterator

class Point:
    def __init__(self, x: D | int | float, y: D | int | float) -> None:
        self._x: D = D(x)
        self._y: D = D(y)

    def __eq__(self, other: object) -> bool:
        if not isinstance(other, Point):
            return False

        return self.x == other.x and self.y == other.y

    def __lt__(self, other: Point) -> bool:
        return self.y > other.y or (self.y == other.y and self.x < other.x)

    def __add__(self, other: Point) -> Point:
        return Point(self.x + other.x, self.y + other.y)

    def __sub__(self, other: Point) -> Point:
        return Point(self.x - other.x, self.y - other.y)

    def __abs__ (self) -> D:
        return D.sqrt(self.x ** 2 + self.y ** 2)

    def __iter__(self) -> Iterator[D]:
        return iter([self.x, self.y])

    def __next__(self) -> D:
        return next(self)

    def __str__(self) -> str:
        return f"({self.x:.3f}, {self.y:.3f})"

    def __repr__(self) -> str:
        return str(self)

    def __hash__(self) -> int:
        return hash((self.x, self.y))

    @property
    def x(self) -> D:
        return self._x

    @property
    def y(self) -> D:
        return self._y

    @x.setter
    def x(self, value: int | float | D) -> None:
        self._x = D(value)

    @y.setter
    def y(self, value: int | float | D) -> None:
        self._y = D(value)

    # O'Rourke 2ed p. 189
    @staticmethod
    def empty_circle(i: Point, j: Point, k: Point) -> tuple[Point | None, D]:

        x1, y1 = i
        x2, y2 = j
        x3, y3 = k

        if (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1) > 0:
            return None, D("inf")

        a = x2 - x1
        b = y2 - y1
        c = x3 - x1
        d = y3 - y1
        e = (x2 - x1) * (x1 + x2) + (y2 - y1) * (y1 + y2)
        f = (x3 - x1) * (x1 + x3) + (y3 - y1) * (y1 + y3)
        g = 2 * ((x2 - x1) * (y3 - y2) - (x3 - x2) * (y2 - y1))

        if abs(g) < EPSILON:
            return None, D("inf")

        x = (d * e - b * f) / g
        y = (a * f - c * e) / g

        o: Point = Point(x, y)
        r: D = abs(o - i)

        if r > (1 / EPSILON):
            return None, D("inf")

        return o, r