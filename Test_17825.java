import java.io.*;
import java.util.*;

// https://buddev.tistory.com/38
public class Test_17825{
	
	static int dices[];
	static Node map[];
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		dices = new int[10];
		for(int i = 0; i < 10; i++) {
			dices[i] = Integer.parseInt(st.nextToken());
		}
		
		map = new Node[43];
		for(int i = 0; i <= 40; i += 2) {
			map[i] = new Node(i, i + 2);
		}
		
		init();
		
		max = Integer.MIN_VALUE;
		solution(new int[10], 10);
		System.out.println(max);
	}
	
	public static void init() {
		map[10].isBlue = true;
		map[20].isBlue = true;
		map[30].isBlue = true;
		map[10].nextBlue = 11;
		map[20].nextBlue = 17;
		map[30].nextBlue = 31;
		
		map[11] = new Node(13, 13);
		map[13] = new Node(16, 15);
		map[15] = new Node(19, 25);
		map[25] = new Node(25, 37);
		map[31] = new Node(28, 33);
		map[33] = new Node(27, 35);
		map[35] = new Node(26, 25);
		map[17] = new Node(22, 19);
		map[19] = new Node(24, 25);
		map[37] = new Node(30, 39);
		map[39] = new Node(35, 40);
		map[42] = new Node(0, 42);
	}
	
	// 중복 순열: 말들이 선택되는 순서
	public static void solution(int bucket[], int k) {
		if(k == 0) {
			int[] horse = new int[4]; // 현재 말들의 위치
			boolean[] visit = new boolean[43]; // 현재 말이 있는지 여부
			
			int score = 0;
			for(int i = 0; i < bucket.length; i++) {
				int r = move(bucket[i], dices[i], horse, visit);
				if(r == -1) return;
				horse[bucket[i]] = r;
				score += map[r].score;
			}
			
			if(max < score) 
				max = score;
			
			return;
		}
		
		int lastIndex = bucket.length - k - 1;
		
		for(int i = 0; i < 4; i++) {
			bucket[lastIndex + 1] = i;
			solution(bucket, k - 1);
		}
	}
	
	// h번 말이 d 만큼 이동
	public static int move(int h, int d, int[] horse, boolean[] visit) {
		int temp = horse[h];
		for(int i = 0; i < d; i++) {
			if(i == 0 && map[temp].isBlue) {
				temp = map[temp].nextBlue;
				continue;
			}
			temp = map[temp].nextRed;
		}
		
		if(temp <= 40 && visit[temp]) { // 도착칸이 아닌데 다른 말이 있는 경우 이동 불가
			return -1;
		} else {
			visit[horse[h]] = false;
			visit[temp] = true;
			return temp;
		}
	}
	
	public static class Node{
		int score;
		int nextBlue;
		int nextRed;
		boolean isBlue = false;
		
		public Node(int score, int next_red) {
			this.score = score;
			this.nextRed = next_red;
		}
	}
}