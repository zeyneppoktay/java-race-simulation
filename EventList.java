public class EventList {
    private Eventt[] events = new Eventt[2000];
    private int size = 0;

    public void add(Eventt e) {
        int i = size - 1;
        while (i >= 0 && events[i].arrivalTime > e.arrivalTime) {
            events[i + 1] = events[i];
            i--;
        }
        events[i + 1] = e;
        size++;
    }

    public Eventt poll() {
        if (size == 0) return null;
        Eventt e = events[0];
        for (int i = 1; i < size; i++) events[i - 1] = events[i];
        size--;
        return e;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

