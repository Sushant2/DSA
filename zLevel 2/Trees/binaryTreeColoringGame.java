//TODO - GFG -Leetcode - Binary Tree Coloring Game -
// [https://leetcode.com/problems/binary-tree-coloring-game/]

class Solution {
    static int xLsize = 0, xRsize = 0;

    public int getSize(TreeNode root, int x) {
        if (root == null)
            return 0;

        int left = getSize(root.left, x);
        int right = getSize(root.right, x);
        // in post order we get size of x's left & right
        if (root.val == x) {
            xLsize = left;
            xRsize = right;
        }
        return left + right + 1;
    }

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        getSize(root, x);
        int otherSize = n - (xLsize + xRsize + 1);
        int maxP2 = Math.max(otherSize, Math.max(xLsize, xRsize));
        // to win p2, it's size will be max from rest
        int rest = n - maxP2;
        if (maxP2 > rest)
            return true;
        else
            return false;
    }
}