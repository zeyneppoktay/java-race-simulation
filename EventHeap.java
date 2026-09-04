public class EventHeap {
    private Eventt[] heap;
    private int size;

    public EventHeap() {
        heap = new Eventt[64];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Eventt e) {
        if (e == null) return;
        if (size == heap.length) grow();
        heap[size] = e;
        swim(size);
        size++;
    }

    public Eventt poll() {
        if (size == 0) return null;

        Eventt min = heap[0];
        heap[0] = heap[--size];
        heap[size] = null;

        sink(0);
        return min;
    }

    private boolean less(Eventt a, Eventt b) {
        if (a.arrivalTime != b.arrivalTime) return a.arrivalTime < b.arrivalTime;
        int ida = (a.car == null) ? Integer.MAX_VALUE : a.car.getId();
        int idb = (b.car == null) ? Integer.MAX_VALUE : b.car.getId();
        return ida < idb;
    }

    private void swim(int k) {
        while (k > 0) {
            int p = (k - 1) / 2;
            if (!less(heap[k], heap[p])) break;
            swap(p, k);
            k = p;
        }
    }

    private void sink(int k) {
        while (true) {
            int l = 2 * k + 1;
            int r = 2 * k + 2;
            if (l >= size) break;

            int m = l;
            if (r < size && less(heap[r], heap[l])) m = r;

            if (!less(heap[m], heap[k])) break;
            swap(k, m);
            k = m;
        }
    }

    private void swap(int i, int j) {
        Eventt t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    private void grow() {
        Eventt[] b = new Eventt[heap.length * 2];
        for (int i = 0; i < heap.length; i++) b[i] = heap[i];
        heap = b;
    }
}
