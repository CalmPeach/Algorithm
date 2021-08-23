import java.io.*;
import java.util.*;
 
public class Test_1946 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		StringTokenizer str;
    	
		while(tc != 0) {
    		int n = Integer.parseInt(br.readLine()); // 지원자 수 
    		
    		int p[][] = new int[n][2]; // 성적
    		for(int i = 0; i < n; i++) {
    			str=new StringTokenizer(br.readLine());
    			p[i][0] = Integer.parseInt(str.nextToken()); // 서류심사 성적 
    			p[i][1] = Integer.parseInt(str.nextToken()); // 면접 성적
    		}
    		
    		// 서류심사 성적으로 오름차순 정렬
    		Arrays.sort(p, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return Integer.compare(o1[0], o2[0]);
				}  			
			});
    		
    		int cnt = 1;
    		int iRank = p[0][1];
    		for(int i = 1; i < p.length; i++) {
    			if(p[i][1] < iRank) { // 기존 지원자의 성적보다 높으면 
    				iRank = p[i][1];
    				cnt++;
    			}
    		}
    		System.out.println(cnt);
    		tc--;
    	}
    }
    
}