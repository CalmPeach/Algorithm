import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_2567 {
	
	static int[][] background;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		background = new int[102][102];
		int[][] color = new int[n][2];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			color[i][0] = Integer.parseInt(st.nextToken());
			color[i][1] = Integer.parseInt(st.nextToken());
			fill(color[i][0], color[i][1]);
		}
		int cnt = 0;
		int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for(int i = 0; i < 102; i++) {
			for(int j = 0; j < 102; j++) {
				if(background[i][j] == 1) { // 색종이가 있는 영역이라면
					for(int k = 0; k < 4; k++) {
						int y = i + xy[k][0];
						int x = j + xy[k][1];
						if(y < 0 || y >= 102 || x < 0 || x >= 102)
							continue;
						if(background[y][x] == 0) // 색종이와 인접한 곳에 색종이가 없다면 
							cnt++;
					}
				}
			}
		}
		System.out.println(cnt);
	}
	
	public static void fill(int y, int x) {
		for(int i = y; i < y + 10; i++)
			for(int j = x; j < x + 10; j++)
				background[i][j] = 1;
	}
	
}