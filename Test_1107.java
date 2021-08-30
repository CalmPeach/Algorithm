import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1107 {
	
	static Set<Integer> btn;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 보고싶은 채널번호
		int m = Integer.parseInt(br.readLine()); // 고장난 버튼 개수
		btn = new HashSet<Integer>(); // 고장난 버튼
		if(m > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++)
				btn.add(Integer.parseInt(st.nextToken()));
		}
		
		int plus = 0;
		int num = n; // 채널 번호
		int answer = abs(100, n);
		while(plus <= answer) {
			int num2 = num - plus;
			if(num2 >= 0) {
				if(check(num2)) {
					if(answer > plus + Integer.toString(num2).length())
						answer = plus + Integer.toString(num2).length();
					break;
				}
			}
			int num1 = num + plus;
			if(check(num1)) {
				if(answer > plus + Integer.toString(num1).length())
					answer = plus + Integer.toString(num1).length();
				break;
			}
			plus++;
		}
		System.out.println(answer);
	}
	
	public static int abs(int n1, int n2) {
		return n1 > n2? n1 - n2: n2 - n1;
	}
	
	public static boolean check(int n) {
		while(n / 10 != 0) {
			int re = n % 10;
			if(btn.contains(re))
				return false;
			n = n / 10;
		}
		if(btn.contains(n))
			return false;
		return true;
	}
}