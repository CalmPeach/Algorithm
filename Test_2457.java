import java.io.*;
import java.util.*;
 
public class Test_2457 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine()); // 꽃들의 총 개수 
    	int[][] md = new int[n][4]; // 피는 날짜, 지는 날짜
    	for(int i = 0; i < n; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		md[i][0] = Integer.parseInt(st.nextToken()); // 피는날짜 월
    		md[i][1] = Integer.parseInt(st.nextToken()); // 피는날짜 일
    		md[i][2] = Integer.parseInt(st.nextToken()); // 지는날짜 월
    		md[i][3] = Integer.parseInt(st.nextToken()); // 지는날짜 일
    	}
    	
    	// 피는날짜가 빠른 순으로 피는 날짜가 같다면 지는 날짜가 빠른 순으로 
    	Arrays.sort(md, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0])
					return Integer.compare(o1[1], o2[1]);
				return Integer.compare(o1[0], o2[0]);
			}
		});
    	
    	// 날짜가 느린게 우선 순위가 높다
    	PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				if(o1.m == o2.m)
					return Integer.compare(o2.d, o1.d);
				return Integer.compare(o2.m, o1.m);
			}
		});
    	
    	Pair standard = new Pair(3, 1);
    	int cnt = 0;
    	int i = 0;
    	while(i < n) {
    		if(md[i][0] < standard.m || (md[i][0] == standard.m && md[i][1] <= standard.d)) { // 시작 날짜가 기준 날짜보다 작거나 같다면
    			pq.add(new Pair(md[i][2], md[i][3])); // 지는 날짜 추가
    			i++;
    		} else {
    			if(!pq.isEmpty()) {
	    			standard = pq.poll();
	    			cnt++;
    			} else { // 중간이 연결되어 있지 않는다면 더이상 탐색 필요 x
    				cnt = 0;
    				break;
    			}
    		}
    		
    		if(standard.m > 11) // 기준 날짜가 11월 30일보다 느리면 더이상 탐색 필요 x
    			break;
    	}
    	
    	if(i == n) { // 마지막 날짜에서 기준이 충족될 경우 standard는 갱신되지 않는다 갱신 작업 필요 
    		if(!pq.isEmpty()) 
    			standard = pq.poll();
    		
    		if(standard.m > 11)
    			System.out.println(cnt + 1);
    		else
    			System.out.println(0);
    	}
    	else
    		System.out.println(cnt);
    }
    
    public static class Pair{
    	int m;
    	int d;
    	public Pair(int m, int d) {
    		this.m = m;
    		this.d = d;
    	}
    }
}   