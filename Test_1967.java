import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1967 {
	
	public static ArrayList<ArrayList<Node>> tree;
	public static int start;
	public static int max;
	public static boolean[] visit;
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList<ArrayList<Node>>();
        for(int i = 0; i <= n; i++) {
        	tree.add(new ArrayList<Node>());
        }
        for(int i = 0; i < n - 1; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int p = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	tree.get(p).add(new Node(c, v));
        	tree.get(c).add(new Node(p, v));
        }
        
        max = -1;
        start = 1; // 1에서 가장 멀리 있는 점 
        visit = new boolean[n + 1];
        dfs(1, 0);
        visit = new boolean[n + 1];
        dfs(start, 0);
        System.out.println(max);
    }
    
    public static void dfs(int n, int length) {
    	if(max < length) {
    		max = length;
    		start = n;
    	}
    	
    	visit[n] = true;
    	for(Node node : tree.get(n)) {
    		if(!visit[node.child]) {
    			dfs(node.child, length + node.value);
    		}
    	}
    }
    
    public static class Node{
    	int child;
    	int value;
    	public Node (int child, int value) {
    		this.child = child;
    		this.value = value;
    	}
    }
}