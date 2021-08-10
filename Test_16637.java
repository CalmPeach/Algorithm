import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Test_16637 {
	
	static ArrayList<Integer> num; // 숫자를 저장하는 리스트
	static ArrayList<Character> op; // 연산자를 저장하는 리스트
	static int max;
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();
        num = new ArrayList<Integer>();
        op = new ArrayList<Character>();
        for(int i = 0; i < n; i++) {
        	char ch = input.charAt(i);
        	if(ch == '*' || ch == '+' || ch == '-')
        		op.add(ch);
        	else
        		num.add(ch - '0');
        }
        
        max = Integer.MIN_VALUE;
        dfs(num.get(0), 0, 1);
        System.out.println(max);
    }
    
    // n: 현재까지 계산된 연산 결과, oIdx: 현재 연산할 연산자의 index, nIdx: 연산에 사용할 피연산자
    public static void dfs(int n, int oIdx, int nIdx) {
    	// 뒤에 연산식이 없는 경우 
    	if(oIdx >= op.size() || nIdx >= num.size()) {
    		if(n > max)
    			max = n;
    		return;
    	}
    	
    	int total = calc(n, num.get(nIdx), op.get(oIdx));
    	dfs(total, oIdx + 1, nIdx + 1);
    	
    	// ()를 만드는 경우 
    	if(oIdx + 1 < op.size()) {
    		int tmp = calc(num.get(nIdx), num.get(nIdx + 1), op.get(oIdx + 1));
    		int result = calc(n, tmp, op.get(oIdx));
    		dfs(result, oIdx + 2, nIdx + 2);
    	}
    }
    
    // 계산하는 메서드
    public static int calc(int n1, int n2, char op) {
    	int result = 0;
    	switch(op) {
    		case '+':
    			result = n1 + n2;
    			break;
    		case '-':
    			result = n1 - n2;
    			break;
    		case '*':
    			result = n1 * n2;
    			break;
    	}
    	return result;
    }
}