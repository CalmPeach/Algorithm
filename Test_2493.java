import java.io.*;
import java.util.*;

public class Test_2493 {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	int n = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	Stack<Pair> sta = new Stack<Pair>();
    	
    	for(int i = 1; i <= n; i++) {
    		int height = Integer.parseInt(st.nextToken());
    		
    		while(!sta.isEmpty()) {
    			if(sta.peek().height >= height) {
    				System.out.print(sta.peek().idx + " ");
    				break;
    			}
    			sta.pop();
    		}
    		if(sta.isEmpty()) {
    			System.out.print(0 + " ");
    		}
    		sta.push(new Pair(i, height));
    	}
    	System.out.println();
    }
    
    public static class Pair{
    	int idx;
    	int height;
    	public Pair(int idx, int height) {
    		this.idx = idx;
    		this.height = height;
    	}
    }
}