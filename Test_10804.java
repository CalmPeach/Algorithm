import java.io.*;
import java.util.*;

public class Test_10804{
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int[] card = new int[21]; // 오름차순으로 놓인 카드 
		for(int i = 1; i <= 20; i++)
			card[i] = i;
		
		for(int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			// 입력으로 주어진 구간에 해당하는 부분을 순서대로 뒤집기
			for(int j = s; j <= (s + e) / 2; j++) {
				swap(card, j, (s + e) - j);
			}
		}
		
		for(int i = 1; i <= 20; i++) {
			System.out.print(card[i] + " ");
		}
		System.out.println();
	}
	
	// 자리를 바꾸는 메서드 
	public static void swap(int[] card, int idx1, int idx2) {
		int tmp = card[idx1];
		card[idx1] = card[idx2];
		card[idx2] = tmp;
	}
}