import java.io.*;
import java.util.*;

public class Test_4889 {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int step = 1;
    	loop:
    	while(true) {
    		String input = br.readLine();
    		Stack<Character> st = new Stack<Character>();
    		int cnt = 0;
    		for(int i = 0; i < input.length(); i++) {
    			char ch = input.charAt(i);
    			if(ch == '-')
    				break loop;
    			if(ch == '}') {
    				if(st.isEmpty()) {
    					cnt++;
    				} else {
    					st.pop();
    				}
    			} else {
    				st.push(ch);
    			}
    		}
    		System.out.println(step + ". " + (cnt / 2 + cnt % 2 + st.size() / 2 + st.size() % 2));
    		step++;
    	}
    }
}