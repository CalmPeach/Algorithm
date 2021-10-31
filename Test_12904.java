import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Test_12904 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		
		StringBuilder tmp = new StringBuilder(t);
		for(int i = t.length() - 1; i >= s.length(); i--) {
			char ch = tmp.charAt(i);
			tmp.setLength(i);
			if(ch == 'B') {
				tmp.reverse();
			}
		}
		
		if(tmp.toString().equals(s)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
	
}

