import java.io.*;
import java.util.*;

public class Test_12851 {
	
	static int n;
	static int k;
	static int cnt;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	if(n == k) {
    		System.out.println(0);
    		System.out.println(1);
    	} else {
	    	System.out.println(bfs());
	    	System.out.println(cnt);
    	}
    }
    
    public static int bfs() {
    	Pair[] visit = new Pair[100002];
    	visit[n] = new Pair(-1, n, 1, 1);
    	Queue<Pair> q = new LinkedList<Pair>();
    	q.add(visit[n]);
    	int minSec = Integer.MAX_VALUE;
    	while(!q.isEmpty()) {
    		Pair now = q.poll();
    		for(int i = 0; i < 3; i++) {
    			int next = now.idx;
    			if(i == 0) {
    				next += 1;
    			} else if(i == 1) {
    				next -= 1;
    			} else {
    				next *= 2;
    			}
    			if((next >= 0 && next <= 100001 && (visit[next] == null || visit[next].minSec >= visit[now.idx].minSec + 1)) || next == k) {
    				if(next == k) {
    					if(minSec > visit[now.idx].minSec) {
    	    				minSec = visit[now.idx].minSec;
    	    				cnt = visit[now.idx].cnt;
    	    			}
    	    			else if(minSec == visit[now.idx].minSec)
    	    				cnt += visit[now.idx].cnt;
    				} else {
    					if(visit[next] == null) {
    						visit[next] = new Pair(now.idx, next, visit[now.idx].cnt, visit[now.idx].minSec + 1);
		    				q.add(visit[next]);
    					} else if(visit[next].minSec == visit[now.idx].minSec + 1) {
    						visit[next].beforeIdx = now.idx;
    						visit[next].cnt += visit[now.idx].cnt;
    					} 
    				}
    			}
    		}
    	}
    	return minSec;
    }
    
    public static class Pair{
    	int beforeIdx;
    	int idx;
    	int cnt;
    	int minSec;
    	public Pair(int beforeIdx, int idx, int cnt, int minSec) {
    		this.beforeIdx = beforeIdx;
    		this.idx = idx;
    		this.cnt = cnt;
    		this.minSec = minSec;
    	}
    }
}

/*
 * import java.util.*;
import java.io.*;

class Main {
    static int N, K;
    static int[] time = new int[100001];
    static int minTime = 987654321;
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N >= K) {
            System.out.println((N-K) + "\n1");
            return;
        }

        bfs();

        System.out.println(minTime + "\n" + count);
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<Integer>();

        q.add(N);
        time[N] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();
            
            // now 방문 시간이 최소 시간보다 크면 뒤는 더 볼 필요 없음
            if (minTime < time[now]) return;

            for (int i=0; i<3; i++) {
                int next;

                if (i == 0)         next = now + 1;
                else if (i == 1)    next = now - 1;
                else                next = now * 2;

                if (next < 0 || next > 100000) continue;

                if (next == K) {
                    minTime = time[now];
                    count++;
                }

                // 이미 방문한 곳이어도 경우의 수 카운팅을 위해 큐에 넣어줌
                if (time[next] == 0 || time[next] == time[now] + 1) {
                    q.add(next);
                    time[next] = time[now] + 1;
                }
            }
        }
    }
}
 */