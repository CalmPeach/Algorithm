import java.io.*;
import java.util.*;

//bfs + visit
public class Test_9177 {
	
	static String[] word;
	static boolean isEnd;
	 
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int tc = Integer.parseInt(br.readLine());
    	for(int t = 0; t < tc; t++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		word =  new String[3];
    		for(int i = 0; i < 3; i++)
    			word[i] = st.nextToken();
    		
    		isEnd = false;
    		bfs();
    		if(isEnd) {
    			System.out.println("Data set " + (t + 1) + ": yes");
    		} else {
    			System.out.println("Data set " + (t + 1) + ": no");
    		}
    	}
    }
    
    public static void bfs() {
    	Queue<Pair> q = new LinkedList<Pair>();
    	q.add(new Pair(0, 0, 0));
    	boolean[][] visit = new boolean[word[0].length() + 1][word[1].length() + 1];
    	visit[0][0] = true;
    	
    	while(!q.isEmpty()) {
    		Pair now = q.poll();
    		
    		if(now.idx3 == word[2].length()) {
    			isEnd = true;
    			break;
    		}
    		
    		if(now.idx1 < word[0].length() && !visit[now.idx1 + 1][now.idx2] && word[0].charAt(now.idx1) == word[2].charAt(now.idx3)) {
    			q.add(new Pair(now.idx1 + 1, now.idx2, now.idx3 + 1));
    			visit[now.idx1 + 1][now.idx2] = true;
    		}
    		if(now.idx2 < word[1].length() && !visit[now.idx1][now.idx2 + 1] && word[1].charAt(now.idx2) == word[2].charAt(now.idx3)) {
    			q.add(new Pair(now.idx1, now.idx2 + 1, now.idx3 + 1));
    			visit[now.idx1][now.idx2 + 1] = true;
    		}
    	}
    }
    
    public static class Pair{
    	int idx1;
    	int idx2;
    	int idx3;
    	public Pair(int idx1, int idx2, int idx3) {
    		this.idx1 = idx1;
    		this.idx2 = idx2;
    		this.idx3 = idx3;
    	}
    }
}

/*
import java.io.*;
import java.util.*;

// 알파벳 확인  + dfs
public class Test_9177 {
	
	static String[] word;
	static boolean isEnd;
	static HashSet<Character> set = new HashSet<>();
	 
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int tc = Integer.parseInt(br.readLine());
    	for(int t = 0; t < tc; t++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		word =  new String[3];
    		for(int i = 0; i < 3; i++)
    			word[i] = st.nextToken();
    		
    		isEnd = false;
    		if(check()) {
    			dfs(0, 0, 0);
    		}
    		if(isEnd) {
    			System.out.println("Data set " + (t + 1) + ": yes");
    		} else {
    			System.out.println("Data set " + (t + 1) + ": no");
    		}
    	}
    }
    
    public static void dfs(int idx1, int idx2, int idx3) {
    	if(isEnd)
    		return;
    	
    	if(idx3 == word[2].length()) {
    		isEnd = true;
    		return;
    	}
    	
    	if(idx1 < word[0].length() && idx2 < word[1].length()) {
    		char ch1 = word[0].charAt(idx1);
    		char ch2 = word[1].charAt(idx2);
    		char ch3 = word[2].charAt(idx3);
    		if(ch1 == ch2 && ch2 == ch3){
    			dfs(idx1 + 1, idx2, idx3 + 1);
    			dfs(idx1, idx2 + 1, idx3 + 1);
    		} else if(ch1 == ch3) {
    			dfs(idx1 + 1, idx2, idx3 + 1);
    		} else if(ch2 == ch3){
    			dfs(idx1, idx2 + 1, idx3 + 1);
    		}
    	} else if(idx1 < word[0].length()) {
    		if(word[0].charAt(idx1) != word[2].charAt(idx3)) {
    			return;
    		} else {
    			dfs(idx1 + 1, idx2, idx3 + 1);
    		}
    	} else if(idx2 < word[1].length()) {
    		if(word[1].charAt(idx2) != word[2].charAt(idx3)) {
    			return;
    		} else {
    			dfs(idx1, idx2 + 1, idx3 + 1);
    		}
    	}
    }
    
    static boolean check() {
        
        for (int i = 0; i < 2; i++) {
            int len = word[i].length();
            for (int j = 0; j < len; j++) {
                set.add(word[i].charAt(j));
            }
        }
        
        for (int i = 0; i < word[2].length(); i++) {
            if(!set.contains(word[2].charAt(i))) return false;
        }
        
        return true;
    }
}
 */
