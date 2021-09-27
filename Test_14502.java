import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Test_14502 {	
	
	static int max_value;
	static int N, M;
	static int[][] map;
	static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
	static ArrayList<Pair> virus;
	
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		
		map = new int[N][M];
		virus = new ArrayList<Pair>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = scan.nextInt();
				if(map[i][j] == 2)
					virus.add(new Pair(i, j));
			}
		}
		
		max_value = Integer.MIN_VALUE;
		combination(new int[3], 3);
		System.out.println(max_value);
	}
	
	// 벽을 세울 3개의 칸을 뽑는 메서드 
	static void combination(int[] bucket, int k) {
		if(k == 0) { // 3개의 칸을 모두 뽑음
			// bfs를 하기 위해 배열을 복사함
			int[][] copy = new int[N][];
			for(int i = 0; i < N; i++) {
				copy[i] = Arrays.copyOf(map[i], M);
			}
			
			// 벽을 세움
			for(int i = 0; i < bucket.length; i++) {
				int r = bucket[i] / M;
				int c = bucket[i] % M;
				copy[r][c] = 1;
			}
			
			// 바이러스 전파
			bfs(copy);
			
			return;
		}
		
		int lastIndex = bucket.length - k - 1;
		
		for(int i = 0; i < N * M; i++) {
			int r = i / M;
			int c = i % M;
			// 벽이 세워질 수 없는 곳 
			if(map[r][c] != 0)
				continue;
			
			if(bucket.length == k) {
				bucket[0] = i;
				combination(bucket, k - 1);
			} else if(bucket[lastIndex] < i) {
				bucket[lastIndex + 1] = i;
				combination(bucket, k - 1);
			}
		}
	}
	
	// 바이러스 전파 메서드
	static void bfs(int[][] copy) {
		Queue<Pair> q = new LinkedList<Pair>();
		for(int i = 0; i < virus.size(); i++) {
			q.add(virus.get(i));
		}
		
		while(!q.isEmpty()) {
			Pair now = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = now.r + dir[i][0];
				int nc = now.c + dir[i][1];
				if(nr >= 0 && nr < N
					&& nc >= 0 && nc < M
					&& copy[nr][nc] == 0) {
						copy[nr][nc] = 2;
						q.add(new Pair(nr, nc));
				}
			}
		}
		
		// 안전 영역 수 세기
		int total = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				if(copy[i][j] == 0)
					total++;
		
		if(total > max_value)
			max_value = total;
	}
	
	static class Pair{
		int r;
		int c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
