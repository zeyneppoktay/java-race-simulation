public class MyQueue {
    private Car[] data = new Car[8];
    private int front = 0, rear = 0, size = 0;

    public void enqueue(Car c) {

        if (size == data.length) grow();
        data[rear] = c;
        rear = (rear + 1) % data.length;
        size++;
    }

    public Car dequeue() {

        if (isEmpty()) return null;

        Car c = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        return c;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {

        Car[] b = new Car[data.length * 2];

        for (int i = 0; i < size; i++) {

            b[i] = data[(front + i) % data.length];
        }
        data = b;
        front = 0;
        rear = size;
    }
}



