//TODO - Leetcode - 94 - Inorder Traversal
[https://leetcode.com/problems/binary-tree-inorder-traversal/]

// 1. Normal DFS traversal, din't wrote


//2. Iterative using stack
class Solution {
    private void pushAllLeft(TreeNode node, Stack<TreeNode> stk){
        while(node!=null){
            stk.push(node);
            node= node.left;
        }
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> in = new ArrayList<>();
        if(root == null) return in;
        Stack<TreeNode> stk = new Stack<>();
        pushAllLeft(root, stk);
        while(!stk.isEmpty()){
            TreeNode curr = stk.pop();
            in.add(curr.val);
            pushAllLeft(curr.right, stk);
        }
        return in;
    }
}

// 3. MORRIS Traversal
//in O(1) space

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
         List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        TreeNode curr = root;
        while(curr!=null){
            if(curr.left == null){
                ans.add(curr.val);
                curr = curr.right;
            }else{
                //inorder predecessor = left's right most
                TreeNode iop = curr.left;
                while(iop.right!=null && iop.right!=curr){
                    iop = iop.right;
                }
                if(iop.right == null){
                    iop.right = curr; //making the thread
                    curr = curr.left;
                }else{
                    //unlink the thread
                    iop.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return ans;
    }
}