//TODO - Leetcode - 145 - PostOrder Traversal
// [https://leetcode.com/problems/binary-tree-postorder-traversal/]

//1. we can do normal postorder DFS traversal

//2. iterative using 2 stacks
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> post = new ArrayList<>();
        if (root == null)
            return post;
        Stack<TreeNode> stk1 = new Stack<>();
        Stack<TreeNode> stk2 = new Stack<>();
        stk1.push(root);
        while (!stk1.isEmpty()) {
            TreeNode stk1Peek = stk1.pop();
            stk2.push(stk1Peek);
            if (stk1Peek.left != null)
                stk1.push(stk1Peek.left);
            if (stk1Peek.right != null)
                stk1.push(stk1Peek.right);
        }
        while (!stk2.isEmpty()) {
            post.add(stk2.pop().val);
        }
        return post;
    }
}

// 3. iterative using 1 stack & linkedlist
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> post = new LinkedList<>();
        if (root == null)
            return post;
        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);
        while (!stk.isEmpty()) {
            TreeNode curr = stk.pop();
            post.addFirst(curr.val);
            if (curr.left != null)
                stk.push(curr.left);
            if (curr.right != null)
                stk.push(curr.right);
        }
        return post;
    }
}