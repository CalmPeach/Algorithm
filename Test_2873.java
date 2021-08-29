import java.io.*;
import java.util.*;
 
public class Test_2873 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int r = Integer.parseInt(st.nextToken());
    	int c = Integer.parseInt(st.nextToken());
    	int[][] map = new int[r][c];
    	for(int i = 0; i < r; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < c; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	StringBuffer sb = new StringBuffer();
    	if(r % 2 != 0){ // 세로가 홀수라면 
    		char d = 'R';
    		for(int i = 0; i < r; i++) {
    			for(int j = 0; j < c - 1; j++) {
    				sb.append(d);
    			}
    			if(i == r - 1)
    				break;
    			sb.append("D");
    			d = d == 'R'? 'L': 'R';
    		}
    	} else if(c % 2 != 0) { // 가로가 홀수라면 
    		char d = 'D';
    		for(int i = 0; i < c; i++) {
    			for(int j = 0; j < r - 1; j++) {
    				sb.append(d);
    			}
    			if(i == c - 1)
    				break;
    			sb.append("R");
    			d = d == 'D'? 'U': 'D';
    		}
    	} else {
    		int minR = 0;
    		int minC = 1;
    		// 가장 작은 위치를 찾는다. 0, 2... 짝수번 줄은 홀수칸만 탐색 1, 3... 홀수번 줄은 짝수칸만 탐색
    		for(int i = 0; i < r; i++) {
    			for(int j = i % 2 == 0? 1: 0; j < c; j += 2) {
    				if(map[minR][minC] > map[i][j]) {
    					minR = i;
    					minC = j;
    				}
    			}
    		}
    		
    		char d = 'R';
    		for(int i = 0; i < r; i++) {
    			if(i % 2 == 0 && (i == minR || i + 1 == minR)) { // 짝수행일때 가장 작은 위치가 포함되어 있는지 확인 
    				boolean flag = false; // 가장 작은 위치 탐색했는지 여부
    				for(int j = 0; j < c; j += 2) {
    					if(minC == j) {
    						sb.append("RD");
    						flag = true;
    					} else if(minC == j + 1) {
    						sb.append("DR");
    						flag = true;
    					} else {
    						if(flag)
    							sb.append("URD");
    						else
    							sb.append("DRU");
    					}
    					if(j == c - 2)
    						break;
    					sb.append("R");
    				}
    				i++;
    			} else {
	    			for(int j = 0; j < c - 1; j++) {
	    				sb.append(d);
	    			}
    			}
    			if(i == r - 1)
    				break;
    			sb.append("D");
    			d = d == 'R'? 'L': 'R';
    		}
    	}
    	System.out.println(sb);
    }
}   