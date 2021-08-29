import java.io.*;
import java.util.*;
 
public class Test_1201 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken()); // 1-N까지 수
    	int m = Integer.parseInt(st.nextToken()); // 가장 긴 증가하는 부분 수열의 길이
    	int k = Integer.parseInt(st.nextToken()); // 가장 긴 감소하는 부분 수열의 길이 
    	
    	if(n >= m + k - 1 && n <= m * k) {
    		int[] arr = new int[n]; // 1-N까지의 수가 담긴 배열
    		for(int i = 0; i < n; i++) {
    			arr[i] = i + 1;
    		}
    		
    		ArrayList<Integer> division = new ArrayList<Integer>();
    		division.add(0);
    		division.add(k);
    		
    		n -= k;
    		m -= 1;
    		int groupSize = (m == 0)? 1 : n / m; // 기본 사이즈 
            int r = (m == 0) ? 0: n % m; // 추가될 수 있는 사이즈 
            for (int i=0; i<m; i++) {
                int v = division.get(division.size() - 1) + groupSize;
                if (r > 0) { // while()로 할 경우 r로 더해진 크기가 k를 넘어가서는 안된다
                    v++;
                    r -= 1;
                }
                division.add(v); // 다음 구분선을 추가 
            }
    		
    		for(int i = 0; i < division.size() - 1; i++) {
    			int begin = division.get(i);
    			int end = division.get(i + 1) - 1;
    			while(begin < end) { // 거꾸로 
    				int temp = arr[begin];
    				arr[begin] = arr[end];
    				arr[end] = temp;
    				begin++;
    				end--;
    			}
    		}
    		
    		for (int i=0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
    	} else {
    		System.out.println(-1);
    	}
    }
}   