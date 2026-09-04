

public class DepthFirstPaths {
    private boolean[] marked;

    public DepthFirstPaths(Graph g, int s) {
        marked = new boolean[g.size()];
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (Road r : g.neighbors(v)) {
            int w = r.getEnd();
            if (!marked[w]) dfs(g, w);
        }
    }

    public boolean hasPathTo(int v) {
        if (v < 0 || v >= marked.length) return false;
        return marked[v];
    }
}

