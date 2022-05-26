import java.util.Stack;

public class Pair {
    Node node;
    int state = 0;

    Pair(Node node, int state) {
        this.node = node;
        this.state = state;
    }

}

public static void prePostInOrder(Node root){
    String pre = "";
    String in = "";
    String post = "";
    Stack<Pair> stk = new Stack<>();
    stk.push(new Pair(root, 0));
    while(!stk.isEmpty()){
        Pair p = stk.peek();
        if(p.state==0){
            pre += p.node.data + " ";
            if(p.node.left!=null){
                stk.push(new Pair(p.node.left, 0));
            }
        }else if(p.state==1){
            in += p.node.data + " ";
            if(p.node.right!=null){
                stk.push(new Pair(p.node.right, 0));
            }
        }else{
            post += p.node.data + " ";  
            stk.pop();
        }
        p.state++;
    }
    System.out.println(pre);
    System.out.println(in);
    System.out.println(post);
}