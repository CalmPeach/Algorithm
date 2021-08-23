import java.io.*;
import java.util.*;

public class Test_1700 {
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken()); //멀티탭 구멍
    	int k = Integer.parseInt(st.nextToken()); //전기용품 총 사용 횟수
    	
    	st = new StringTokenizer(br.readLine());
    	ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>(); // 전기 용품이 몇번 index에 나오는가
    	for(int i = 0; i < k; i++) {
    		arr.add(new ArrayList<Integer>());
    	}
    	int[] order = new int[k]; // 전기 용품의 이름
    	for(int i = 0; i < k; i++) {
    		order[i] = Integer.parseInt(st.nextToken()) - 1;
    		arr.get(order[i]).add(i);
    	}
    	
    	int cnt = 0; // 플러그를 빼는 횟수
    	int[] idx = new int[k]; // 인덱스 위치 
    	Set<Integer> multitab = new HashSet<Integer>();
    	for(int i = 0; i < k; i++) {
    		if(!multitab.contains(order[i])) { // 멀티탭의 전기 용품이 존재하지 않으면 
	    		if(multitab.size() < n) { // 멀티탭의 비어있는 곳이 있다면
	    			multitab.add(order[i]);
	    		} else {
	    			cnt++;
	    			int maxIdx = 0;
	    			int max = 0;
	    			for(int tab : multitab) { // tab: 멀티탭에 꽂은 전기 용품 
	    				if(idx[tab] == -1) { // 더이상 사용되지 않는다.
	    					maxIdx = tab; 
	    					break;
	    				} else {
	    					int nextIdx = arr.get(tab).get(idx[tab]); // 다음 인덱스 
	    					if(nextIdx > max) { // 가장 나중에 사용될 애를 뽑기 위해
	    						max = nextIdx;
	    						maxIdx = tab;
	    					}
	    				} 
	    			}
	    			multitab.remove(maxIdx);
	    			multitab.add(order[i]);
	    		}
    		}
    		
    		// 현재 멀티탭의 들어간 전기용품의 인덱스 조정
    		if(idx[order[i]] + 1 == arr.get(order[i]).size())
    			idx[order[i]] = -1;
    		else
    			idx[order[i]]++;
    	}
    	System.out.println(cnt);
	}
}