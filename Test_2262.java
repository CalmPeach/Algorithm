import java.io.*;
import java.util.*;

public class Test_2262 {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	int n = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	ArrayList<Integer> arr = new ArrayList<Integer>();
    	for(int i = 0; i < n; i++)
    		arr.add(Integer.parseInt(st.nextToken()));
    	
    	int max = n;
    	int sum = 0;
    	for(int i = 0; i < n - 1; i++) {
        	int maxIdx = arr.indexOf(max);
    		if(maxIdx == arr.size() - 1) {
    			sum += Math.abs(arr.get(maxIdx) - arr.get(maxIdx - 1));
    		} else if(maxIdx == 0) {
    			sum += Math.abs(arr.get(maxIdx) - arr.get(maxIdx + 1));
    		} else {
    			int diff1 = Math.abs(arr.get(maxIdx) - arr.get(maxIdx + 1));
    			int diff2 = Math.abs(arr.get(maxIdx) - arr.get(maxIdx - 1));
    			sum = diff1 < diff2? diff1 + sum: diff2 + sum;
    		}
    		arr.remove(maxIdx);
    		max--;
    	}
    	System.out.println(sum);
    }
}