import java.io.*;
import java.util.*;
 
public class Test_2437 {
	
    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine()); // 추의 개수
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int[] w = new int[n]; // 추의 무게
    	for(int i = 0; i < n; i++)
    		w[i] = Integer.parseInt(st.nextToken());
    	Arrays.sort(w);
    	
    	int max = 0;
    	for(int i = 0; i < n; i++)
    		if(w[i] > max + 1)
    			break;
    		else
    			max += w[i];
    	System.out.println(max + 1);
    }
    
    
}   