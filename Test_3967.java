import java.io.*;
import java.util.*;

public class Test_3967 {
	
	static boolean isEnd;
	static int[] magic;
	static Set<Integer> group;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	char[][] map = new char[5][9];
    	magic = new int[12];
    	group = new HashSet<Integer>(); // 이미 채워져있는 알파벳
    	
    	int idx = 0;
    	for(int i = 0; i < 5; i++) {
    		String input = br.readLine();
    		for(int j = 0; j < 9; j++) {
    			map[i][j] = input.charAt(j);
    			if(map[i][j] == 'x') {
    				magic[idx++] = -1;
    			} else if(map[i][j] != '.') {
    				magic[idx++] = map[i][j] - 65;
    				group.add(map[i][j] - 65);
    			}
    		}
    	}
    	
    	isEnd = false;
    	solution(0);
    	idx = 0;
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 9; j++) {
    			if(map[i][j] == 'x') {
    				map[i][j] = (char)(65 + magic[idx++]);
    			} else if(map[i][j] != '.') {
    				idx++;
    			}
    			System.out.print(map[i][j]);
    		}
    		System.out.println();
    	}
    } 
    
    public static void solution(int k) {
    	if(isEnd)
    		return;
    	
    	if(k == 5 || k == 8 || k == 11 || k == 12) {
    		// 1 2 3 4
    		if(k == 5) {
    			int[] arr = {1, 2, 3, 4};
    			if(!check(arr))
    				return;
    		}
    		// 0 2 5 7
    		else if(k == 8) {
    			int[] arr = {0, 2, 5, 7};
    			if(!check(arr))
    				return;
    		}
    		// 0 3 6 10
    		// 7 8 9 10
    		else if(k == 11) {
    			int[] arr = {0, 3, 6, 10};
    			int[] arr2 = {7, 8, 9, 10};
    			if(!check(arr) || !check(arr2))
    				return;
    		}
    		// 1 5 8 11
    		// 4 6 9 11
    		else if(k == 12) {
    			int[] arr = {1, 5, 8, 11};
    			int[] arr2 = {4, 6, 9, 11};
    			if(!check(arr) || !check(arr2))
    				return;
    		}
    	}
    	if(k == 12) {
    		isEnd = true;
    		return;
    	}
    	
    	if(magic[k] == -1) {
    		for(int i = 0; i < 12; i++) {
    			if(group.contains(i))
    				continue;
    			magic[k] = i;
    			group.add(i);
    			solution(k + 1);
    			group.remove(i);
    			if(isEnd)
    				return;
    			magic[k] = -1;
    		}
    	} else {
    		solution(k + 1);
    	}
    }
    
    public static boolean check(int[] arr) {
		int sum = 0;
		for(int i = 0; i < 4; i++) {
			sum += magic[arr[i]];
		}
		if(sum != 22)
			return false;
		return true;
    }
 
}