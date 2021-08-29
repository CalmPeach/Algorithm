import java.io.*;
import java.util.*;
 
public class Test_8980 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken()); // 마을 수
    	int c = Integer.parseInt(st.nextToken()); // 트럭의 용량
    	int m = Integer.parseInt(br.readLine()); // 보내는 박스 정부의 개수
    	int[][] box = new int[m][3]; // 보내는 마을번호, 받는 마을 번호, 보내는 박스 개수
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		box[i][0] = Integer.parseInt(st.nextToken());
    		box[i][1] = Integer.parseInt(st.nextToken());
    		box[i][2] = Integer.parseInt(st.nextToken());
    	}
    	
    	// 받는 마을이 빠른 순으로 받는 마을이 같다면 보내는 마을이 느린 순으로
    	Arrays.sort(box, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1])
					return Integer.compare(o2[0], o1[0]);
				return Integer.compare(o1[1], o2[1]);
			}
		});
    	
    	int[] q = new int[n - 1]; // 마을에서 보낼 수 있는 용량
    	Arrays.fill(q, c);
    	int sum = 0; // 배송할 수 있는 최대 박스 수 
    	
    	for(int i = 0; i < m; i++) {
    		boolean flag = true;
    		int minValue = box[i][2]; // 해당 마을에서 보낼 수 있는 최대 용량
    		for(int j = box[i][0] - 1; j < box[i][1] - 1; j++) {
    			if(q[j] == 0) { // 보낼 수 있는 용량이 0개이다
    				flag = false;
    				break;
    			}
    			minValue = q[j] < minValue? q[j]: minValue;
    		}
    		
    		if(flag) { // 보낼 수 있는 박스가 있다면 
    			for(int j = box[i][0] - 1; j < box[i][1] - 1; j++) {
        			q[j] -= minValue;
        		}
    			sum += minValue;
    		}
    	}
    	System.out.println(sum);
    }
 
}   