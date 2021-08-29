import java.io.*;
import java.util.*;
 
public class Test_2212 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine()); // 센서의 개수
    	int k = Integer.parseInt(br.readLine()); // 집중국의 개수
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int[] sensor = new int[n];
    	for(int i = 0; i < n; i++)
    		sensor[i] = Integer.parseInt(st.nextToken());
    	Arrays.sort(sensor);
    	
    	long r = 0L;
    	if(k < n) {
	    	int[] diff = new int[n - 1];
	    	for(int i = 0; i < n - 1; i++) {
	    		diff[i] = sensor[i + 1] - sensor[i]; 
	    	}
	    	Arrays.sort(diff);
	    	
	    	for(int i = 0; i < n - k; i++) {
	    		r += diff[i];
	    	}
    	} 
    	System.out.println(r);
    }
 
}   