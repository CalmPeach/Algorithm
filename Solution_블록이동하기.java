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
        int[][][][] visit = new int[101][101][101][101]; // 로봇의 좌표 r1,c1,r2,c2
        visit[0][0][0][1] = 1;
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(new int[]{0, 0}, new int[]{0, 1}, true));
        int min = Integer.MAX_VALUE;
        
        while(!q.isEmpty()){
            Pair now = q.poll();
            int[] r = now.r;
            int[] c = now.c;
            
            if((r[0] == (n - 1) && c[0] == (n - 1)) || (r[1] == (n - 1) && c[1] == (n - 1))){ // 로봇의 두 좌표 중 하나가 n,n에 도달한다면 
                min = visit[r[0]][c[0]][r[1]][c[1]];
                break;
            }
            
            // 회전 없이 움직임
            for(int i = 0; i < 4; i++){
                int nr1 = r[0] + dr[i];
                int nc1 = c[0] + dc[i];
                int nr2 = r[1] + dr[i];
                int nc2 = c[1] + dc[i];
                if(!isRange(nr1, nc1) || !isRange(nr2, nc2) || board[nr1][nc1] == 1 || board[nr2][nc2] == 1) // 두 좌표 중 하나라도 범위를 벗어나거나 벽이라면 
                    continue;
                if(visit[nr1][nc1][nr2][nc2] != 0) // 이미 방문했다면 
                    continue;
                visit[nr1][nc1][nr2][nc2] = visit[r[0]][c[0]][r[1]][c[1]] + 1;
                q.add(new Pair(new int[]{nr1, nr2}, new int[]{nc1, nc2}, now.isRow));
            }
            
            // 회전 
            for(int i = 0; i < 2; i++){ // 로봇의 두 좌표 중 기준이 되는 index
                int other = i ^ 1; // 루봇의 두 좌표 중 기준이 되는 index가 아닌 다른 index
                int sIdx = now.isRow? 0: 2; // 현재 로봇의 상태가 가로이면 로봇은 90도 회전시 세로, 로봇의 상태가 세로이면 로봇은 90도 회전시 가로
                for(int j = sIdx; j < sIdx + 2; j++){
                    int[] nr = new int[2]; // 90도 회전 후 위치
                    int[] nc = new int[2];
                    nr[i] = r[i]; // 기준이 되는 좌표는 그대로 
                    nc[i] = c[i];
                    nr[other] = r[i] + dr[j]; // 기준이 아닌 좌표는 90도 회전
                    nc[other] = c[i] + dc[j];
                    int cr = r[other] + dr[j]; // 회전 시 지나가는 좌표 
                    int cc = c[other] + dc[j];
                    if(!isRange(nr[other], nc[other]) || !isRange(cr, cc) || board[cr][cc] == 1 || board[nr[other]][nc[other]] == 1) 
                        continue;
                    
                    if(visit[nr[0]][nc[0]][nr[1]][nc[1]] != 0)
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
        boolean isRow; // 현재 로봇의 상태가 가로인지 여부
        public Pair(int[] r, int[] c, boolean isRow){
            this.r = r;
            this.c = c;
            this.isRow = isRow;
        }
    }
}