import java.io.*;
import java.util.*;

public class Test_1244{
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine()); // 스위치의 개수
		int[] state = new int[n + 1]; // 각 스위치의 상태
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++)
			state[i] = Integer.parseInt(st.nextToken());
		
		int stuCnt = Integer.parseInt(br.readLine()); // 학생 수
		for(int i = 0; i < stuCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken()); // 1: 남자 2: 여자
			int number = Integer.parseInt(st.nextToken()); // 학생이 받은 수 
			
			if(gender == 1) { // 남학생이라면 받은 수의 배수에 해당하는 스위치 상태 변경
				int idx = number;
				while(idx <= n) {
					state[idx] = state[idx] == 0? 1: 0;
					idx += number;
				}
			} else { // 여학생이라면 받은 수를 기준으로 좌우가 대칭인 가장 넓은 구간을 찾아 스위치 상태 변경
				int s = number - 1;
				int e = number + 1;
				
				while(s > 0 && e <= n && state[s] == state[e]) {
					s--;
					e++;
				}
				
				for(int idx = s + 1; idx <= e - 1; idx++) {
					state[idx] = state[idx] == 0? 1: 0;
				}
			}
		}
		
		// 한줄에 20개씩 출력
		int idx = 1;
		while(idx < n + 1) {
			int range = idx + 20 < n + 1? idx + 20: n + 1;
			for(int i = idx; i < range; i++) {
				System.out.print(state[i] + " ");
			}
			System.out.println();
			idx += 20;
		}
	}
}