import java.io.*;
import java.util.*;
 
public class Test_17471 {
	
	static int n;
	static int[] p;
	static int[][] map;
	static int diff;
	
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	
    	n = scan.nextInt(); // 구역의 개수
    	p = new int[n + 1]; // 구역의 인구
    	for(int i = 1; i < n + 1; i++)
    		p[i] = scan.nextInt();
    	map = new int[n + 1][n + 1]; // 연결 정보 
    	for(int i = 1; i < n + 1; i++) {
    		int cnt = scan.nextInt(); // 인전합 구역의 개수
    		for(int j = 1; j < cnt + 1; j++) {
    			int link = scan.nextInt();
    			map[i][link] = 1;
    		}
    	}
    	
    	diff = Integer.MAX_VALUE;
    	for(int i = 1; i <= n / 2; i++) {
    		combination(new int [i], i, 0);
    	}
    	System.out.println(diff == Integer.MAX_VALUE? -1: diff);
    }
    
    // 조합으로 구역을 나누기
    public static void combination(int bucket[], int k, int cnt) {
    	if(k == 0) {
    		if(!isLink(bucket)) {
    			return;
    		}
    		
    		// 다른 선거구의 구역들을 구하기
    		int[] team2 = new int[n - bucket.length];
    		int team2Idx = 0, team1Idx = 0, team2Cnt = 0;
    		for(int i = 1; i <= n; i++) {
    			if(team1Idx < bucket.length && bucket[team1Idx] == i) {
    				team1Idx++;
    			} else {
    				team2[team2Idx] = i;
    				team2Cnt += p[i];
    				team2Idx++;
    			}
    		}
    		
    		if(isLink(team2)) {
	    		int r = Math.abs(cnt - team2Cnt);
	    		if(r < diff) {
	    			diff = r;
	    		}
    		}
    		return;
    	}
    	
    	int lastIndex = bucket.length - k - 1;
    	
    	for(int i = 1; i <= n; i++) {
    		if(k == bucket.length) {
    			bucket[0] = i;
    			combination(bucket, k - 1, cnt + p[i]);
    		}else if(bucket[lastIndex] < i) {
    			bucket[lastIndex + 1] = i;
    			combination(bucket, k - 1, cnt + p[i]);
    		}
    	}
    }
    
    // 구역들끼리 연결되어이있는지 확인
    public static boolean isLink(int[] team) {
    	Queue<Integer> q = new LinkedList<Integer>();
		q.add(team[0]);
		boolean[] visit = new boolean[n + 1];
		visit[team[0]] = true;
		boolean result = false;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int j = 0; j < team.length; j++) {
				if(map[now][team[j]] == 1 && !visit[team[j]]) {
					visit[team[j]] = true;
					q.add(team[j]);
				}
			}
			
			// 모든 구역들이 연결되어있는지 확인
			int flag = 0;
			for(int j = 0; j < team.length; j++) {
				if(!visit[team[j]]) {
					flag = 1;
					break;
				}
			}
			
			// 모든 구역들이 연결되어있다면
			if(flag == 0) {
				result = true;
				break;
			}
		}
		
    	return result;
    }
}