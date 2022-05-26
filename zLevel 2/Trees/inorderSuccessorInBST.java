//TODO - GFG - inorder successor in BST
// [https://practice.geeksforgeeks.org/problems/inorder-successor-in-bst/1]

//using another approach - if the "x" node is above then we can easily calculate inorder successor - right ka left most, but if "x" node is down, then we've to move our root where the node x is

class Solution {
    public Node inorderSuccessor(Node root, Node x) {
        // if node x is above - it's inorder successor - right's left most
        Node succ = null;
        if (x.right != null) {
            succ = x.right;
            while (succ.left != null)
                succ = succ.left;
            return succ;
        }
        // if node x is downward, we'll move our root to downward & if root is parent of
        // node x, we'll do succ = root & return
        while (root != null) {
            if (x.data > root.data)
                root = root.right;
            else if (x.data < root.data) {
                succ = root;
                root = root.left;
            } else {
                break;
            }
        }
        return succ;
    }

    // using MORRIS traversal
    class Solution {
        // returns the inorder successor of the Node x in BST (rooted at 'root')
        public Node inorderSuccessor(Node root, Node x) {
            // add code here.
            // morris traversal
            Node curr = root;
            Node succ = null;
            Boolean flag = false;
            while (curr != null) {
                if (curr.left == null) {
                    if (flag == true) {
                        succ = curr;
                        break;
                    }
                    if (curr == x)
                        flag = true;
                    curr = curr.right;
                } else {
                    Node iop = curr.left;
                    while (iop.right != null && iop.right != curr) {
                        iop = iop.right;
                    }
                    if (iop.right == null) {
                        iop.right = curr;
                        curr = curr.left;
                    } else {
                        if (flag == true) {
                            succ = curr;
                            break;
                        }
                        if (curr == x)
                            flag = true;
                        iop.right = null;
                        curr = curr.right;
                    }
                }

            }
            return succ;
        }
    }

    // using MORRIS Traversal - pre & curr node
class Solution
{
    // returns the inorder successor of the Node x in BST (rooted at 'root')
	public Node inorderSuccessor(Node root,Node x)
        {
        //add code here.
        Node curr = root;
        Node prev = null;
        Node succ = null;
        while(curr!=null){
            if(curr.left==null){
                if(prev ==x)
                succ = curr;
                
                prev = curr;
                curr = curr.right;
            }else{
                Node iop = curr.left;
                while(iop.right!=null && iop.right!=curr){
                    iop = iop.right;
                }
                if(iop.right==null){
                    iop.right = curr;
                    curr = curr.left;
                }else{
                    if(prev==x)
                    succ = curr;
                prev = curr;
                    iop.right = null;
                    curr = curr.right;
                }
            }
        }
        return succ;
        }
}