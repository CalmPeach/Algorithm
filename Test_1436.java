import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1436 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int f = 0; // 앞 숫자
		int b = 666; // 뒤 숫자
		int num = 666; // 시작
		int i = 1;
		while(i <= n) {
			int cnt = getCnt(f);
			if(cnt == 0) {
				b = 666;
			} else if(cnt == 1) {
				b = 660;
			} else if(cnt == 2) {
				b = 600;
			} else {
				b = 0;
			}
			
			if(cnt != 0) {
				int k = 0;
				if(cnt == 1)
					k = 9;
				else if(cnt == 2)
					k = 99;
				else
					k = 999;
				if(i + k >= n) { // 건너뛰기 중간에 답이 존재한다면
					num = f * 1000 + b + (n - i);
				}
				i += k; // 건너뛰기
			} else {
				num = f * 1000 + b;
			}
			i++;
			f++;
		}
		System.out.println(num);
	}
	
	public static int getCnt(int n) {
		int lastCnt = 0; // 끝에 6이 몇개 있는가
		int cnt = 0; // 6이 세개가 연속으로 있는가
		
		while(n != 0) {
			int re = n % 10;
			n /= 10;
			if(re == 6)
				lastCnt++;
			else
				break;
			
			if(lastCnt == 3)
				break;
		}
		
		if(lastCnt == 3)
			return 3;
		
		while(n != 0) {
			int re = n % 10;
			n /= 10;
			if(re == 6) {
				cnt++;
				if(cnt == 3)
					break;
			} else {
				cnt = 0;
			}
		}
		
		if(cnt == 3)
			return 3;
		else
			return lastCnt;
	}
}