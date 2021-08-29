import java.io.*;
import java.util.*;
 
public class Test_11501 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int tc = Integer.parseInt(br.readLine());
    	for(int t = 0; t < tc; t++) {
    		int n = Integer.parseInt(br.readLine());
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int[] price = new int[n];
    		for(int i = 0; i < n; i++) {
    			price[i] = Integer.parseInt(st.nextToken());
    		}
    		
    		int max = price[n - 1];
    		long sum = 0L;
    		for(int i = n - 2; i >= 0; i--) {
    			if(max < price[i])
    				max = price[i];
    			else if(max > price[i])
    				sum += max - price[i];
    		}
    		System.out.println(sum);
    	}
    }
}   