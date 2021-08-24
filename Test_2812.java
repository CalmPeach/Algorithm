import java.io.*;
import java.util.*;
 
public class Test_2812 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int k = Integer.parseInt(st.nextToken());
    	String s = br.readLine();
    	
    	Deque<Integer> dq = new ArrayDeque<Integer>();
    	for(int i = 0; i < n; i++) {
    		while(k > 0 && !dq.isEmpty() && dq.getLast() < s.charAt(i) - '0') { // 나보다 작은 수가 앞에 있는 경우
    			dq.removeLast();
    			k--;
    		}
    		dq.add(s.charAt(i) - '0');
    	}
    	
    	// 못지우고 남은 k개를 제외하고 출력 
    	while(dq.size() > k) 
    		System.out.print(dq.removeFirst());
    }
}   