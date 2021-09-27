import java.util.*;

class Solution {
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    int n;
    
    public int solution(int[][] board) {
        n = board.length;
        int answer = bfs(board);
        return answer;
    }
    
    public int bfs(int[][] board){
        int[][][][] visit = new int[101][101][101][101];
        visit[0][0][0][1] = 1;
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(new int[]{0, 0}, new int[]{0, 1}, true));
        int min = Integer.MAX_VALUE;
        
        while(!q.isEmpty()){
            Pair now = q.poll();
            int[] r = now.r;
            int[] c = now.c;
            
            if((r[0] == (n - 1) && c[0] == (n - 1)) || (r[1] == (n - 1) && c[1] == (n - 1))){
                min = min > visit[r[0]][c[0]][r[1]][c[1]]? visit[r[0]][c[0]][r[1]][c[1]]: min;
                continue;
            }
            
            // 회전 없이 움직임
            for(int i = 0; i < 4; i++){
                int nr1 = r[0] + dr[i];
                int nc1 = c[0] + dc[i];
                int nr2 = r[1] + dr[i];
                int nc2 = c[1] + dc[i];
                if(!isRange(nr1, nc1) || !isRange(nr2, nc2) || board[nr1][nc1] == 1 || board[nr2][nc2] == 1)
                    continue;
                if(visit[nr1][nc1][nr2][nc2] != 0 && visit[nr1][nc1][nr2][nc2] <= visit[r[0]][c[0]][r[1]][c[1]] + 1)
                    continue;
                visit[nr1][nc1][nr2][nc2] = visit[r[0]][c[0]][r[1]][c[1]] + 1;
                q.add(new Pair(new int[]{nr1, nr2}, new int[]{nc1, nc2}, now.isRow));
            }
            
            // 회전 
            for(int i = 0; i < 2; i++){
                int other = i ^ 1;
                int sIdx = now.isRow? 0: 2;
                for(int j = sIdx; j < sIdx + 2; j++){
                    int[] nr = new int[2];
                    int[] nc = new int[2];
                    nr[i] = r[i];
                    nc[i] = c[i];
                    nr[other] = r[i] + dr[j];
                    nc[other] = c[i] + dc[j];
                    int cr = r[other] + dr[j];
                    int cc = c[other] + dc[j];
                    if(!isRange(nr[other], nc[other]) || !isRange(cr, cc) || board[cr][cc] == 1 || board[nr[other]][nc[other]] == 1)
                        continue;
                    
                    if(visit[nr[0]][nc[0]][nr[1]][nc[1]] != 0 && visit[nr[0]][nc[0]][nr[1]][nc[1]] <= visit[r[0]][c[0]][r[1]][c[1]] + 1)
                        continue;
                    
                    visit[nr[0]][nc[0]][nr[1]][nc[1]] = visit[r[0]][c[0]][r[1]][c[1]] + 1;
                    q.add(new Pair(nr, nc, !now.isRow));
                }
            }
        }
        return min - 1;
    }
    
    public boolean isRange(int r, int c){
        if(r < 0 || r >= n || c < 0 || c >= n)
            return false;
        return true;
    }
    
    public class Pair{
        int[] r;
        int[] c;
        boolean isRow;
        public Pair(int[] r, int[] c, boolean isRow){
            this.r = r;
            this.c = c;
            this.isRow = isRow;
        }
    }
}