from __future__ import annotations
from os import path

DIRNAME = path.dirname(__file__)
FONT_NAME = path.join(DIRNAME, "fonts", "FiraCode-Regular.ttf")
FONT_BOLD_NAME: str = path.join(DIRNAME, "fonts", "FiraCode-Bold.ttf")

WHITE: tuple[int, int, int] = (255, 255, 255)
BLACK: tuple[int, int, int] = (0, 0, 0)
RED: tuple[int, int, int] = (255, 0, 0)
BLUE: tuple[int, int, int] = (0, 0, 255)
GREEN: tuple[int, int, int] = (0, 255, 0)
GRAY: tuple[int, int, int, int] = (240, 240, 240, 50)

GRID_THICKESS: int = 1
MIN_GRIDS: int = 64

WIDTH: int = 1024
HEIGHT: int = 1024

MIN_X: int = -WIDTH // 2
MAX_X: int = WIDTH // 2
MIN_Y: int = -HEIGHT // 2
MAX_Y: int = HEIGHT // 2

EPSILON: float = 1e-6

EVENT_COLOR: tuple[int, int, int] = BLUE
CIRCLE_COLOR: tuple[int, int, int] = RED

DEFAULT_SCALE: float = 1
MIN_SCALE: float = 1/8
MAX_SCALE: float = 8

DEFAULT_SPEED: float = 1
MIN_SPEED: float = 1/128
MAX_SPEED: float = 8

SITE_RADIUS: int = 7
SITE_COLOR: tuple[int, int, int] = BLACK
EDGE_COLOR: tuple[int, int, int] = BLACK
LINE_THICKNESS: int = 2
FONT_SIZE: int = 20
FONT_REGULAR: int = 0
FONT_BOLD: int = 1
TUTORIAL: list[tuple[str, int]] = [
    ("CONTROLS", FONT_BOLD),
    ("Left Click  : Add/Select Site", FONT_REGULAR),
    ("Right Click : Remove Site", FONT_REGULAR),
    ("Drag Mouse  : Move Site", FONT_REGULAR),
    ("W, A, S, D  : Move Diagram", FONT_REGULAR),
    ("I           : Zoom In", FONT_REGULAR),
    ("O           : Zoom Out", FONT_REGULAR),
    ("K           : Start/Pause Sweep Line", FONT_REGULAR),
    ("J           : Decrease Sweep Line Speed", FONT_REGULAR),
    ("L           : Increase Sweep Line Speed", FONT_REGULAR),
    ("M           : Draw Largest Empty Circle", FONT_REGULAR),
    ("N           : Draw Delaunay Triangulation", FONT_REGULAR),
    ("E           : Redraw Diagram", FONT_REGULAR),
    ("R           : Clear Diagram", FONT_REGULAR),
    ("H           : Help", FONT_REGULAR),
    ("Q           : Quit", FONT_REGULAR),
    ("", FONT_REGULAR),
    ("By default, the program will look for points.txt", FONT_BOLD),
    ("In the file, define points as tuples, line-separated", FONT_BOLD),
    (f"Points are from ({MIN_X}, {MIN_Y}) to ({MAX_X}, {MAX_Y}).", FONT_BOLD)
]
