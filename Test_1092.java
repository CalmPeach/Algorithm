import java.io.*;
import java.util.*;

public class Test_1092 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 크레인 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] crain = new int[n]; // 크레인 무게
		for (int i = 0; i < n; i++)
			crain[i] = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine()); // 박스 수
		st = new StringTokenizer(br.readLine());
		int[] box = new int[m]; // 박스 무게
		for (int i = 0; i < m; i++)
			box[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(crain); // 크레인 무게순으로 오름차순
		Arrays.sort(box); // 박스 무게순으로 오름차순
		if (crain[n - 1] < box[m - 1]) // n이 0이거나 무게제한이 가장 크레인보다 박스 무게가 큰 경우
			System.out.println(-1);
		else {
			int maxCnt = m / n; // 최대 넣을 수 있는 박스 수 
			int mCpy = m; // 박스 수
			int nCpy = n; // 크레인 수 
			int idx = 0; // 크레인 인덱스
			int[] spare = new int[n]; // 크레인에 넣을 수 있는 박스 수
			for (int i = 0; i < m; i++) {
				if (crain[idx] >= box[i]) { // 크레인 무게보다 박스 무게가 작다면 
					mCpy--;
					spare[idx]++;
					if (maxCnt == spare[idx]) { // 최대 넣을 수 있는 박스만큼 넣었다면 
						nCpy = nCpy - 1 >= 1 ? nCpy - 1 : 1;
						idx++;
						maxCnt = mCpy / nCpy;
					}
				} else {
					nCpy--;
					idx++;
					maxCnt = mCpy / nCpy;
					i--;
				}
			}
			System.out.println(spare[idx - 1]);
		}
	}
}