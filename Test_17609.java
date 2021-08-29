import java.io.*;
import java.util.*;

public class Test_17609 {

	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 문자열의 개수
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			ans = 2;
			dfs(input, 0, input.length() - 1, false);
			System.out.println(ans);
		}
	}
	
	public static void dfs(String str, int s, int e, boolean isPass) {
		if(ans != 2) // 답이 이미 나왔다면 
			return;
		
		if(s >= e) {
			ans = isPass? 1: 0;
			return;
		}
		
		if(str.charAt(s) == str.charAt(e)) {
			dfs(str, s + 1, e - 1, isPass);
		} else { // 두 문자가 다르다면
			if(!isPass) { // 하나의 문자를 삭제한적이 없다면 삭제 가능
				dfs(str, s + 1, e, true);
				dfs(str, s, e - 1, true);
			}
		}
	}
}