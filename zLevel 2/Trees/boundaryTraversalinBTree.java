//TODO - GFG - Boundary Traversal In Binary Tree
// [https://practice.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1]

class Solution {

    public static void getLeftW(Node node, ArrayList<Integer> list) {
        if (node == null)
            return;
        if (node.left != null || node.right != null)
            list.add(node.data);
        if (node.left != null)
            getLeftW(node.left, list);
        else if (node.right != null)
            getLeftW(node.right, list);
    }

    public static void getLeaves(Node node, ArrayList<Integer> list) {
        if (node == null)
            return;
        getLeaves(node.left, list);
        if (node.left == null && node.right == null)
            list.add(node.data);
        getLeaves(node.right, list);
    }

    public static void getRightW(Node node, ArrayList<Integer> list) {
        if (node == null)
            return;
        if (node.right != null)
            getRightW(node.right, list);
        else if (node.left != null)
            getRightW(node.left, list);
        if (node.left != null || node.right != null)
            list.add(node.data);

    }

    ArrayList<Integer> boundary(Node node) {
        // in 4 parts
        // left wall +left subtree leaves + right subtree leaves + right wall
        ArrayList<Integer> list = new ArrayList<>();
        list.add(node.data);
        getLeftW(node.left, list);
        getLeaves(node.left, list);
        getLeaves(node.right, list);
        getRightW(node.right, list);

        return list;
    }
}
