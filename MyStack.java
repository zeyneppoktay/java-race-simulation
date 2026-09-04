public class MyStack {
    private Car[] data = new Car[1000];
    private int top = -1;

    public void push(Car c)
    {
        data[++top] = c;
    }

    public Car pop() {
        if (top < 0) return null;
        return data[top--];
    }

    public boolean isEmpty() {
        return top < 0;
    }
}
