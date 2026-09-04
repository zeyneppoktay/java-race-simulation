public class Car {
    private int id;
    private int time;
    private int currentCheckpoint;
    private String status;
    private int[] path;
    private int pathLength;

    public Car(int id) {
        this.id = id;
        this.time = 0;
        this.currentCheckpoint = 0;
        this.status = "racing";
        this.path = new int[200];
        this.path[0] = 0;
        this.pathLength = 1;
    }

    public int getId() { return id; }
    public int getTime() { return time; }
    public int getCheckpoint() { return currentCheckpoint; }
    public String getStatus() { return status; }

    public void advanceTime(int d) { time += d; }

    public void moveTo(int cp) {
        currentCheckpoint = cp;
        if (pathLength < path.length) {
            path[pathLength++] = cp;
        }
    }

    public void setStatus(String s) { status = s; }

    @Override
    public String toString() {
        return "Car " + id;
    }

    public String getPathString() {
        String s = "";
        for (int i = 0; i < pathLength; i++) {
            s += path[i];
            if (i != pathLength - 1) s += " -> ";
        }
        return s;
    }
}
