import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Test_1697 {	
	
	static int[] visit;
	static int[] dir = {-1, 1};
	
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		visit = new int[100001]; // 모든 점은 0 ≤ K ≤ 100,000에 있다
		int N = scan.nextInt();
		int K = scan.nextInt();
		
		System.out.println(solution(N, K));
	}
	
	static int solution(int N, int K) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(N);
		visit[N] = 1;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			// 동생을 찾음
			if(now == K)
				break;
			
			for(int i = 0; i < 3; i++) {
				int next = i < 2? now + dir[i]: now * 2;
				if(next < 0 || next  > 100000 || visit[next] != 0) 
					continue;
				visit[next] = visit[now] + 1;
				q.add(next);
			}
		}
		
		// visit[N]에 1을 넣어주며 시작하여, 답은 -1을 감소시켜주어야한다.
		return visit[K] - 1;
	}
}
