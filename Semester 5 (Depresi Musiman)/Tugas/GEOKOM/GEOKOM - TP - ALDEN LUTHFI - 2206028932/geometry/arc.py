from __future__ import annotations
from decimal import Decimal as D
from geometry.edge import Edge
from geometry.point import Point

class Arc:
    def __init__(self, focus: Point, left: Edge, right: Edge) -> None:

        self.focus: Point = focus
        self.e1: Edge = left
        self.e2: Edge = right
        self.on_beachline = True

        self.right: Arc | None = None
        self.left: Arc | None = None
        self.height: int = 1

        self.next: Arc | None = None
        self.prev: Arc | None = None

    def __eq__(self, other: object) -> bool:
        if not isinstance(other, Arc):
            return False

        a = self.focus == other.focus
        b = self.e1 == other.e1
        c = self.e2 == other.e2

        return a and b and c

    def __lt__(self, other: Arc | None) -> bool:
        assert other is not None

        cond: bool = self.e2.b.x == other.e1.b.x and self.e1 != other.e2
        return self != other and (self.e2.b.x < other.e1.b.x or cond)

    def __gt__(self, other: Arc | None) -> bool:
        assert other is not None

        if self.e1 == self.e2:
            return self.e1.b.x > other.e2.b.x

        cond: bool = self.e1.b.x == other.e2.b.x and self.e2 != other.e1
        return self != other and (self.e1.b.x > other.e2.b.x or cond)

    def __str__(self) -> str:
        return f"Arc({self.focus}, {self.e1.b.x:.2f}, {self.e2.b.x:.2f})"

    def __repr__(self) -> str:
        return str(self)

    def update(self, y: D) -> None:
        intersection_left: Point | None = Arc.intersect(self.prev, self, y)
        intersection_right: Point | None = Arc.intersect(self, self.next, y)

        if intersection_left is not None and not self.e1.finished:
            self.e1.b = intersection_left

        if intersection_right is not None and not self.e2.finished:
            self.e2.b = intersection_right

    def f(self, x: D, d: D) -> D:
        a, b = self.focus
        return ((x - a)**2 / (2 * (b - d))) + (b + d) / 2

    # https://www.wolframalpha.com/input?i2d=true&i=Divide%5BPower%5B%5C%2840%29x-a%5C%2841%29%2C2%5D%2C2%5C%2840%29b-d%5C%2841%29%5D%2BDivide%5Bb%2Bd%2C2%5D%3DDivide%5BPower%5B%5C%2840%29x-p%5C%2841%29%2C2%5D%2C2%5C%2840%29q-d%5C%2841%29%5D%2BDivide%5Bq%2Bd%2C2%5D
    @staticmethod
    def intersect(u: Arc | None, v: Arc | None, d: D) -> Point | None:

        if u is None or v is None:
            return None

        a, b = u.focus
        p, q = v.focus

        if b < d <= q or q < d <= b:
            return None

        if b == q:
            x = (a + p) / 2
            return Point(x, D("inf") if b == d else u.f(x, d))

        if b == d:
            x = u.focus.x
            y = v.f(x, d)
            return Point(x, y)

        if q == d:
            x = v.focus.x
            y = u.f(x, d)
            return Point(x, y)

        A = (1 / (2 * (b - d))) + (1 / (2 * (d-q)))
        B = -a/(b-d) - p/(d-q)
        C = (a**2 / (2 * (b - d))) + ((b - q) / 2) + (p**2 / (2 * (d - q)))

        x = (-B + D.sqrt(B**2 - 4 * A * C)) / (2 * A)
        y = u.f(x, d)

        return Point(x, y)

    @staticmethod
    def dummy(focus: Point) -> Arc:
        x, _ = focus
        left = Edge(Point(x, 0), Point(x, 0))
        right = Edge(Point(x, 0), Point(x, 0))
        return Arc(focus, left, right)
