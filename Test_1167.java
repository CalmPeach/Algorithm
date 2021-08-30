import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1167 {
	
	public static ArrayList<ArrayList<Node>> tree;
	public static int start;
	public static int max;
	public static boolean[] visit;
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList<ArrayList<Node>>();
        for(int i = 0; i < n + 1; i++) {
        	tree.add(new ArrayList<Node>());
        }
        for(int i = 0; i < n; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int v = Integer.parseInt(st.nextToken());
        	while(true) {
        		int next = Integer.parseInt(st.nextToken());
        		if(next == -1)
        			break;
        		int value = Integer.parseInt(st.nextToken());
        		tree.get(v).add(new Node(next, value));
        	}
        }
        
        max = -1;
        start = 1;
        visit = new boolean[n + 1];
        dfs(1, 0);
        visit = new boolean[n + 1];
        dfs(start, 0);
        System.out.println(max);
    }
    
    public static void dfs(int v, int length) {
    	if(max < length) {
    		max = length;
    		start = v;
    	}
    	
    	visit[v] = true;
    	for(Node node : tree.get(v)) {
    		if(!visit[node.next]) {
    			dfs(node.next, length + node.value);
    		}
    	}
    }
    
    public static class Node{
    	int next;
    	int value;
    	public Node(int next, int value) {
    		this.next = next;
    		this.value = value;
    	}
    }
}