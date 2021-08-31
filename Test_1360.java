import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1360 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] command = new String[n];
		for(int i = 0; i < n; i++) {
			command[i] = br.readLine();
		}
		ArrayList<Integer> idx = new ArrayList<Integer>(); // 명령이 진행된 초
		HashMap<Integer, StringBuilder> hm = new HashMap<Integer, StringBuilder>(); // 초에 따른 텍스트 관리
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(command[i]);
			String c1 = st.nextToken(); // 명령어
			String c2 = st.nextToken(); // 명령어에 사용되는 인자
			int sec = Integer.parseInt(st.nextToken()) - 1; // 수행한 시간
			idx.add(sec);
			if(c1.equals("type")) {
				if(i == 0) {
					hm.put(sec, new StringBuilder(c2)); // 문자를 추가
				} else {
					hm.put(sec, new StringBuilder(hm.get(idx.get(i - 1))).append(c2)); // 현재 글의 가장 뒤에 문자를 추가
				}
			} else {
				if(hm.get(sec - Integer.parseInt(c2)  - 1) != null) { // sec - c2초에 수행된 작업이 있다면
					hm.put(sec, new StringBuilder(hm.get(sec - Integer.parseInt(c2) - 1)));
				} else { // sec - c2초에 수행된 작업이 없다면
					int j; // sec - c2초 이전에 실행된 명령중에 가장 가깝게 실행된 명령 결과를 찾는다
					for(j = 0; j < i; j++)
						if(idx.get(j) > sec - Integer.parseInt(c2) - 1)
							break;
					if(j - 1 >= 0) // 명령을 찾음
						hm.put(sec, new StringBuilder(hm.get(idx.get(j - 1))));
					else // 명령을 찾지 못함
						hm.put(sec, new StringBuilder());
				}
			}
		}
		System.out.println(hm.get(idx.get(idx.size() - 1)));
	}
}