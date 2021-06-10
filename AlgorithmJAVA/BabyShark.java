import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BabyShark {

    static int[][] map;
    static int[] dx = { -1, 0, 1, 0 }; // 상 우 하 좌
    static int[] dy = { 0, 1, 0, -1 };
    public static void main(String[] args) throws Exception{
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        Fish shark = null;
        Queue<Fish> fishes = new LinkedList<Fish>();
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                int val = Integer.parseInt(st.nextToken());
                map[i][j] = val;
                if(val== 9 )
                {
                    shark = new Fish(i, j, 2, 0);
                    map[i][j] = 0; // 상어가 돌아다니면서 처음 상어위치로 갔을때 Size 0으로 계산하기위함. 지나갈수있게.
                }
            }
        }
        int sharkEatCnt = 0; // 상어가 먹은 물고기 Count
        while(true)
        {
            boolean[][] visited = new boolean[n][n];
            List<Fish> targetFish = new ArrayList<Fish>(); // 먹을 수 있는 물고기 리스트
            fishes.clear();
            fishes.add(shark); //처음 상어 부터 시작
            while(!fishes.isEmpty())
            {
                Fish f = fishes.poll(); //시작위치
                for(int i=0; i< 4; i++)
                {
                    int nx = f.x + dx[i];
                    int ny = f.y + dy[i];
                    // 지도 범위밖
                    if(isImPossible(nx, ny))
                        continue;

                    if(visited[nx][ny])
                        continue;

                    Fish eatTarget = new Fish(nx, ny, map[nx][ny], f.distance +1);
                    
                    // 먹을수 있는경우.
                    if(shark.size > eatTarget.size && isFish(map[nx][ny]))
                        targetFish.add(eatTarget);

                    // 물고기가 상어보다 크기가크면 Pass
                    if(shark.size < eatTarget.size )
                        continue;       

                    visited[nx][ny] = true;

                    fishes.add(eatTarget);                    
                }
            }

            if(targetFish.size() >0)
            {
                // 물고기 우선순위로 추출
                Collections.sort(targetFish);
                Fish eatFish = targetFish.get(0);
                
                //상어 위치이동
                shark.x = eatFish.x;
                shark.y = eatFish.y;
                shark.distance = eatFish.distance;
                
                sharkEatCnt ++;
                if(shark.size == sharkEatCnt)
                {
                    shark.size ++;
                    sharkEatCnt = 0;
                }

                // 지도에서 위치이동가능하게 0 처리
                map[shark.x][shark.y] =0;
                targetFish.clear(); //먹잇감 초기화
            }
            else
            {
                break;
            }
        }

        System.out.println(shark.distance);
    }
    private static boolean isFish(int f)
    {
        // 1 ~6 물고기
        if(0 < f && f <7)
            return true;
        return false;
        
    }
    private static boolean isImPossible(int nx, int ny)
    {
        return nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length;
    }
}
class Fish implements Comparable<Fish>
{
    int x;
    int y;
    int size;
    int distance;
    public Fish(int x, int y, int size, int distance)
    {
        this.x = x;
        this.y= y;
        this.size = size;
        this.distance = distance;
    }

    public int compareTo(Fish a)
    {
        // 거리가 다르면 오름차순.
        if(this.distance != a.distance)
            return Integer.compare(this.distance, a.distance);
        
        // 가장 위에있는 물고기
        if(this.x != a.x)
            return Integer.compare(this.x, a.x);
        
        return Integer.compare(this.y, a.y);
    }
}
