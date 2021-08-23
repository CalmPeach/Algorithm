import java.io.*;
import java.util.*;
 
public class Test_1783 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	
    	int r = 0;
    	if(n == 1) {
    		r = 1;
    	} else if(n == 2) {
    		r = (m + 1) / 2;
    		r = r >= 4? 4: r;
    	} else if(m < 7) {
    		r = m;
    		r = r >= 4? 4: r;
    	} else {
    		r = 4 + m - 7 + 1;
    	}
    	
    	System.out.println(r);
    }
    
}   