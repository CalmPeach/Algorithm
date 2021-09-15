import java.io.*;
import java.util.*;

public class Test_2668 {
	
	static int[] arr;
	static boolean isEnd;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	int n = Integer.parseInt(br.readLine());
    	arr = new int[n + 1];
    	for(int i = 1; i <= n; i++) {
    		arr[i] = Integer.parseInt(br.readLine());
    	}
    	
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	boolean[] visit = new boolean[n + 1];
    	for(int i = 1; i <= n; i++) {
    		if(visit[i])
    			continue;
    		Set<Integer> idx = new HashSet<Integer>();
    		Set<Integer> group = new HashSet<Integer>();
    		idx.add(i);
    		group.add(arr[i]);
    		int j = i;
    		while(true) {
    			if(idx.contains(arr[j])) {
    				break;
    			}
    			idx.add(arr[j]);
    			group.add(arr[arr[j]]);
    			j = arr[j];
    		}
    		
    		boolean flag = true;
    		for(int l : idx)
    			if(!group.contains(l)){
    				flag = false;
    				break;
    			}
    		
    		if(flag) {
    			for(int l : idx) {
        			visit[l] = true;
    				result.add(arr[l]);
    			}
    		}
    			
    	}
    	
    	result.sort(Comparator.naturalOrder());
    	System.out.println(result.size());
    	for(int i = 0; i < result.size(); i++)
    		System.out.println(result.get(i));
    }
}

/*
import java.io.*;
import java.util.*;

public class Main {
	
	static int[] nums;
	static int cnt;
	static ArrayList<Integer> ans;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	int n = Integer.parseInt(br.readLine());
    	nums = new int[n + 1];
    	for(int i = 1; i <= n; i++)
    		nums[i] = Integer.parseInt(br.readLine());
    	
    	cnt = 0;
    	ans = new ArrayList<Integer>();
    	boolean[] visit = new boolean[n + 1];
    	for(int i = 1; i <= n; i++) {
    		if(!visit[i]) {
    			ArrayList<Integer> group = new ArrayList<Integer>();
    			dfs(visit, group, i);
    		}
    	}
    	
    	System.out.println(cnt);
    	ans.sort(Comparator.naturalOrder());
    	for(int i = 0; i < cnt; i++) {
    		System.out.println(ans.get(i));
    	}
    }
    
    public static void dfs(boolean[] visit, ArrayList<Integer> group, int idx) {
    	if(visit[idx]) {
    		if(group.indexOf(idx) != -1) {
    			for(int i = group.size() - 1; i >= 0; i--) {
    				int num = group.get(i);
    				cnt++;
    				ans.add(num);
    				if(num == idx) {
    					break;
    				}
    			}
    		}
    		return;
    	}
    	group.add(idx);
    	visit[idx] = true;
    	dfs(visit, group, nums[idx]);
    }
}
*/