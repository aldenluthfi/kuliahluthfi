import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Lab6 {
    private static InputReader in;
    private static PrintWriter out;
    public static boolean isFound = false;
    static AVLTree tree = new AVLTree();

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            tree.root = tree.insert(tree.root, in.nextInt());
        }

        int Q = in.nextInt();
        for (int i = 0; i < Q; i++) {
            char query = in.nextChar();

            if (query == 'G') {
                grow();
            }

            else if (query == 'P') {
                out.println(pick());
            }

            else if (query == 'F') {
                out.println(fall());
            }

            else {
                out.println(height());
            }
        }

        out.close();
    }

    static void grow() {
        tree.root = tree.insert(tree.root, in.nextInt());
    }

    static int pick() {
        int selected = in.nextInt();

        isFound = false;
        Node temp = tree.delete(tree.root, selected);
        if (!isFound) return -1;

        tree.root = temp;
        return selected;
    }

    static int fall() {
        if (tree.root == null) return -1;

        Node max = tree.max(tree.root);

        tree.root = tree.delete(tree.root, max.key);

        return max.key;
    }

    static int height() {
        return tree.root == null ? 0 : tree.root.height;
    }

    // taken from https://www.programiz.com/dsa/avl-tree
    // a method to print the contents of a Tree data structure in a readable
    // format. it is encouraged to use this method for debugging purposes.
    // to use, simply copy and paste this line of code:
    // printTree(tree.root, "", true);
    static void printTree(Node currPtr, String indent, boolean last) {
        if (currPtr != null) {
            out.print(indent);
            if (last) {
                out.print("R----");
                indent += "   ";
            } else {
                out.print("L----");
                indent += "|  ";
            }
            out.println(currPtr.key);
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the
    // usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit
    // Exceeded caused by slow input-output (IO)
    static class InputReader {
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

class Node {
    int key, height;
    Node left, right;

    Node(int key) {
        this.key = key;
        height = 1;
    }
}

class AVLTree {
    Node root;

    Node insert(Node node, int key) {
        if (node == null) {
            root = new Node(key);
            node = root;
        }

        else if (key < node.key) {
            node.left = insert(node.left, key);
        }

        else {
            node.right = insert(node.right, key);
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        node = balancing(node);

        return node;
    }

    Node delete(Node node, int key) {
        if (node == null) {
            Lab6.isFound = false;
            return null;
        }

        if (node.key < key) {
            node.right = delete(node.right, key);
        }

        else if (node.key > key) {
            node.left = delete(node.left, key);
        }

        else {
            Lab6.isFound = true;
            if (node.left == null || node.right == null) {
                if (node.left == null) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            } else {
                Node temp = max(node.left);
                node.key = temp.key;
                node.left = delete(node.left, temp.key);
            }
        }

        if (node == null) return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        node = balancing(node);

        return node;
    }

    Node max(Node node) {
        return node.right == null ? node : max(node.right);
    }

    Node balancing(Node node) {
        int balance = balance(node);
        if (balance > 1) {
            if (height(node.left.left) >= height(node.left.right)) {
                node = singleRightRotate(node);
            } else {
                node.left = singleLeftRotate(node.left);
                node = singleRightRotate(node);
            }
        } else if (balance < -1) {
            if (height(node.right.right) >= height(node.right.left)) {
                node = singleLeftRotate(node);
            } else {
                node.right = singleRightRotate(node.right);
                node = singleLeftRotate(node);
            }
        }

        return node;
    }

    Node singleLeftRotate(Node node) {
        Node nRoot = node.right;
        Node newRight = nRoot.left;
        nRoot.left = node;
        node.right = newRight;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        nRoot.height = 1 + Math.max(height(nRoot.left), height(nRoot.right));

        return nRoot;
    }

    Node singleRightRotate(Node node) {
        Node nRoot = node.left;
        Node newLeft = nRoot.right;

        nRoot.right = node;
        node.left = newLeft;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        nRoot.height = 1 + Math.max(height(nRoot.left), height(nRoot.right));

        return nRoot;
    }

    int height(Node node) {
        return node == null ? 0 : node.height;
    }

    public int balance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }
}