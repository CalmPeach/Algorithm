import java.io.*;
import java.util.*;
 
public class Test_6118 {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
    	for(int i = 0; i < n; i++) {
    		map.add(new ArrayList<Integer>());
    	}
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int v1 = Integer.parseInt(st.nextToken()) - 1;
    		int v2 = Integer.parseInt(st.nextToken()) - 1;
    		map.get(v1).add(v2);
    		map.get(v2).add(v1);
    	}
    	
    	PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return Integer.compare(o1.val, o2.val);
			}
		});
    	
    	boolean[] visit = new boolean[n];
    	Pair[] dist = new Pair[n];
    	for(int i = 0; i < n; i++) {
    		if(i == 0) {
    			dist[i] = new Pair(i, 0);
    		} else {
    			dist[i] = new Pair(i, 500001);
    		}
    		pq.add(dist[i]);
    	}
    	
    	while(!pq.isEmpty()) {
    		Pair nowIdx = pq.poll();
    		ArrayList<Integer> now = map.get(nowIdx.node);
    		for(int i = 0; i < now.size(); i++) {
    			int next = now.get(i);
    			if(!visit[next] && dist[next].val > dist[nowIdx.node].val + 1) {
    				dist[next].val = dist[nowIdx.node].val + 1;
    				pq.remove(dist[next]);
    				pq.add(dist[next]);
    			}
    		}
    		visit[nowIdx.node] = true;
    	}
    	
    	int maxIdx = 0;
    	int cnt = 0;
    	for(int i = 1; i < n; i++) {
    		if(dist[maxIdx].val < dist[i].val) {
    			maxIdx = i;
    			cnt = 1;
    		} else if(dist[maxIdx].val == dist[i].val) {
    			cnt++;
    		}
    	}
    	System.out.println(maxIdx + 1 + " " + dist[maxIdx].val + " " + cnt);
    }
    
    public static class Pair{
    	int node;
    	int val;
    	public Pair(int node, int val) {
    		this.node = node;
    		this.val = val;
    	}
    }
}

// BFS로 풀어도 무방
