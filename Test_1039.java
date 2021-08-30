import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_1039 {
	
	static int k;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		bfs(n);
	}
	
	public static void bfs(int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		int step = 0;
		int max = Integer.MIN_VALUE;
		boolean[][] visit = new boolean[k + 1][1000001];
		visit[step][s] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			step++;
			
			if(step > k)
				break;
			
			for(int i = 0; i < size; i++) {
				String now = Integer.toString(q.poll());
				int nowSize = now.length();
				
				char[] nowArr = now.toCharArray(); // 현재 숫자를 charArr로 변경 
				
				for(int j = 0; j < nowSize - 1; j++) { 
					for(int l = j + 1; l < nowSize; l++) {
						if(j == 0 && nowArr[l] == '0') // 0으로 시작된다면
							continue;
						
						// j번 위치와 l번 위치를 변경
						char tmp = nowArr[j];
						nowArr[j] = nowArr[l];
						nowArr[l] = tmp;
						
						int next = Integer.parseInt(new String(nowArr));
						if(visit[step][next] == true) { // 이미 방문했다면
							// 원상복구
							tmp = nowArr[j];
							nowArr[j] = nowArr[l];
							nowArr[l] = tmp;
							continue;
						}
						
						if(step == k) { // k번 변경했다면 
							if(max < next)
								max = next;
						} else { // 아직 더 변경해야한다면
							visit[step][next] = true;
							q.add(next);
						}
						
						// 원상복구
						tmp = nowArr[j];
						nowArr[j] = nowArr[l];
						nowArr[l] = tmp;
					}
				}
			}
		}
		System.out.println(max == Integer.MIN_VALUE? -1: max);
	}
}
