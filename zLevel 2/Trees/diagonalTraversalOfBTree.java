//TODO - GFG - Diagonal Traversal of binary tree
// [https://practice.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1]

class Tree {
    static int maxD = 0;

    public void getDiagonals(Node root, int currDiagonal, HashMap<Integer, ArrayList<Integer>> map) {
        // base case
        if (root == null)
            return;
        if (currDiagonal > maxD)
            maxD = currDiagonal;
        if (map.containsKey(currDiagonal) == false) {
            map.put(currDiagonal, new ArrayList<>());
            map.get(currDiagonal).add(root.data);
        } else {
            map.get(currDiagonal).add(root.data);
        }
        getDiagonals(root.left, currDiagonal + 1, map);
        getDiagonals(root.right, currDiagonal, map);
    }

    public ArrayList<Integer> diagonal(Node root) {
        // add your code here.
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        getDiagonals(root, 0, map);
        for (int i = 0; i <= maxD; i++) {
            ArrayList<Integer> list = map.get(i);
            if (list == null)
                continue;
            for (Integer x : list)
                ans.add(x);
        }
        return ans;
    }
}

// ! this code is BFS - level order traversal but doesn't work cos GFG one
// requires in DFS only
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