import java.io.*;
import java.util.*;
 
public class Test_9576 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int tc = Integer.parseInt(br.readLine());
    	for(int t = 0; t < tc; t++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int m = Integer.parseInt(st.nextToken());
	    	int[] v = new int[n + 1];
	    	int[][] p = new int[m][2];
	    	for(int i = 0; i < m; i++) {
	    		st = new StringTokenizer(br.readLine());
	    		p[i][0] = Integer.parseInt(st.nextToken()); // a
	    		p[i][1] = Integer.parseInt(st.nextToken()); // b
	    	}
	    	
	    	// b가 작은 순으로 b가 같다면 a가 큰 순으로 -> 범위가 가장 작은 학부생부터 먼저 책을 나눠줌
	    	Arrays.sort(p, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[1] == o2[1])
						return Integer.compare(o2[0], o1[0]);
					return Integer.compare(o1[1], o2[1]);
				}
			});
	    	int cnt = 0;
	    	for(int i = 0; i < m; i++) {
	    		for(int j = p[i][0]; j <= p[i][1]; j++)
	    			if(v[j] == 0) { // j 책이 남아있다면
	    				v[j] = 1;
	    				cnt++;
	    				break;
	    			}
	    	}
	    	
	    	System.out.println(cnt);
    	}
    }
}   