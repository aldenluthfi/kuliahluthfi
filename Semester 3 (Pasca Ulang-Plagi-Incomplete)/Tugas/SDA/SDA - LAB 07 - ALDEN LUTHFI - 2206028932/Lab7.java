import java.util.*;
import java.io.*;

public class Lab7 {

    static class Box implements Comparable<Box> {
        static int idCounter;

        int id;
        long value;
        String state;

        Box(long value, String state) {
            this.id = idCounter++;
            this.value = value;
            this.state = state;
        }

        @Override
        public int compareTo(Box other) {
            if (this.value == other.value)
                return -Integer.compare(this.id, other.id);

            return Long.compare(this.value, other.value);
        }
    }

    static class BoxContainer {
        public ArrayList<Box> heap;
        public HashMap<Integer, Integer> idToIndexMap;

        public BoxContainer() {
            this.heap = new ArrayList<>();
            this.idToIndexMap = new HashMap<>();
        }

        public static int getParentIndex(int i) {
            return (i - 1) / 2;
        }

        public void percolateDown(int i) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;

            if (left < heap.size() && heap.get(left).compareTo(heap.get(largest)) > 0) {
                largest = left;
            }

            if (right < heap.size() && heap.get(right).compareTo(heap.get(largest)) > 0) {
                largest = right;
            }

            if (largest != i) {
                swap(i, largest);
                percolateDown(largest);
            }
        }

        public void percolateUp(int i) {
            while (i > 0 && heap.get(i).compareTo(heap.get(getParentIndex(i))) > 0) {
                swap(i, getParentIndex(i));
                i = getParentIndex(i);
            }
        }

        public void insert(Box box) {
            heap.add(box);
            idToIndexMap.put(box.id, heap.size() - 1);
            percolateUp(heap.size() - 1);
        }

        public Box peek() {
            return heap.get(0);
        }

        public void swap(int firstIndex, int secondIndex) {
            Box temp = heap.get(firstIndex);
            heap.set(firstIndex, heap.get(secondIndex));
            heap.set(secondIndex, temp);

            idToIndexMap.put(heap.get(firstIndex).id, firstIndex);
            idToIndexMap.put(heap.get(secondIndex).id, secondIndex);
        }

        public void updateBox(Box box) {
            int index = idToIndexMap.get(box.id);

            percolateUp(index);
            percolateDown(index);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());

        ArrayList<Box> boxes = new ArrayList<>();
        BoxContainer boxContainer = new BoxContainer();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long value = Long.parseLong(st.nextToken());
            String state = st.nextToken();

            Box box = new Box(value, state);
            boxes.add(box);
            boxContainer.insert(box);
        }

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if ("A".equals(command)) {
                int V = Integer.parseInt(st.nextToken());
                String S = st.nextToken();
                Box box = new Box(V, S);
                boxes.add(box);
                boxContainer.insert(box);
            } else if ("D".equals(command)) {
                int idI = Integer.parseInt(st.nextToken());
                int idJ = Integer.parseInt(st.nextToken());

                Box boxI = boxes.get(idI);
                Box boxJ = boxes.get(idJ);

                if (!boxI.state.equals(boxJ.state)) {
                    if ((boxI.state.equals("R") && boxJ.state.equals("S")) ||
                            (boxI.state.equals("P") && boxJ.state.equals("R")) ||
                            (boxI.state.equals("S") && boxJ.state.equals("P"))) {
                        boxI.value += boxJ.value;
                        boxJ.value /= 2;
                    } else {
                        boxJ.value += boxI.value;
                        boxI.value /= 2;
                    }
                    boxContainer.updateBox(boxI);
                    boxContainer.updateBox(boxJ);
                }
            } else if ("N".equals(command)) {
                int idI = Integer.parseInt(st.nextToken());
                Box boxI = boxes.get(idI);
                int[] ids = { idI - 1, idI + 1 };

                for (int neighborId : ids) {
                    if (neighborId >= 0 && neighborId < boxes.size()) {
                        Box neighborBox = boxes.get(neighborId);
                        if (!boxI.state.equals(neighborBox.state)) {
                            if ((boxI.state.equals("R") && neighborBox.state.equals("S")) ||
                                    (boxI.state.equals("P") && neighborBox.state.equals("R")) ||
                                    (boxI.state.equals("S") && neighborBox.state.equals("P"))) {
                                boxI.value += neighborBox.value;
                                boxContainer.updateBox(boxI);
                            }
                        }
                    }
                }
            }

            Box topBox = boxContainer.peek();
            pw.println(topBox.value + " " + topBox.state);
        }

        pw.flush();
        pw.close();
    }
}