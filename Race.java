import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class Race {
    private Graph graph;
    private Checkpoint[] checkpoints;
    private EventHeap eventQueue;
    private Car[] cars;
    private Car[] winners;
    private int finishCount = 0;
    private Random rand = new Random();

    public void loadTrack(String file) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(file));
        int v = sc.nextInt();
        int e = sc.nextInt();
        graph = new Graph(v);
        checkpoints = new Checkpoint[v];

        for (int i = 0; i < v; i++) {
            checkpoints[i] = new Checkpoint(i, ruleFor(i));
        }

        for (int i = 0; i < e; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();
            int dist = sc.nextInt();
            graph.addEdge(s, d, dist);
        }

        sc.close();
        eventQueue = new EventHeap();
    }

    private String ruleFor(int id) {
        if (id == 0) return "START";
        if (id == 10) return "PIT";
        if (id == 15) return "FINISH";
        int r = rand.nextInt(3);
        if (r == 0) return "FIFO";
        if (r == 1) return "LIFO";
        return "MAX";
    }

    public void createCars(int n) {
        cars = new Car[n];
        winners = new Car[n];
        for (int i = 0; i < n; i++) {
            cars[i] = new Car(i + 1);
            checkpoints[0].addCar(cars[i]);
        }
    }

    public void startRace() {
        System.out.println(" Race started!\n");

        while (checkpoints[0].hasCars()) {
            Car c = checkpoints[0].releaseCar();
            dispatch(c, 0);
        }

        int V = checkpoints.length;

        Eventt nextTmpEvv = null;

        while (nextTmpEvv != null || !eventQueue.isEmpty()) {

            Eventt first;
            if (nextTmpEvv != null) {
                first = nextTmpEvv;
                nextTmpEvv = null;
            } else {
                first = eventQueue.poll();
            }

            int t = first.arrivalTime;
            boolean[] touched = new boolean[V];
            handleArrival(first, touched);

            while (!eventQueue.isEmpty()) {
                Eventt ev = eventQueue.poll();

                if (ev.arrivalTime == t) {
                    handleArrival(ev, touched);
                } else {
                    nextTmpEvv = ev;
                    break;
                }
            }

            for (int cp = 0; cp < V; cp++) {
                if (touched[cp]) {
                    System.out.println("Checkpoint " + cp + " rule: " + checkpoints[cp].getRule());
                    releaseCheckpoint(cp);
                }
            }
        }

        printResults();
    }

    private void handleArrival(Eventt ev, boolean [] touched) {
        Car c = ev.car;

        if (!c.getStatus().equals("racing")) return;
        int delta = ev.arrivalTime - c.getTime();
        if (delta > 0) c.advanceTime(delta);

        c.moveTo(ev.targetCheckpoint);

        System.out.println( c + " reached checkpoint " + ev.targetCheckpoint + " at t=" + c.getTime());

        if (ev.targetCheckpoint == 10) {
            c.setStatus("pit");
            System.out.println( c + " fell into PIT");
            return;
        }

        if (ev.targetCheckpoint == 15) {
            c.setStatus("finished");
            winners[finishCount++] = c;
            System.out.println( c + " FINISHED!");
            return;
        }

        checkpoints[ev.targetCheckpoint].addCar(c);
        touched[ev.targetCheckpoint] = true;
    }

    private void releaseCheckpoint(int id) {
        System.out.println("Releasing checkpoint " + id + " | rule=" + checkpoints[id].getRule());

        while (checkpoints[id].hasCars()) {
            Car c = checkpoints[id].releaseCar();
            System.out.println("...Passing: " + c);
            dispatch(c, id);
        }
    }


   private void dispatch(Car c, int from) {
        if (!c.getStatus().equals("racing")) return;

        Road[] roads = graph.neighbors(from);

        if (roads == null || roads.length == 0) {
            System.out.println(c + " canNOT continue (no outgoing road from " + from + ")");
            c.setStatus("pit");
            return;
        }

        int k = 0;
        while (k < roads.length && roads[k] != null) k++;

        if (k == 0) {
            System.out.println( c + " canNOT continue (no outgoing road from " + from + ")");
            c.setStatus("pit");
            return;
        }

        Road r = roads[rand.nextInt(k)];
        int arrivalTime = c.getTime() + r.getDistance();

        System.out.println(c + " takes road " + from + r.getEnd()
                + " (distance=" + r.getDistance() + ")");

        eventQueue.add(new Eventt(c, arrivalTime, r.getEnd()));
    }


    private void printResults() {
        System.out.println("\n=== LEADERBOARD ===");

        for (int i = 0; i < finishCount; i++) {
            int best = i;
            for (int j = i + 1; j < finishCount; j++) {
                Car a = winners[j];
                Car b = winners[best];

                if (a.getTime() < b.getTime()) {
                    best = j;
                } else if (a.getTime() == b.getTime() && a.getId() < b.getId()) {
                    best = j;
                }
            }

            Car tmp = winners[i];
            winners[i] = winners[best];
            winners[best] = tmp;
        }

        for (int i = 0; i < finishCount; i++) {
            System.out.println((i + 1) + ". " + winners[i] + " (t=" + winners[i].getTime() + ")");
        }

        System.out.println("\n---* NOT FINISHED *---");
        for (int i = 0; i < cars.length; i++) {
            if (!cars[i].getStatus().equals("finished")) {
                System.out.println(cars[i] + " (" + cars[i].getStatus() + ")");
            }
        }

        System.out.println("\n=== PATHS ===");
        for (int i = 0; i < cars.length; i++) {
            System.out.println(cars[i] + ": " + cars[i].getPathString());
        }
    }

    public boolean hasPath(int from, int to) {
        int V = graph.size();
        if (from < 0 || from >= V || to < 0 || to >= V) {
            System.out.println("Invalid checkpoint number. Valid range: 0.." + (V - 1));
            return false;
        }

        DepthFirstPaths dfs = new DepthFirstPaths(graph, from);
        return dfs.hasPathTo(to);
    }

}