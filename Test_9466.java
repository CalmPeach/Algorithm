import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Test_9466 {
    
	static boolean[] visit;
	static int[] input;
	static boolean[] ringCheck;
	static int cnt;
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int t = 0; t < tc; t++) {
        	int n = Integer.parseInt(br.readLine());
        	input = new int[n + 1];
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int i = 1; i <= n; i++) {
        		input[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	visit = new boolean[n + 1];
        	ringCheck = new boolean[n + 1];
        	cnt = n;
        	for(int i = 1; i <= n; i++)
        		if(!visit[i])
        			bfs(i);
        	System.out.println(cnt);
        }
    }
    
    public static void bfs(int n) {
    	visit[n] = true;
    	if(!visit[input[n]]) {
    		bfs(input[n]);
    	} else if(visit[input[n]] && !ringCheck[input[n]]) {
    		for(int i = input[n]; i != n; i = input[i]) {
    			cnt--;
    		}
    		cnt--;
    	}
    	ringCheck[n] = true;
    }
}

/*
// ringCheck 생각하기 어려울 때 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {
	static boolean[] visit;
	static int[] wanted;
	static int cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t = 0; t < tc; t++) {
			int n = Integer.parseInt(br.readLine());
			wanted = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				wanted[i] = Integer.parseInt(st.nextToken()) - 1;
			}
			cnt = n;
			visit = new boolean[n];
			for(int i = 0; i < n; i++) {
				if(!visit[i]) {
					dfs(i, new ArrayList<Integer>());
				}
			}
			System.out.println(cnt);
		}
	}
	
	public static void dfs(int num, ArrayList<Integer> link) {
		visit[num] = true;
		link.add(num);
		if(!visit[wanted[num]]) {
			dfs(wanted[num], link);
		} else {
			int i = 0;
			for(i = link.size() - 1; i >= 0; i--) {
				if(wanted[num] == link.get(i)) {
					break;
				}
			}
			cnt -= i != -1? link.size() - i: 0;
		}
	}
}
*/