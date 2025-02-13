from constants import *
from pygame import *
from algorithm.event import Event, SiteEvent, CircleEvent
from algorithm.voronoi import Voronoi
from decimal import Decimal as D
from geometry.arc import Arc
from geometry.edge import Edge
from geometry.point import Point
from os import path

class GUI:
    def __init__(self) -> None:
        self.screen: Surface = display.set_mode((WIDTH, HEIGHT))
        display.set_caption("Voronoi Diagram")

        self.scale: float = DEFAULT_SCALE
        self.speed: float = DEFAULT_SPEED

        self.running: bool = True

        self.sites: list[Rect] = []
        self.sweep_line: Rect | None = None
        self.sweep_line_y: float = 0
        self.selected_site: Rect | None = None
        self.largest_circle: bool = False
        self.delaunay: bool = False
        self.voronoi: Voronoi | None = None

        self.help: bool = True
        self.tutorial_box: Rect | None = None
        self.paused: bool = True
        self.font: font.Font | None = None
        self.font_bold: font.Font | None = None

    def run(self) -> None:
        init()
        font.init()

        self.font = font.Font(FONT_NAME, FONT_SIZE)
        self.font_bold = font.Font(FONT_BOLD_NAME, FONT_SIZE)
        self.input_file()

        while self.running:
            frame = Surface((WIDTH, HEIGHT))

            for ev in event.get():
                self.handle_event(frame, ev)

            if not self.paused:
                self.move_line()

            self.draw_diagram(frame)

            self.screen.blit(frame, (0, 0))
            display.flip()

        quit()


    def handle_event(self, frame: Surface, ev: event.Event) -> None:
        import pygame

        type = self.getattr(ev, 'type')

        modifier = self.getattr(ev, 'key')
        modifier = modifier or self.getattr(ev, 'text')
        modifier = modifier or self.getattr(ev, 'button')
        modifier = modifier or self.getattr(ev, 'buttons')

        match (type, modifier):

            case (pygame.MOUSEBUTTONDOWN, pygame.BUTTON_RIGHT):
                self.delete_site(ev.pos)

            case (pygame.MOUSEBUTTONDOWN, pygame.BUTTON_LEFT):
                self.add_select_site(frame, ev.pos)

            case (pygame.MOUSEMOTION, (1, 0, 0)):
                self.move_site(ev.pos)

            case (pygame.MOUSEBUTTONUP, _):
                self.selected_site = None

            case (pygame.KEYDOWN, pygame.K_r):
                self.hard_reset()

            case (pygame.KEYDOWN, pygame.K_e):
                self.soft_reset()

            case (pygame.KEYDOWN, pygame.K_h):
                self.help = not self.help

            case (pygame.KEYDOWN, pygame.K_k):
                self.start_pause_line()

            case (pygame.KEYDOWN, pygame.K_n):
                self.delaunay = not self.delaunay

            case (pygame.KEYDOWN, pygame.K_m):
                self.largest_circle = not self.largest_circle

            case (pygame.TEXTINPUT, 'i'):
                self.scale_diagram(2)

            case (pygame.TEXTINPUT, 'o'):
                self.scale_diagram(0.5)

            case (pygame.TEXTINPUT, 'l'):
                self.speed = min(self.speed * 2, MAX_SPEED)

            case (pygame.TEXTINPUT, 'j'):
                self.speed = max(self.speed / 2, MIN_SPEED)

            case (pygame.TEXTINPUT, 'w'):
                self.y_offset(1)

            case (pygame.TEXTINPUT, 'a'):
                self.x_offset(1)

            case (pygame.TEXTINPUT, 's'):
                self.y_offset(-1)

            case (pygame.TEXTINPUT, 'd'):
                self.x_offset(-1)

            case (pygame.QUIT, _) | (pygame.KEYDOWN, pygame.K_q):
                self.running = False

            case _:
                pass

    def getattr(self, obj: object, name: str) -> object:
        try:
            return obj.__getattribute__(name)
        except AttributeError:
            return None

    def to_float(self, p: Point) -> tuple[float, float]:
        return float(p.x), float(p.y)

    def input_file(self) -> None:
        if not path.exists('points.txt'):
            return

        with open('points.txt', 'r') as file:
            points = file.readlines()

            for point in points:
                x, y = eval(point)
                self.sites.append(
                    draw.circle(
                        Surface((WIDTH, HEIGHT)),
                        SITE_COLOR,
                        self.uncenter_coords(x, y),
                        SITE_RADIUS
                    )
                )

    def collides(self, a: tuple[int, int], b: tuple[int, int]) -> bool:
        point_a = Point(*a)
        point_b = Point(*b)

        return abs(point_a - point_b) < (1 / self.scale)

    def y_offset(self, y: int) -> None:
        self.voronoi = None

        sign = 1 if y > 0 else -1
        y = sign * int(max(1, abs(y * (1 / self.scale))))

        for site in self.sites:
            site.center = (site.center[0], site.center[1] + y)

    def x_offset(self, x: int) -> None:
        self.voronoi = None

        sign = 1 if x > 0 else -1
        x = sign * int(max(1, abs(x * (1 / self.scale))))

        for site in self.sites:
            site.center = (site.center[0] + x, site.center[1])

    def draw_help(self, frame: Surface, texts: list[Surface]) -> Rect:

        padding: int = 20
        font_height: int = texts[0].get_height()

        max_width: int = max(text.get_width() for text in texts)
        box_width: int = max_width + padding * 2
        box_height: int = len(texts) * (font_height + 5) + padding * 2

        box_rect = Rect(0, 0, box_width, box_height)
        box_rect.center = frame.get_rect().center

        box: Rect = draw.rect(frame, GRAY, box_rect, 0, SITE_RADIUS)
        draw.rect(frame, BLACK, box_rect, LINE_THICKNESS, SITE_RADIUS)

        text_y = box_rect.top + padding

        for text in texts:
            text_x = box_rect.left + padding

            frame.blit(text, (text_x, text_y))

            text_y += font_height + 5

        return box

    def render_texts(self, tutorial: list[tuple[str, int]]) -> list[Surface]:

        text_surfaces: list[Surface] = []

        for line, font in tutorial:
            assert self.font is not None and self.font_bold is not None
            assert font in (FONT_REGULAR, FONT_BOLD)

            if font == FONT_BOLD:
                text_surface = self.font_bold.render(line, True, BLACK)
            else:
                text_surface = self.font.render(line, True, BLACK)

            text_surfaces.append(text_surface)

        return text_surfaces

    def hard_reset(self) -> None:
        self.sites.clear()
        self.sweep_line = None
        self.sweep_line_y = 0
        self.selected_site = None
        self.voronoi = None
        self.paused = True

        self.largest_circle = False
        self.delaunay = False

        self.speed = DEFAULT_SPEED
        self.scale = DEFAULT_SCALE

    def soft_reset(self) -> None:
        self.paused = True
        self.sweep_line = None
        self.sweep_line_y = 0
        self.voronoi = None

    def delete_site(self, pos: tuple[int, int]) -> None:
        if self.help:
            return

        self.voronoi = None

        for site in self.sites:
            if self.collides(site.center, self.unscale_point(*pos)):
                self.sites.remove(site)
                break

    def add_select_site(self, frame: Surface, pos: tuple[float, float]) -> None:
        if self.help:

            assert self.tutorial_box is not None

            if not self.tutorial_box.collidepoint(pos):
                self.help = False
                self.tutorial_box = None

            return

        self.voronoi = None

        for site in self.sites:
            if self.collides(site.center, self.unscale_point(*pos)):
                self.selected_site = site
                break
        else:
            self.sites.append(self.unscale_site(frame, *pos))

    def move_site(self, pos: tuple[int, int]) -> None:
        if self.selected_site is None or self.help:
            return

        self.voronoi = None
        self.selected_site.center = self.unscale_point(*pos)

    def move_line(self) -> None:
        if self.sweep_line is None:
            return

        self.sweep_line_y += (self.speed * self.scale / MIN_SCALE)
        self.sweep_line.move_ip(0, self.sweep_line_y - self.sweep_line.centery)

        cond: bool = bool(self.voronoi) and bool(self.voronoi.next_visible)

        if self.sweep_line.top > HEIGHT and not cond:
            self.sweep_line = None
            self.sweep_line_y = 0
            self.paused = True

        self.voronoi = None

    def center_coords(self, x: float, y: float) -> tuple[float, float]:
        return x - WIDTH / 2, HEIGHT / 2 - y

    def uncenter_coords(self, x: float, y: float) -> tuple[float, float]:
        return x + WIDTH / 2, HEIGHT / 2 - y

    def scale_coords(self, x: float, y: float) -> tuple[float, float]:
        return x * self.scale, y * self.scale

    def unscale_coords(self, x: float, y: float) -> tuple[float, float]:
        return x / self.scale, y / self.scale

    def scale_point(self, x: float, y: float) -> tuple[int, int]:
        x, y = self.center_coords(x, y)

        x *= 1 / MIN_SCALE
        y *= 1 / MIN_SCALE

        x, y = self.uncenter_coords(*self.scale_coords(x, y))

        return round(x), round(y)

    def unscale_point(self, x: float, y: float) -> tuple[int, int]:
        x, y = self.center_coords(x, y)

        x *= MIN_SCALE
        y *= MIN_SCALE

        x, y = self.uncenter_coords(*self.unscale_coords(x, y))

        return round(x), round(y)

    def scale_site(self, frame: Surface, x: float, y: float) -> Rect:
        x, y = self.center_coords(x, y)

        x *= 1 / MIN_SCALE
        y *= 1 / MIN_SCALE

        x, y = self.uncenter_coords(*self.scale_coords(x, y))

        x = round(x)
        y = round(y)

        return draw.circle(frame, SITE_COLOR, (x, y), SITE_RADIUS)

    def unscale_site(self, frame: Surface, x: float, y: float) -> Rect:
        x, y = self.center_coords(x, y)

        x *= MIN_SCALE
        y *= MIN_SCALE

        x, y = self.uncenter_coords(*self.unscale_coords(x, y))

        x = round(x)
        y = round(y)

        return draw.circle(frame, SITE_COLOR, (x, y), SITE_RADIUS)

    def scale_edge(
            self,
            frame: Surface,
            edge: Edge,
            color: tuple[int, int, int]
        ) -> Rect:

        x1, y1 = self.to_float(edge.a)
        x2, y2 = self.to_float(edge.b)

        x1 *= 1 / MIN_SCALE
        y1 *= 1 / MIN_SCALE
        x2 *= 1 / MIN_SCALE
        y2 *= 1 / MIN_SCALE

        x1, y1 = self.uncenter_coords(*self.scale_coords(x1, y1))
        x2, y2 = self.uncenter_coords(*self.scale_coords(x2, y2))

        return draw.line(frame, color, (x1, y1), (x2, y2), LINE_THICKNESS)

    def scale_event(self, frame: Surface, event: Event) -> None:
        assert isinstance(event, SiteEvent) or isinstance(event, CircleEvent)

        if isinstance(event, SiteEvent):
            x, y = self.to_float(event.point)
        else:
            x, y = self.to_float(event.center)

        x *= 1 / MIN_SCALE
        y *= 1 / MIN_SCALE

        x, y = self.uncenter_coords(*self.scale_coords(x, y))

        if isinstance(event, CircleEvent):
            r = float(event.r * D(self.scale / MIN_SCALE))

            draw.circle(frame, CIRCLE_COLOR, (x, y), r, LINE_THICKNESS)

        draw.circle(frame, EVENT_COLOR, (x, y), SITE_RADIUS)

    def scale_diagram(self, factor: float) -> None:
        self.voronoi = None
        self.scale = min(max(self.scale * factor, MIN_SCALE), MAX_SCALE)

    def start_pause_line(self) -> None:
        self.paused = not self.paused

        if self.sweep_line is None:
            self.sweep_line = Rect(0, 0, WIDTH, LINE_THICKNESS)

    def sweep_line_pos(self) -> D | None:
        if self.sweep_line is None:
            return None

        x, y = self.sweep_line.center
        _, y = self.center_coords(x, y)

        return D(y * MIN_SCALE / self.scale)

    def draw_grid(self, frame: Surface) -> None:

        grid_size: int = int(self.scale / MIN_SCALE)

        for x in range(grid_size, WIDTH, grid_size):
            draw.line(frame, GRAY, (x, 0), (x, HEIGHT), GRID_THICKESS)
            draw.line(frame, GRAY, (0, x), (WIDTH, x), GRID_THICKESS)

    def draw_diagram(self, frame: Surface) -> Surface:
        frame.fill(WHITE)

        if WIDTH * MIN_SCALE / self.scale <= MIN_GRIDS:
            self.draw_grid(frame)

        self.draw_voronoi(frame)

        if self.sweep_line is not None:
            draw.rect(frame, RED, self.sweep_line)

        if self.help:
            self.tutorial_box = self.draw_help(
                frame,
                self.render_texts(TUTORIAL)
            )

        return frame

    def draw_delaunay(self, frame: Surface) -> None:
        if self.voronoi is None:
            return

        for edge in self.voronoi.delaunay:
            self.scale_edge(frame, edge, GREEN)

    def draw_voronoi(self, frame: Surface) -> None:
        if self.voronoi is None:
            self.voronoi = Voronoi(
                {
                    Point(*self.center_coords(*site.center))
                    for site in self.sites
                }
            )
            self.voronoi.voronoi(self.sweep_line_pos())

        self.draw_edges(frame)
        self.draw_arcs(frame)

        if self.delaunay:
            self.draw_delaunay(frame)

        if self.largest_circle:
            self.draw_largest_circle(frame)

        if isinstance(self.voronoi.next_visible, CircleEvent):
            self.draw_event(frame)

        for site in self.sites:
            self.scale_site(frame, *site.center)

        if isinstance(self.voronoi.next_visible, SiteEvent):
            self.draw_event(frame)


    def draw_edges(self, frame: Surface) -> None:
        if self.voronoi is None:
            return

        for edge in self.voronoi.edges:
            self.scale_edge(frame, edge, EDGE_COLOR)

    def draw_arcs(self, frame: Surface) -> None:

            if self.voronoi is None or self.sweep_line is None:
                return

            d: D | None = self.sweep_line_pos()

            assert d is not None

            arc = self.voronoi.beachline.list.head

            while arc is not None:

                arc.update(d)

                self.scale_edge(frame, arc.e1.bound(), EDGE_COLOR)
                self.scale_edge(frame, arc.e2.bound(), EDGE_COLOR)

                self.draw_arc(frame, arc, d)

                arc = arc.next

    def draw_arc(self, frame: Surface, arc: Arc, d: D) -> None:

        start: D = D(max(arc.e1.b.x, -WIDTH * MIN_SCALE / self.scale))
        end: D = D(min(arc.e2.b.x, WIDTH * MIN_SCALE / self.scale))
        increment: D = D(1 / self.scale)

        while start < end:

            a = start
            b = min(start + increment, end)

            if arc.focus.y != d:
                first: Point = Point(a, arc.f(a, d))
                second: Point = Point(b, arc.f(b, d))

                self.scale_edge(frame, Edge(first, second), EDGE_COLOR)

            start += increment

    def draw_event(self, frame: Surface) -> None:

            if self.voronoi is None:
                return

            event: Event | None = self.voronoi.next_visible

            if event is not None:
                self.scale_event(frame, event)

    def draw_largest_circle(self, frame: Surface) -> None:
        if self.voronoi is None:
            return

        event: CircleEvent | None = self.voronoi.empty_circle

        if event is not None:
            self.scale_event(frame, event)

if __name__ == '__main__':
    gui: GUI = GUI()
    while gui.running:
        try:
            gui.run()
        except Exception as e:
            print(e.with_traceback(None))
            gui.hard_reset()