//TODO - Leetcode - 987 - Vertical Ordering Of Binary tree
// [https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/]

class Solution {
    static class Pair implements Comparable<Pair>{ 
	TreeNode node;
	int width;
	int height;
	Pair(TreeNode node, int width, int height){
		this.node = node;	
		this.width = width;
		this.height = height;	
	}
	//this - other means increasing
	//other - this decreasing
	public int compareTo(Pair other){
		//if on same depth
		if(this.height == other.height){
			return this.node.val - other.node.val;
		}
		//else the first one on height
		else{
			return this.height - other.height;
		}
	}
}

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Queue<Pair> q = new LinkedList<>();
	    q.add(new Pair(root, 0, 1));
	  //hashmap of <width<Pair>> - comparabale
	  HashMap<Integer,PriorityQueue<Pair>> map = new HashMap<>();
	  //2 variables of maintaing leftmost width & rightmost width
	  int lmw = 0, rmw = 0;
	  while(!q.isEmpty()){
	 	Pair p = q.remove();
		if(p.width < lmw)
			lmw = p.width;
		if(p.width > rmw)
			rmw = p.width;
		//put in hashmap - if key doesnot exist - create PQ over there & insert values
		if(map.containsKey(p.width)==false){
			map.put(p.width, new PriorityQueue<>());
			map.get(p.width).add(p);
		}else{
			map.get(p.width).add(p);
		}
		if(p.node.left!=null){
			q.add(new Pair(p.node.left, p.width-1, p.height+1));	
		}
		if(p.node.right!=null){
			q.add(new Pair(p.node.right, p.width+1, p.height+1));
		}
	  }
	  //we have to written our answer in list of list
	  List<List<Integer>> ans = new ArrayList<>();
	  for(int w = lmw;w<=rmw;w++){
		List<Integer> list = new ArrayList<>();
		PriorityQueue<Pair> pq = map.get(w);
		while(!pq.isEmpty()){
			Pair temp = pq.remove();
			list.add(temp.node.val);	
		}
		ans.add(list);
	  }
	return ans;
    }
}


//we can use "arrayList" instead of priorrity queue, it's just that we'have to use sorting at the end for width
//time complexity is same as of above - no change

class Solution {
    static class Pair implements Comparable<Pair> {
        TreeNode node;
        int width;
        int height;

        Pair(TreeNode node, int width, int height) {
            this.node = node;
            this.width = width;
            this.height = height;
        }

        // this - other means increasing
        // other - this decreasing
        public int compareTo(Pair other) {
            // if on same depth
            if (this.height == other.height) {
                return this.node.val - other.node.val;
            }
            // else the first one on height
            else {
                return this.height - other.height;
            }
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0, 1));
        // hashmap of <width<Pair>> - comparabale
        HashMap<Integer, ArrayList<Pair>> map = new HashMap<>();
        // 2 variables of maintaing leftmost width & rightmost width
        int lmw = 0, rmw = 0;
        while (!q.isEmpty()) {
            Pair p = q.remove();
            if (p.width < lmw)
                lmw = p.width;
            if (p.width > rmw)
                rmw = p.width;
            // put in hashmap - if key doesnot exist - create PQ over there & insert values
            if (map.containsKey(p.width) == false) {
                map.put(p.width, new ArrayList<>());
                map.get(p.width).add(p);
            } else {
                map.get(p.width).add(p);
            }
            if (p.node.left != null) {
                q.add(new Pair(p.node.left, p.width - 1, p.height + 1));
            }
            if (p.node.right != null) {
                q.add(new Pair(p.node.right, p.width + 1, p.height + 1));
            }
        }
        // we have to written our answer in list of list
        List<List<Integer>> ans = new ArrayList<>();
        for (int w = lmw; w <= rmw; w++) {
            List<Integer> list = new ArrayList<>();
            ArrayList<Pair> unsortedList = map.get(w);
            Collections.sort(unsortedList);
            for (Pair x : unsortedList)
                list.add(x.node.val);
            ans.add(list);
        }
        return ans;
    }
}