import java.io.*;
import java.util.*;
 
public class Test_1931 {
	
	static int n;
	static int con[][];
	
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	
    	n = scan.nextInt();
    	con = new int[n][2];
    	
    	for(int i = 0; i < n; i++) {
    		con[i][0] = scan.nextInt();
    		con[i][1] = scan.nextInt();
    	}
    	
    	Arrays.sort(con, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1])
					return Integer.compare(o1[0], o2[0]);
				return Integer.compare(o1[1], o2[1]);
			}
		});
    	
    	int n = con[0][1];
    	int cnt = 1;
    	for(int i = 1; i < con.length; i++) {
    		if(n <= con[i][0]) {
    			n = con[i][1];
    			cnt++;
    		}
    	}
    	System.out.println(cnt);
    }
}