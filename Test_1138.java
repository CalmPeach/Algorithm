import java.io.*;
import java.util.*;
 
public class Test_1138 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[] cnt = new int[n]; // 자기보다 큰 사람이 왼쪽에 몇명 있는지 
    	StringTokenizer s = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++)
    		cnt[i] = Integer.parseInt(s.nextToken());;
    	
    	int[] ans = new int[n]; 
    	int[] zeroCnt = new int[n]; // 빈칸 개수 
    	for(int i = 0; i < n; i++) 
    		zeroCnt[i] = i;
    	
    	for(int i = 0; i < n; i++) {
    		int tmp = cnt[i]; // tmp 위치에 서고 싶다.
    		while(zeroCnt[tmp] != cnt[i] || ans[tmp] != 0) { // 빈칸의 개수와 맞지 않거나 답변이 채워져 있다면 
    			tmp++;
    		}
    		ans[tmp] = i + 1;
    		
    		// tmp 위치 이후에 빈칸 개수 감소 
    		for(int j = tmp + 1; j < n; j++)
    			zeroCnt[j] -= 1;
    	}
    	
    	for(int i = 0; i < n; i++) {
    		System.out.print(ans[i] + " ");
    	
    	}
    }
    
    
}   