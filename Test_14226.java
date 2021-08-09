import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Test_14226 {	
	
	
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int s = scan.nextInt();
		
		System.out.println(sol(s));
	}
	
	static int sol(int s) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(0, 1));
		int[][] visit = new int[1001][1001]; // [클립보드 이모티콘 수][화면 이모티콘 수]
		visit[0][1] = 1;
		int ans = -1;
		
		while(!q.isEmpty()) {
			Pair now = q.poll();
			
			if(now.cnt == s) {
				ans = visit[now.clipedCnt][now.cnt] - 1; // 1부터 시작했으므로 답은 -1
				break;
			}
			
			for(int i = 0; i < 3; i++) {
				int nextClipedCnt = now.clipedCnt;
				int nextCnt = now.cnt;
				
				switch(i) {
					case 0:
						nextClipedCnt = now.cnt;
						break;
					case 1:
						nextCnt += now.clipedCnt;
						break;
					case 2:
						if(nextCnt == 0)
							continue;
						nextCnt -= 1;
						break;
				}
				
				if(nextClipedCnt >= 1001 || nextCnt >= 1001 || visit[nextClipedCnt][nextCnt] != 0) 
					continue;
				visit[nextClipedCnt][nextCnt] = visit[now.clipedCnt][now.cnt] + 1;
				q.add(new Pair(nextClipedCnt, nextCnt));
			}
		}
		return ans;
	}
	
	public static class Pair{
		int clipedCnt; // 클립보드에 있는 이모티콘 수
		int cnt; // 화면에 있는 이모티콘 수
		public Pair(int clipedCnt, int cnt) {
			this.clipedCnt = clipedCnt;
			this.cnt = cnt;
		}
	}
}
