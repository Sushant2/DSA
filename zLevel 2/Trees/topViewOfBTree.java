//TODO GFG - Bottom View Of Binary Tree 
// [https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1]

class Solution {
    // Function to return a list of nodes visible from the top view
    // from left to right in Binary Tree.
    static class Pair {
        int vl = 0;
        Node node;

        Pair(int vl, Node node) {
            this.vl = vl;
            this.node = node;
        }
    }

    static ArrayList<Integer> topView(Node root) {
        // add your code
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer, Node> map = new HashMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, root));
        int lv = 0, rv = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Pair p = q.remove();
                if (p.vl < lv)
                    lv = p.vl;
                if (p.vl > rv)
                    rv = p.vl;
                if (map.containsKey(p.vl) == false)
                    map.put(p.vl, p.node);
                if (p.node.left != null)
                    q.add(new Pair(p.vl - 1, p.node.left));
                if (p.node.right != null)
                    q.add(new Pair(p.vl + 1, p.node.right));
            }
        }
        for (int i = lv; i <= rv; i++)
            ans.add(map.get(i).data);
        return ans;
    }
}