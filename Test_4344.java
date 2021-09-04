import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_4344 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] score = new int[n];
			int sum = 0;
			for(int i = 0; i < n; i++) {
				score[i] = Integer.parseInt(st.nextToken());
				sum += score[i];
			}
			double avg = (double)sum / n;
			int cnt = 0;
			for(int i = 0; i < n; i++) {
				if(avg < score[i])
					cnt++;
			}
			double result = (double)cnt / n * 100;
			System.out.println(String.format("%.3f", result) + "%");
		}
	}	
}