import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_9372 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 국가의 수
			int m = Integer.parseInt(st.nextToken()); // 비행기의 수
			int[][] schedule = new int[m][2];
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				schedule[i][0] = Integer.parseInt(st.nextToken()) - 1;
				schedule[i][1] = Integer.parseInt(st.nextToken()) - 1;
			}
			System.out.println(n - 1);
		}
	}
}
