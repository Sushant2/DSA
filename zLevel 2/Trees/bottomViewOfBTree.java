//TODO GFG - Bottom View Of Binary Tree 
// [https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1]

//it's just a slight variation of "Top" View of binary tree, that, here we update the hashmap everytime with new nodes, so that last nodes will will put there in hashmap

class Solution {
    // Function to return a list containing the bottom view of the given tree.
    static class Pair {
        int vl = 0;
        Node node;

        Pair(int vl, Node node) {
            this.vl = vl;
            this.node = node;
        }
    }

    public ArrayList<Integer> bottomView(Node root) {
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
                // if(map.containsKey(p.vl)==false)
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