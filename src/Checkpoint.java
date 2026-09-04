public class Checkpoint {
    private int id;
    private String rule;
    private MyQueue fifo;
    private MyStack lifo;
    private MyHeap maxHeap;
    private MyHeap minHeap;

    public Checkpoint(int id, String rule) {
        this.id = id;
        this.rule = rule;
        fifo = new MyQueue();
        lifo = new MyStack();
        maxHeap = new MyHeap(true);
        minHeap = new MyHeap(false);
    }

    public int getId() { return id; }
    public String getRule() { return rule; }

    public void addCar(Car c) {
        if (rule.equals("FIFO")) fifo.enqueue(c);
        else if (rule.equals("LIFO")) lifo.push(c);
        else if (rule.equals("MAX")) maxHeap.add(c);
        else if (rule.equals("START")) minHeap.add(c);
    }

    public boolean hasCars() {
        return !(fifo.isEmpty() && lifo.isEmpty() && maxHeap.isEmpty() && minHeap.isEmpty());
    }

    public Car releaseCar() {
        if (rule.equals("FIFO")) return fifo.dequeue();
        if (rule.equals("LIFO")) return lifo.pop();
        if (rule.equals("MAX")) return maxHeap.poll();
        if (rule.equals("START")) return minHeap.poll();
        return null;
    }
}
