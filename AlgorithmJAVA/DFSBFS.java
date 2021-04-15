import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class DFSBFS {
    public static void main(String[] args) {
        
        int n = 5;
        int m = 6;
        int [][] map = new int[n][m];
        //101010
        //111111
        //000001
        //111111
        //111111

        Scanner sc = new Scanner(System.in);
        for(int i =0; i<n; i ++){
            String s = sc.nextLine();
            for(int j=0; j<m; j++){
                map[i][j] = Character.getNumericValue(s.charAt(j)); // char -> int 변환
            }
        }
        sc.close();

        System.out.println(BFS(0, 0, map));
    }     

    public static int BFS(int x, int y, int[][]map)
    {
        // 상 하 좌 우
        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, -1, 1};
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y));

        while(que.isEmpty() ==false){

            Node node = que.poll();
            x = node.getX();
            y = node.getY();
            if(x == map.length && y == map[0].length) // 맵 마지막 이면 끝
                break;
            // 현재 위치에서 상 하 좌 우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // Map 공간을 벗어나면 Pass
                if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) 
                    continue;
                
                if (map[nx][ny] == 0)// 해당 노드를 처음 방문하는 경우에만 최단 거리 기록 
                    continue;
                
                if (map[nx][ny] == 1) {
                    map[nx][ny] = map[x][y] + 1;
                    que.offer(new Node(nx, ny));
                } 
            }
        }
        return map[map.length-1][map[0].length-1];
    }    
}
class Node{
    private int x, y;
    Node(int x, int y){
        this.x =x;
        this.y=y;
    }
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
