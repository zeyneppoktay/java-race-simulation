public class MyHeap {
    private Car[] heap = new Car[1000];
    private int size = 0;
    private boolean isMax;

    public MyHeap(boolean isMax) {
        this.isMax = isMax;
    }

    public void add(Car c) {
        heap[size] = c;
        int i = size;
        size++;
        while (i > 0) {
            int p = (i - 1) / 2;
            boolean condition = isMax ? (heap[i].getId() > heap[p].getId()) : (heap[i].getId() < heap[p].getId());
            if (!condition) break;
            Car tmp = heap[i]; heap[i] = heap[p]; heap[p] = tmp;
            i = p;
        }
    }

    public Car poll() {
        if (size == 0) return null;
        Car result = heap[0];
        heap[0] = heap[--size];
        int i = 0;
        while (true) {
            int l = 2 * i + 1, r = 2 * i + 2, swap = i;
            if (l < size) {
                boolean condition = isMax ? (heap[l].getId() > heap[swap].getId()) : (heap[l].getId() < heap[swap].getId());
                if (condition) swap = l;
            }
            if (r < size) {
                boolean condition = isMax ? (heap[r].getId() > heap[swap].getId()) : (heap[r].getId() < heap[swap].getId());
                if (condition) swap = r;
            }
            if (swap == i) break;
            Car tmp = heap[i]; heap[i] = heap[swap]; heap[swap] = tmp;
            i = swap;
        }
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}