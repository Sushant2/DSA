//TODO GFG - Left View Of Binary Tree
// [https://practice.geeksforgeeks.org/problems/left-view-of-binary-tree/1]

class Tree {
    // Function to return list containing elements of left view of binary tree.
    ArrayList<Integer> leftView(Node root) {
        // Your code here
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        if (root == null)
            return ans;
        q.add(root);
        while (!q.isEmpty()) {
            ans.add(q.peek().data);
            int size = q.size();
            while (size-- > 0) {
                Node node = q.remove();
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
        }
        return ans;
    }
}