import java.util.*;


public class Implement {
    
    public static void main(String[] args) {
        
        //DevGame(4,4, 1,1, 0);
        System.out.println(StringCompression("aabbaccc"));
    }

    public static void DevGame(int n, int m, int playerX, int playerY, int playerC){
        // n, m 은 맵
        // x,y 는 현위치, c는 방향
        
        int[] curLoc = new int[]{playerX, playerY, playerC};

        // 북 동 남 서
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1}; 
        
        
        int[][] visit = new int[n][m];
        int[][] map = new int[n][m];

        int turnCnt =0;
        visit[playerX][playerY] = 1;

        System.out.println(String.format("%d x %d 형태의 Map 입력하세요", n,m));
        Scanner sc = new Scanner(System.in);
        // map 초기화
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
            {
                map[i][j] = sc.nextInt();
            }
        
        int totalCnt =1;
        sc.close();
        while(true)
        {
            int nextDir = TurnLeft(curLoc[2]);
            int nextX = curLoc[0]+dx[nextDir];
            int nextY = curLoc[1]+dy[nextDir];
            int mapNextXY =  map[nextX][nextY];
            
            // 바다인경우 or 가봤던 칸.
            if(mapNextXY == 1 || visit[nextX][nextY] == 1)
            {
                turnCnt  ++;
                curLoc[2] = nextDir;
            }
            else{ // 방문할 수 있는 경우.
                visit[nextX][nextY] =1; //방문 기록

                //위치 변경
                curLoc[0] = nextX;
                curLoc[1] = nextY;
                curLoc[2] = nextDir;

                turnCnt = 0;
                totalCnt++;
                continue;
            }

            if(turnCnt ==4)
            {
                int x = curLoc[0]-dx[nextDir];
                int y = curLoc[1]-dy[nextDir];

                if(map[x][y] ==1)
                    break;
                else{
                    curLoc[0] = x;
                    curLoc[1] = y;
                }

                turnCnt=0;
            }
        }
        System.out.println("정답은 : "+ totalCnt);

    }
    private static int TurnLeft(int cur)
    {
        // 북 이면 서
        if(cur == 0 )
            return 3;
        else
            return cur-1;
    }

    public static int StringCompression(String s) {
        int answer = 9999;
        int result =0;
        int cnt =0;
        for (int j = 1; j < s.length() /2 +1; j ++)
        {
            String pre = s.substring(j,1);

            for (int i=1; i <s.length(); i++)
            {
                String cur = s.substring(j, i);
                if(pre.equals(cur))
                {
                    cnt ++;
                }
                else{
                    result += cnt + pre.length();
                    cnt=0;
                }       

                pre = cur;
            }
            result =0;
            answer = Integer.min(answer, result);
        }
        return answer;
    }
}
