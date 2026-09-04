public class Graph {
    private Road[][] adj;
    private int[] edgeCount;
    private int numV;

    public Graph(int v) {
        this.numV = v;
        adj = new Road[v][20];
        edgeCount = new int[v];
        for (int i = 0; i < v; i++) edgeCount[i] = 0;
    }

    public void addEdge(int s, int e, int d) {
        if (s >= 0 && s < numV) {
            int idx = edgeCount[s];
            adj[s][idx] = new Road(s, e, d);
            edgeCount[s]++;
        }
    }

    public Road[] neighbors(int v) {
        int count = edgeCount[v];
        Road[] result = new Road[count];
        for (int i = 0; i < count; i++) result[i] = adj[v][i];
        return result;
    }

    public int size() { return numV; }
}


