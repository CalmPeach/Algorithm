import java.io.*;
import java.util.*;

public class Test_2660 {
	
	static int n;
	static ArrayList<ArrayList<Integer>> map;
	 
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	n = Integer.parseInt(br.readLine());
    	map = new ArrayList<ArrayList<Integer>>();
    	for(int i = 0; i < n; i++)
    		map.add(new ArrayList<Integer>());
    	while(true) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int v1 = Integer.parseInt(st.nextToken()) - 1;
    		int v2 = Integer.parseInt(st.nextToken()) - 1;
    		if(v1 == -2 && v2 == -2)
    			break;
    		map.get(v1).add(v2);
    		map.get(v2).add(v1);
    	}
    	bfs();
    }
    
    public static void bfs() {
    	int[][] visit = new int[n][n];
    	int max = Integer.MAX_VALUE;
    	int[] total = new int[n];
    	int cnt = 1;
    	
    	for(int i = 0; i < n; i++) {
    		Queue<Integer> q = new LinkedList<Integer>();
    		q.add(i);
    		boolean flag = false;
    		
    		while(!q.isEmpty()) {
    			int nowIdx = q.poll();
    			ArrayList<Integer> link = map.get(nowIdx);
    			for(int j = 0; j < link.size(); j++) {
    				int next = link.get(j);
    				if(next != i && visit[i][next] == 0) {
    					visit[i][next] = visit[i][nowIdx] + 1;
    					q.add(next);
    				}
    			}
    			
    			for(int j = 0; j < n; j++) {
    				if(i == j)
    					continue;
    				if(visit[i][j] == 0) {
    					flag = true;
    					break;
    				}
    			}
    			
    			// i번 회원이 모든 회원과 연결되어있다면 
    			if(!flag)
    				break;
 
    		}
    		
    		int sum = 0;
    		for(int j = 0; j < n; j++) {
    			if(sum < visit[i][j])
    				sum = visit[i][j];
    		}
    		total[i] = sum;
    		if(max > total[i]) {
    			max = total[i];
    			cnt = 1;
    		}
    		else if(max == total[i])
    			cnt++;
    	}
    	
    	System.out.println(max + " " + cnt);
    	for(int i = 0; i < n; i++)
    		if(max == total[i])
    			System.out.print(i + 1 + " ");
    	System.out.println();
    }
    
}