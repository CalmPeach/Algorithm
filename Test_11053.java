import java.io.*;
import java.util.*;
 
public class Test_11053 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	int[] arr = new int[n];
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++)
    		arr[i] = Integer.parseInt(st.nextToken());
    	
    	int[] d = new int[n];
    	for(int i = 0; i < n; i++) {
    		d[i] = 1;
    		for(int j = 0; j < i; j++) {
    			if(arr[j] < arr[i] && d[i] <= d[j]) // 수열 a = {10, 20, 10, 30} i = 1, j는 0인 경우  (10 < 20 && d[0] <= d[1]) d[0] = 1, d[1] = 1
    				d[i] = d[j] + 1;
    		}
    	}
    	
    	int max = 0;
    	for(int i = 0; i < n; i++) {
    		if(max < d[i])
    			max = d[i];
    	}
    	System.out.println(max);
    }
}   