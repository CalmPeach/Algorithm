import java.io.*;
import java.util.*;
 
public class Test_11000 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine()); // 수업 개수
    	int[][] t = new int[n][2];
    	for(int i = 0; i < n; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		t[i][0] = Integer.parseInt(st.nextToken()); // 수업 시작시간
    		t[i][1] = Integer.parseInt(st.nextToken()); // 수업 종료시간
    	}
    	// 수업 시작시간을 기준으로 오름차순, 시작시간이 같다면 종료시간순으로 오름차순
    	Arrays.sort(t, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0])
					return Integer.compare(o1[1], o2[1]);
				return Integer.compare(o1[0], o2[0]);
			} 		
		});
    	
    	PriorityQueue<Integer> q = new PriorityQueue<Integer>();
    	for(int i = 0; i < n; i++) {
    		int end = t[i][1];
    		// 가장 빨리 수업이 종료될 강의실과 비교, 종료시간보다 시작시간이 크면 해당 강의실에서 수업 가능
    		if(!q.isEmpty() && q.peek() <= t[i][0]) 
    			q.poll();
    		q.add(end);
    	}
    	System.out.println(q.size());
    }
 
}   