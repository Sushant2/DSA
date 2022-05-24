//TODO GFG - Reverse Level Order Traversal Of Binary Tree
// [https://practice.geeksforgeeks.org/problems/reverse-level-order-traversal/1/]

class Tree {
    public ArrayList<Integer> reverseLevelOrder(Node node) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node curr = q.remove();
            ans.add(curr.data);
            if (curr.right != null)
                q.add(curr.right);
            if (curr.left != null)
                q.add(curr.left);
        }
        ArrayList<Integer> finalAns = new ArrayList<>();
        for (int i = ans.size() - 1; i >= 0; i--) {
            finalAns.add(ans.get(i));
        }
        return finalAns;
    }
}