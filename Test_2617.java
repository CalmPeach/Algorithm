import java.io.*;
import java.util.*;
 
public class Test_2617 {
	
	static int lcnt;
	static int hcnt;
	static boolean[] visit;
	static ArrayList<ArrayList<Integer>> light;
	static ArrayList<ArrayList<Integer>> heavy;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	light = new ArrayList<ArrayList<Integer>>();
    	heavy = new ArrayList<ArrayList<Integer>>();
    	
    	for(int i = 0; i < n; i++) {
    		light.add(new ArrayList<Integer>());
    		heavy.add(new ArrayList<Integer>());
    	}
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int v1 = Integer.parseInt(st.nextToken()) - 1;
    		int v2 = Integer.parseInt(st.nextToken()) - 1;
    		light.get(v1).add(v2);
    		heavy.get(v2).add(v1);
    	}
    	
    	int cnt = 0;
    	int middle = (n + 1) / 2;
    	for(int i = 0; i < n; i++) {
    		visit = new boolean[n];
    		lcnt = 0;
    		hcnt = 0;
    		getLightCnt(i);
    		getHeavyCnt(i);
    		if(lcnt >= middle || hcnt >= middle)
    			cnt++;
    	}
    	System.out.println(cnt);
    }
    
    public static void getLightCnt(int n) {
    	visit[n] = true;
    	ArrayList<Integer> link = light.get(n);
    	for(int i = 0; i < link.size(); i++) {
    		int next = link.get(i);
    		if(visit[next] == false) {
    			lcnt++;
    			getLightCnt(next);
    		}
    	}
    }
    
    public static void getHeavyCnt(int n) {
    	visit[n] = true;
    	ArrayList<Integer> link = heavy.get(n);
    	for(int i = 0; i < link.size(); i++) {
    		int next = link.get(i);
    		if(visit[next] == false) {
    			hcnt++;
    			getHeavyCnt(next);
    		}
    	}
    }
}

/*
플로이드 와샬 해결법
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Answer {
	static int cnt;
	static int rcnt;
	static int[][] d;
	static int[][] r;
	static int n;
	static int[][] pd;
	static int[][] pr;
	
	public static void clear(){
	    cnt = 0;
	    rcnt = 0;
	    for(int i=0; i<n; i++){
	        for(int j=0; j<n; j++){
	            d[i][j] = 101;
	            r[i][j] = 101;
	            if(pd[i][j] == 1) d[i][j] = 1;
	            if(pr[i][j] == 1) r[i][j] = 1;
	        }
	    }
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	int baseNum = (n+1)/2;
    	pd = new int[n][n];
    	pr = new int[n][n];
    	d = new int[n][n];
    	r = new int[n][n];
    	
    	for(int i=0; i<m; i++){
    		st = new StringTokenizer(br.readLine());
    		int v1 = Integer.parseInt(st.nextToken()) - 1;
    		int v2 = Integer.parseInt(st.nextToken()) - 1;
    		pd[v1][v2] = 1;
    	    pr[v2][v1] = 1;
    	}
    	
    	clear();
    	for(int k=0; k<n; k++){
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
	                    
					if(d[i][j] > d[i][k] + d[k][j]){
						d[i][j] = d[i][k] + d[k][j];
	                }    
	                if(r[i][j] > r[i][k] + r[k][j]){
	                    r[i][j] = r[i][k] + r[k][j];
	                }
				}
			}
		}
    	
    	int result = 0;
    	for(int node=0; node<n; node++){   	        
//    		clear();
    		cnt = 0;
    	    rcnt = 0;
    	    
    	    for(int j=0; j<n; j++){
    	       if(d[node][j] != 101) cnt++;
    	       if(r[node][j] != 101) rcnt++;
    	    }
    	        
    	    if(cnt >= baseNum || rcnt >= baseNum){
    	          result++;
    	    }
    	}
     System.out.println(result);	    
    }
}
 */