public class Road {
    private int start, end, distance;

    public Road(int s, int e, int d) {
        start = s;
        end = e;
        distance = d;
    }

    public int getStart() { return start; }
    public int getEnd() { return end; }
    public int getDistance() { return distance; }
}
