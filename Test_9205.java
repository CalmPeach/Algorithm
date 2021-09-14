import java.io.*;
import java.util.*;
 
public class Test_9205 {
	
	static Pair[] pairs;
	static int[][] dist;
	static int n;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int tc = Integer.parseInt(br.readLine());
    	for(int t = 0; t < tc; t++) {
    		n = Integer.parseInt(br.readLine()); // 편의점 개수
    		pairs = new Pair[n + 2];
    		for(int i = 0; i < n + 2; i++) {
    			StringTokenizer st = new StringTokenizer(br.readLine());
    			int x = Integer.parseInt(st.nextToken());
    			int y = Integer.parseInt(st.nextToken());
    			pairs[i] = new Pair(x, y);
    		}
    		
    		dist = new int[n + 2][n + 2];
    		for(int i = 0; i < n + 2; i++)
    			for(int j = 0; j < n + 2; j++)
    				dist[i][j] = 100000000;
    		for(int i = 0; i < n + 2; i++) {
    			for(int j = i + 1; j < n + 2; j++) {
    				dist[i][j] = getDist(pairs[i], pairs[j]);
    				dist[j][i] = dist[i][j];
    			}
    		}
    		
    		bfs();
    	}
    }
    
    public static void bfs() {
    	Queue<Location> q = new LinkedList<Location>();
    	q.add(new Location(0, 20));
    	boolean[][] visit = new boolean[n + 2][n + 2];
    	visit[0][0] = true;
    	boolean isEnd = false;
    	
    	while(!q.isEmpty()) {
    		Location lo = q.poll();
    		
    		if(lo.node == n + 1) {
    			isEnd = true;
    		}
    		
    		for(int i = 1; i < n + 2; i++) {
    			if(lo.node == i)
    				continue;
    			int d = dist[lo.node][i];
    			int drink = d / 50;
    			drink = d % 50 != 0? drink + 1: drink;
    			if(drink <= lo.cnt) {
    				if(!visit[lo.node][i]) {
    					if(i != n + 1) {
    						q.add(new Location(i, 20));
    					} else {
    						q.add(new Location(i, lo.cnt - drink));
    					}
    					visit[lo.node][i] = true;
    				}
    			}
    		}
    	}
    	
    	System.out.println(isEnd == true? "happy": "sad");
    }
    
    public static int getDist(Pair p1, Pair p2) {
    	int diff1 = abs(p1.x, p2.x);
    	int diff2 = abs(p1.y, p2.y);
    	return diff1 + diff2;
    }
    
    public static int abs(int n1, int n2) {
    	return n1 > n2? n1 - n2: n2 - n1;
    }
    
    public static class Location{
    	int node;
    	int cnt;
    	public Location(int node, int cnt) {
    		this.node = node;
    		this.cnt = cnt;
    	}
    }
    
    public static class Pair{
    	int x;
    	int y;
    	public Pair(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
}

/*
import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int[][] dist;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  	
    	int tc = Integer.parseInt(br.readLine());
    	for(int t = 0; t < tc; t++) {
    		n = Integer.parseInt(br.readLine());
    		int[][] location = new int[n + 2][2];
    		for(int i = 0; i < n + 2; i++) {
    			StringTokenizer st = new StringTokenizer(br.readLine());
    			location[i][0] = Integer.parseInt(st.nextToken());
    			location[i][1] = Integer.parseInt(st.nextToken());
    		}
    		
    		// 거리
    		dist = new int[n + 2][n + 2];
    		for(int i = 0; i < n + 2; i++) {
    			for(int j = 0; j < n + 2; j++) {
    				if(i == j)
    					continue;
    				dist[i][j] = abs(location[i][0], location[j][0]) + abs(location[i][1], location[j][1]);
    			}
    		}
    		
    		bfs();
    	}
    }
    
    public static void bfs() {
    	Queue<Pair> q = new LinkedList<Pair>();
    	q.add(new Pair(0, 20));
    	boolean[] visit = new boolean[n + 2];
    	visit[0] = true;
    	boolean isEnd = false;
    	
    	while(!q.isEmpty()) {
    		Pair now = q.poll();
    		
    		if(now.place == n + 1) {
    			isEnd = true;
    			break;
    		}
    		
    		for(int i = 0; i < n + 2; i++) {
    			if(now.place == i)
    				continue;
    			// 가야할 거리 dist[now.place][i]
    			int re = dist[now.place][i] % 50 != 0? 1: 0;
    			// 필요한 맥주 개수 
    			int need = dist[now.place][i] / 50 + re; 
    			if(now.cnt >= need && !visit[i]) {
    				q.add(new Pair(i, 20));
    				visit[i] = true;
    			}
    		}
    	}
    	
    	if(isEnd)
    		System.out.println("happy");
    	else
    		System.out.println("sad");
    }
    
    public static int abs(int n1, int n2) {
    	return n1 > n2? n1 - n2: n2 - n1;
    }
    
    public static class Pair{
    	int place;
    	int cnt;
    	public Pair(int place, int cnt) {
    		this.place = place;
    		this.cnt = cnt;
    	}
    }
}

*/