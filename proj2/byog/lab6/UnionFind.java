package byog.lab6;

public class UnionFind {
    private int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    public int find(int v1) {
        validate(v1);
        int temp = v1;
        while (parent[v1] >= 0) {
            v1 = parent[v1];
        }
        while (parent[temp] >= 0) {
            int tempPlus = temp;
            temp = parent[temp];
            parent[tempPlus] = v1;
        }
        return v1;
    }

    public void validate(int v1) {
        if (v1 < 0 || v1 >= parent.length) {
            throw new IllegalArgumentException("not a valid index");
        }
    }

    public int sizeOf(int v1) {
        validate(v1);
        return -parent[find(v1)];
    }

    public int parent(int v1) {
        validate(v1);
        return parent[v1];
    }

    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    public void union(int v1, int v2) {
        if (sizeOf(v1) <= sizeOf(v2)) {
            parent[find(v2)] += parent[find(v1)];
            parent[find(v1)] = find(v2);
        } else {
            parent[find(v1)] += parent[find(v2)];
            parent[find(v2)] = find(v1);
        }
    }

    /*
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (parent(v1) < 0) {
            if (parent(v2) < 0) {
                id[v1] = v2;
                id[v2]--;
            } else {
                id[v1] = parent(v2);
                id[parent(v2)]--;
            }
        } else {
            if (parent(v2) < 0) {
                id[v2] = parent(v1);
                id[parent(v1)]--;
            } else {
                if (sizeOf(v1) <= sizeOf(v2)) {
                    id[parent(v2)] += id[parent(v1)];
                    id[parent(v1)] = parent(v2);
                } else {
                    id[parent(v1)] += id[parent(v2)];
                    id[parent(v2)] = parent(v1);
                }
            }
        }
    }
     */

}
