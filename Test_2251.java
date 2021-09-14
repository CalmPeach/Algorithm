import java.io.*;
import java.util.*;
 
public class Test_2251 {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int a = Integer.parseInt(st.nextToken());
    	int b = Integer.parseInt(st.nextToken());
    	int c = Integer.parseInt(st.nextToken());
    	bfs(a, b, c);
    }
    
    public static void bfs(int a, int b, int c) {
    	boolean[][][] visit = new boolean[201][201][201];
    	visit[0][0][c] = true;
    	Queue<Node> q = new LinkedList<Node>();
    	int[] tmp = {0, 0, c};
    	q.add(new Node(tmp));
    	ArrayList<Integer> arr = new ArrayList<Integer>();
    	int[] limit = {a, b, c};
    	
    	while(!q.isEmpty()) {
    		Node now = q.poll();
    		if(now.h[0] == 0) {
    			arr.add(now.h[2]);
    		}
    		
    		for(int i = 0; i < 3; i++) {
    			if(now.h[i] == 0)
    				continue;
    			for(int j = 0; j < 3; j++) {
    				if(i == j)
    					continue;
    				int[] next = new int[3];
    				for(int k = 0; k < 3; k++)
    					next[k] = now.h[k];
    				next[j] = now.h[i] + now.h[j];
    				if(next[j] > limit[j]) {
    					int re = next[j] - limit[j];
    					next[i] = re;
    					next[j] = limit[j];
    				} else {
    					next[i] = 0;
    				}
    				if(!visit[next[0]][next[1]][next[2]]) {
    					q.add(new Node(next));
    					visit[next[0]][next[1]][next[2]] = true;
    				}
    			}
    		}
    	}
    	arr.sort(Comparator.naturalOrder());
    	for(int i = 0; i < arr.size(); i++)
    		System.out.print(arr.get(i) + " ");
    	System.out.println();
    }
    
    public static class Node{
    	int h[];
    	public Node(int[] h) {
    		this.h = h;
    	}
    }
}
