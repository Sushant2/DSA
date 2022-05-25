
//! this code is BFS - level order traversal but doesn't work cos GFG one requires in DFS only
class Tree {
    public static class Pair {
        Node node;
        int dL = 0;

        Pair(Node node, int diagonalLevel) {
            this.node = node;
            this.dL = diagonalLevel;
        }
    }

    public ArrayList<Integer> diagonal(Node root) {
        Queue<Pair> q = new LinkedList<>();
        HashMap<Integer, ArrayList<Pair>> map = new HashMap<>();
        q.add(new Pair(root, 0));
        int maxDiagonal = 0;
        while (!q.isEmpty()) {
            Pair p = q.remove();
            if (p.dL > maxDiagonal)
                maxDiagonal = p.dL;
            if (map.containsKey(p.dL) == false) {
                map.put(p.dL, new ArrayList<>());
                map.get(p.dL).add(p);
            } else {
                map.get(p.dL).add(p);
            }
            if (p.node.left != null) {
                q.add(new Pair(p.node.left, p.dL + 1));
            }
            if (p.node.right != null) {
                q.add(new Pair(p.node.right, p.dL));
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= maxDiagonal; i++) {
            ArrayList<Pair> pairs = map.get(i);
            for (Pair x : pairs)
                ans.add(x.node.data);
        }
        return ans;
    }
}