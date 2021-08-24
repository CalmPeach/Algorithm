import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 다익스트라
public class Test_1238 {
	
	static int n;
	static int m;
	static int x;
	static ArrayList<ArrayList<Node>> map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken()) - 1;
		map = new ArrayList<ArrayList<Node>>();
		for(int i = 0; i < n; i++)
			map.add(new ArrayList<Node>());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;
			int value = Integer.parseInt(st.nextToken());
			map.get(n1).add(new Node(n2, value));
		}
		
		int[] total = new int[n];
		
		for(int j = 0; j < n; j++) {
			PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return Integer.compare(o1.value, o2.value);
				}
			});
			Node[] dist = new Node[n];
			boolean[] visit = new boolean[n];
			for(int i = 0; i < n; i++) {
				if(i == j) {
					dist[i] = new Node(i, 0);
				} else {
					dist[i] = new Node(i, Integer.MAX_VALUE);
				}
				pq.add(dist[i]);
			}
			
			while(!pq.isEmpty()) {
				Node now = pq.poll();
				
				ArrayList<Node> link = map.get(now.node);
				for(int i = 0; i < link.size(); i++) {
					Node next = link.get(i);
					if(!visit[next.node] && dist[next.node].value > dist[now.node].value + next.value) {
						dist[next.node].value = dist[now.node].value + next.value;
						pq.remove(dist[next.node]);
						pq.add(dist[next.node]);
					}
				}
				visit[now.node] = true;
			}
			
			if(j != x) {
				total[j] += dist[x].value; 
			} else {
				for(int i = 0; i < n; i++) {
					if(i == x)
						continue;
					total[i] += dist[i].value;
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++) {
			if(total[i] > max)
				max = total[i];
		}
		System.out.println(max);
	}
	
	public static class Node{
		int node;
		int value;
		public Node(int node, int value) {
			this.node = node;
			this.value = value;
		}
	}
}