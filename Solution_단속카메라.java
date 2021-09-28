import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        // 진입 지점이 작은순으로 진출지점이 작은순으로 정렬
        Arrays.sort(routes, new Comparator<int[]>(){
           public int compare(int[] o1, int[] o2){
               if(o1[0] == o2[0])
                   return Integer.compare(o1[1], o2[1]);
               return Integer.compare(o1[0], o2[0]);
           } 
        });
        
        int idx = 1; // 차량을 가리키는 index
        int cnt = 1; // 카메라 개수 
        int b = routes[0][1]; // 카메라가 설치될 위치
        while(idx < routes.length){
            if(b < routes[idx][0]){ // 카메라가 설치될 위치보다 진입지점이 크다면 -> 겹치지 않음 
                cnt++; // 카메라 개수 더 필요
                b = routes[idx][1]; 
            } else{ // 겹침
                if(b > routes[idx][1]) // 카메라가 설치될 위치보다 차량의 진출지점이 작다면 카메라를 해당 진출지점에 설치해야 해당 차량도 카메라를 만날 수 있음
                    b = routes[idx][1];
            }
            idx++;
        }
        answer = cnt;
        return answer;
    }
}