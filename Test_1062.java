import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
 
public class Test_1062 {
	
	static String[] inputs;
	static int max;
	static int n;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		//단어에 포함된 알파벳을 구하기
		Set<Character> s = new HashSet<Character>();
		inputs = new String[n];
		for(int i = 0; i < n; i++) {
			inputs[i] = br.readLine();
			for(int j = 0; j < inputs[i].length(); j++)
				if(!s.contains(inputs[i].charAt(j))) {
					s.add(inputs[i].charAt(j));
				}
		}
		
		char[] ch = {'a', 'n', 't', 'i', 'c'};
		for(int i = 0; i < 5; i++)
			if(s.contains(ch[i]))
				s.remove(ch[i]);
		ch = new char[s.size()]; // a, n, t, i, c를 제외하고 단어에 포함된 알파벳들을 담는 배열
		int idx = 0;
		for(char c : s) {
			ch[idx++] = c;
		}
		
		max = Integer.MIN_VALUE;
		if(k < 5) // a, n, t, i, c는 모든 단어에 포함되어 있으므로 k < 5라면 어떤 단어도 가르칠 수 없다.
			System.out.println(0);
		else if(k >= ch.length + 5) // 단어에 포함된 모든 알파벳의 수보다 k가 크다면 
			System.out.println(n);
		else { // 조합을 이용해 알파벳 선택
			solution(ch, new int[k - 5], k - 5);
			System.out.println(max == Integer.MIN_VALUE? 0: max);
		}
	}
	
	public static void solution(char[] items, int[] bucket, int k) {
		if(k == 0) {
			Set<Character> s = new HashSet<Character>();
			char[] d = {'a', 'n', 't', 'i', 'c'};
			for(int i = 0; i < 5; i++)
				s.add(d[i]);
			for(int i = 0; i < bucket.length; i++)
				s.add(items[bucket[i]]);
			
			int cnt = 0;
			for(int i = 0; i < n; i++) {
				boolean flag = true;
				for(int j = 4; j < inputs[i].length() - 4; j++)
					if(!s.contains(inputs[i].charAt(j))) {
						flag = false;
						break;
					}
				if(flag) // 선택된 알파벳으로만 단어가 이루어져있다면 
					cnt++;
			}
			if(cnt > max)
				max = cnt;
			return;
		}
		
		int lastIndex = bucket.length - k - 1;
		for(int i = 0; i < items.length; i++) {
			if(bucket.length == k) {
				bucket[0] = i;
				solution(items, bucket, k - 1);
			} else if(bucket[lastIndex] < i) {
				bucket[lastIndex + 1] = i;
				solution(items, bucket, k - 1);
			}
		}
	}
}
