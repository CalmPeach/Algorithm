import java.io.*;
import java.util.*;
import java.util.Spliterator.OfInt;

public class Test_1461 {
	
	static int n;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	ArrayList<Integer> nums1 = new ArrayList<Integer>();
    	ArrayList<Integer> nums2 = new ArrayList<Integer>();
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		int num = Integer.parseInt(st.nextToken());
    		if(num < 0)
    			nums1.add(num);
    		else
    			nums2.add(num);
    	}
    	
    	nums1.sort(Comparator.naturalOrder());
    	nums2.sort(Comparator.reverseOrder());
    	int sum = 0;
    	// 가장 먼 위치에 있는 책을 놔둔 후 에는 다시 0으로 돌아올 필요 없다. 가장 먼 위치애 있는 책까지의 걸음 수를 미리 감소시킴으로써 왕복으로 저장되는 것을 편도로 저장되게 한다.
    	if(nums1.size() == 0) {
    		sum -= nums2.get(0);
    	} else if(nums2.size() == 0) {
    		sum -= nums1.get(0) * -1;
    	} else {
    		sum -= nums1.get(0) * -1 < nums2.get(0)? nums2.get(0): nums1.get(0) * -1;
    	}
    
    	for(int i = 0; i < nums1.size();) {
    		int j = i + 1;
	    	for(; j < i + m; j++) {
	    		if(j == n) {
	    			break;		
	    		}
	    	}
    		sum += nums1.get(i) * -1  * 2;  		
    		i = j;
    	}
    	for(int i = 0; i < nums2.size();) {
    		int j = i + 1;
	    	for(; j < i + m; j++) {
	    		if(j == n) {
	    			break;		
	    		}
	    	}
    		sum += nums2.get(i) * 2;  		
    		i = j;
    	}
    	System.out.println(sum);
    }
}