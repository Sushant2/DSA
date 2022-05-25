//TODO - Leetcode - 144 - Preorder Traversal
// [https://leetcode.com/problems/binary-tree-preorder-traversal/]

//1. we can do that using DFS - didn't write

//2. iterative takinng using stack
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        // iterative
        List<Integer> pre = new ArrayList<>();
        if (root == null)
            return pre;
        // custom stack
        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);
        while (!stk.isEmpty()) {
            TreeNode node = stk.pop();
            pre.add(node.val);
            if (node.right != null)
                stk.push(node.right);
            if (node.left != null)
                stk.push(node.left);
        }
        return pre;
    }
}

// 3. MORRIS Traversal
// In O(1) space

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                // inorder predecessor = left's right most
                TreeNode iop = curr.left;
                while (iop.right != null && iop.right != curr) {
                    iop = iop.right;
                }
                if (iop.right == null) {
                    ans.add(curr.val);
                    iop.right = curr; // making the thread
                    curr = curr.left;
                } else {
                    // unlink the thread
                    iop.right = null;
                    curr = curr.right;
                }
            }
        }
        return ans;
    }
}