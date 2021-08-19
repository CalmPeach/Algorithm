import java.io.*;
import java.util.*;

public class Test_2947{
	
	static int[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		tree = new int[5]; 
		for(int i = 0; i < 5; i++)
			tree[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j < 5 - i; j++) {
				if(tree[j - 1] > tree[j]) {
					int tmp = tree[j - 1];
					tree[j - 1] = tree[j];
					tree[j] = tmp;
					
					// 위치를 바꿀 때마다 조각의 순서를 출력
					print();
				}
			}
			
			int j = 0;
			for(j = 0; j < 5; j++) {
				if(tree[j] != j + 1)
					break;
			}
			
			// 1, 2, 3, 4, 5가 되면 종료
			if(j == 5)
				break;
		}
	}
	
	public static void print() {
		StringBuilder result = new StringBuilder();
		
		for(int i = 0; i < 5; i++)
			result.append(tree[i] + " ");
		
		System.out.println(result.toString());
	}
}