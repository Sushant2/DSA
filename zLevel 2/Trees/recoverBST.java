//TODO - Leetcode - 99 - Recover Binary Search
// [https://leetcode.com/problems/recover-binary-search-tree/]



//Efficient solution in O(1) space using MORRIS traversal
class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null, n1 = null, n2 = null;
        while (curr != null) {
            // if left does not exist
            if (curr.left == null) {
                if (prev != null) {
                    if (curr.val < prev.val) {
                        // if it's first time - as in starting n1 & n2 both are null
                        if (n1 == null) {
                            n1 = prev;
                            n2 = curr;
                        } else {
                            n2 = curr;
                        }
                    }
                }
                prev = curr;
                curr = curr.right;
            } else {
                // necche jane se pahle upar ane ka laste bananalo - iop.right = curr
                TreeNode iop = curr.left;
                while (iop.right != null && iop.right != curr) {
                    iop = iop.right;
                }
                // left not processed
                if (iop.right == null) {
                    iop.right = curr;
                    curr = curr.left;
                } else {
                    if (prev != null) {
                        if (curr.val < prev.val) {
                            // if it's first time - as in starting n1 & n2 both are null
                            if (n1 == null) {
                                n1 = prev;
                                n2 = curr;
                            } else {
                                n2 = curr;
                            }
                        }
                    }
                    prev = curr;
                    iop.right = null;
                    curr = curr.right;
                }
            }
        }
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }
}
