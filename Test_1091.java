import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1091 {
	
	static int n;
	static int[] p;
	static int[] s;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		// 각 플레이어가 가져야 하는 카드
		p = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		// 카드 섞는 방법
		s = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++)
			s[i] = Integer.parseInt(st.nextToken());
		
		System.out.println(solution());
	}
	
	public static int solution() {
		int cnt = 0; // 카드를 섞은 수 
		int[] before = new int[n];
		for(int i = 0; i < n; i++)
			before[i] = i;
		
		if(isCheck(before)) {
			return cnt;
		}
		
		while(true) {
			int[] changed = new int[n];
			for(int i = 0; i < n; i++) {
				changed[s[i]] = before[i];
			}
			before = changed;
			cnt++;
			
			if(isCheck(before)) { // 각각의 카드가 정해져있는 플레이어에게 갔다면
				break;
			}
			
			if(isFirst(before)) { // 시작했던 카드 순서와 같다면 
				cnt = -1;
				break;
			}
		}
		return cnt;
	}
	
	public static boolean isCheck(int[] before) {
		for(int i = 0; i < n; i++)
			if(i % 3 != p[before[i]])
				return false;
		return true;
	}
	
	public static boolean isFirst(int[] before) {
		for(int i = 0; i < n; i++)
			if(before[i] != i)
				return false;
		return true;
	}
}