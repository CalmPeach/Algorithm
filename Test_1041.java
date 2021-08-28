import java.io.*;
import java.util.*;
 
public class Test_1041 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	long n = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int[] dice = new int[6];
    	int minValue = Integer.MAX_VALUE;
    	for(int i = 0; i < 6; i++) {
    		dice[i] = Integer.parseInt(st.nextToken());
    		if(minValue > dice[i])
    			minValue = dice[i];
    	}
    	
    	long r = 0L;
    	if(n == 1) { // n이 1인 경우 가장 큰 수가 쓰여 있는 면을 제외하고 모든 면을 선택
    		Arrays.sort(dice);
    		for(int i = 0; i < 5; i++) {
    			r += dice[i];
    		}
    	} else { 
    		long minDouble = findMinDouble(dice); // 가장 합이 작은 2개의 조합
    		long minThree = findMinThree(dice); // 가장 합이 작은 3개의 조합
    		r += (minDouble * 4 + minValue * (n - 2) * 4) * (n - 1); // 제일 위에 판을 제외한 모든 판
    		r += minThree * 4 + minDouble * (n - 2) * 4 + minValue * (n * n - (n * 4 - 4)); // 제일 위에 판
    	}
    	System.out.println(r);
    }
    
    public static int findMinDouble(int d[]) {
    	int minValue = Integer.MAX_VALUE;
    	for(int i = 0; i < 5; i++) {
    		for(int j = i + 1; j < 6; j++) {
    			if((5 - i) == j)
    				continue;
    			int sum = d[i] + d[j];
    			if(minValue > sum)
    				minValue = sum;
    		}
    	}
    	return minValue;
    }
    
    public static int findMinThree(int d[]) {
    	int minValue = Integer.MAX_VALUE;
    	for(int i = 0; i < 5; i++) {
    		for(int j = i + 1; j < 6; j++) {
    			if((5 - i) == j)
    				continue;
    			for(int k = j + 1; k < 6; k++) {
    				if((5 - i) == k || (5 - j) == k)
        				continue;
    				int sum = d[i] + d[j] + d[k];
        			if(minValue > sum)
        				minValue = sum;
    			}
    		}
    	}
    	return minValue;
    }
 
}   