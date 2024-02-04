import java.io.*;
import java.util.StringTokenizer;

public class Lab5 {

    private static InputReader in;
    private static PrintWriter out;
    private static DoublyLinkedList rooms = new DoublyLinkedList();

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int N = in.nextInt();

        for (int i = 0; i < N; i++) {
            char command = in.nextChar();
            char direction;

            switch (command) {
                case 'A':
                    direction = in.nextChar();
                    char type = in.nextChar();
                    add(type, direction);
                    break;
                case 'D':
                    direction = in.nextChar();
                    out.println(delete(direction));
                    break;
                case 'M':
                    direction = in.nextChar();
                    out.println(move(direction));
                    break;
                case 'J':
                    direction = in.nextChar();
                    out.println(jump(direction));
                    break;
            }
        }

        out.close();
    }

    public static void add(char type, char direction) {
        rooms.add(type, direction);
    }

    public static int delete(char direction) {
        return rooms.delete(direction);
    }

    public static int move(char direction) {
        return rooms.move(direction);
    }

    public static int jump(char direction) {
        return rooms.jump(direction);
    }

    private static class InputReader {

        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public char nextChar() {
            return next().charAt(0);
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

class DoublyLinkedList {

    private int nodeIdCounter = 1;
    ListNode first;
    ListNode current;
    ListNode last;
    int size = 0;

    public void add(Object element, char direction) {
        if (size == 0) {
            first = new ListNode(element, nodeIdCounter++);
            current = first;
            last = first;
            first.next = first;
            first.prev = first;
        } else {
            ListNode newNode = new ListNode(element, nodeIdCounter++);
            if (direction == 'L') {
                newNode.next = current;
                newNode.prev = current.prev;
                current.prev.next = newNode;
                current.prev = newNode;
            } else {
                newNode.prev = current;
                newNode.next = current.next;
                current.next.prev = newNode;
                current.next = newNode;
            }
        }
        size++;
    }

    public int delete(char direction) {
        int result;

        if (size == 0) {
            result = -1;
        } else if (size == 1) {
            result = current.id;
            first = null;
            current = null;
            last = null;
            size--;
        } else {
            if (direction == 'L') {
                result = current.id;
                current.prev.next = current.next;
                current.next.prev = current.prev;
                current = current.next;
            } else {
                result = current.id;
                current.next.prev = current.prev;
                current.prev.next = current.next;
                current = current.prev;
            }
            size--;
        }

        return result;
    }

    public int move(char direction) {
        if (direction == 'L') {
            current = current.prev;
        } else {
            current = current.next;
        }

        return current.id;
    }

    public int jump(char direction) {
        if (current.element.equals('C')) return -1;

        if (direction == 'L') {
            do {
                current = current.prev;
            } while (!current.element.equals('S'));
        } else {
            do {
                current = current.next;
            } while (!current.element.equals('S'));
        }

        return current.id;
    }

    public String traverse() {
        ListNode traverseNode = first;
        StringBuilder result = new StringBuilder();
        do {
            result.append(traverseNode + ((traverseNode.next != first) ? " | " : ""));
            traverseNode = traverseNode.next;
        } while (traverseNode != first);

        return result.toString();
    }
}

class ListNode {

    Object element;
    ListNode next;
    ListNode prev;
    int id;

    ListNode(Object element, int id) {
        this.element = element;
        this.id = id;
    }

    public String toString() {
        return String.format("(ID:%d Elem:%s)", id, element);
    }
}