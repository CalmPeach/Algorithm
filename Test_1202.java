import java.io.*;
import java.util.*;
 
public class Test_1202 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken()); // 보석 개수
    	int k = Integer.parseInt(st.nextToken()); // 가방 개수
    	int[][] jewelry = new int[n][2];
    	int[] bagWeight = new int[k];
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		jewelry[i][0] = Integer.parseInt(st.nextToken()); // 무게
    		jewelry[i][1] = Integer.parseInt(st.nextToken()); // 가격
    	}
    	for(int i = 0; i < k; i++)
    		bagWeight[i] = Integer.parseInt(br.readLine());
    	
    	// 보석을 무게순으로 오름차순 정렬
    	Arrays.sort(jewelry, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
    	// 가방을 무게순으로 오름차순 정렬
    	Arrays.sort(bagWeight);
    	
    	// 가격이 높은 보석순으로 내림차순
    	PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o2, o1);
			}
		}); 
    	
    	int idx = 0;
    	long sum = 0L;
    	for(int i = 0; i < k; i++) {
    		while(idx < n && jewelry[idx][0] <= bagWeight[i]) { // 보석의 무게가 가방 무게보다 작거나 같다면
    			pq.add(jewelry[idx][1]);
    			idx++;
    		}
    		if(!pq.isEmpty())
    			sum += pq.poll();
    	}
    	
    	System.out.println(sum);
    }
    
}   