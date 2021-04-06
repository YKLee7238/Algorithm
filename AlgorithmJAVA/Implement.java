import java.util.*;

import javax.swing.plaf.metal.MetalBorders.PaletteBorder;

public class Implement {
    
    public static void main(String[] args) {
        
        DevGame(4,4, 1,1, 0);
    }

    public static void DevGame(int n, int m, int playerX, int playerY, int playerC){
        // n, m 은 맵
        // x,y 는 현위치, c는 방향
        
        int[] curLoc = new int[]{playerX, playerY};

        // 북 서 남 동
        int[] dir = new int[] {0, 3, 2, 1};
        int c = dir[playerC];

        // 북 동 남 서
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        Scanner sc = new Scanner(System.in);
        int[][] visit = new int[n][m];
        
        int[][] map = new int[n][m];

        visit[playerX][playerY] = 1;
        // map 초기화
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
            {
                map[i][j] = sc.nextInt();
            }


        
        while(true)
        {
            List<Boolean> no = new ArrayList<>(4);
            
            for(int i =0; i < dir.length; i++)
            {
                if(map[curLoc[0]+dx[dir[i]]][curLoc[1]+dy[dir[i]]] == 1)
                {
                    no.add(true);
                    continue;
                }
                if(visit[curLoc[0]+dx[dir[i]]][curLoc[1]+dy[dir[i]]] != 1)
                {
                    visit[curLoc[0]+dx[dir[i]]][curLoc[1]+dy[dir[i]]] = 1;

                    curLoc[0] =curLoc[0]+dx[dir[i]];
                    curLoc[1] =curLoc[1]+dy[dir[i]];
                    break;
                }else{
                    no.add(true);
                    continue;
                }
            }

            no.removeIf(xz-> xz == true);

            if(no.size() ==0)
                break;
        }
    }
}
