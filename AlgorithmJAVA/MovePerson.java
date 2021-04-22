import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MovePerson {
    public static void main(String[] args)  throws Exception{
        // 상 하 좌 우
        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, -1, 1};
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int land = Integer.parseInt(stk.nextToken());
        int llimit = Integer.parseInt(stk.nextToken());
        int rlimit = Integer.parseInt(stk.nextToken());
        int [][] map = new int[land][land];
        for (int i = 0; i < land; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < land; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
    
        int rsltCnt = 0;
        Queue<Nation> q = new LinkedList<>();
        while(true)
        {
            int unitedNum =0;
            boolean isMove = false;
            ArrayList<United> unitedList = new ArrayList<>();
            boolean isUnited[][] = new boolean[land][land];
            for (int i= 0; i < map.length; i++)
            {
                for(int j=0; j<map[0].length; j++)
                {
                    if(isUnited[i][j] == true)
                        continue;
                    Nation start = new Nation(i, j); 
                    isUnited[i][j] = true;
                    q.add(start);

                    unitedNum ++;
                    United uni = new United(unitedNum);
                    uni.AddCountry(start,map[i][j]);
                    while(q.isEmpty() == false)
                    {
                        Nation cur = q.poll();
                        for(int n =0; n< 4; n++)
                        {
                            Nation nextNode = new Nation(cur.getX() + dx[n], cur.getY() + dy[n]);
                            int nx = nextNode.getX();
                            int ny = nextNode.getY();

                            if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length)
                                continue;
                            if(isUnited[nx][ny] == true)
                                continue;
                            
                            int gap =  Math.abs(map[cur.getX()][cur.getY()] - map[nx][ny]);
                            if( llimit<=gap && gap <=rlimit)
                            {
                                isMove = true;
                                // 연합 지정.
                                isUnited[nx][ny] = true;
                                uni.AddCountry(nextNode, map[nx][ny]);
                                q.add(nextNode);
                            }
                        }
                    }
                    unitedList.add(uni);
                }
            }
            if(isMove == false)
                break;
            
            // 연합 참조하여 사람들이동
            MovePeople(map, unitedList);

            rsltCnt++;
        }

        System.out.println("총 인구이동 횟수 : "+ rsltCnt );
    }

    public static void MovePeople(int [][] map, ArrayList<United> unitedList)
    {
        for(United u : unitedList){
            int move = u.GetUnitedPeopleAvrg();
            for(Nation n: u.country)
            {
                map[n.getX()][n.getY()] = move;
            }
        }
    }
}

class Nation{
    private int x, y;
    Nation(int x, int y){
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

class United{
    int number;
    int sum;
    ArrayList<Nation> country = new ArrayList<Nation>();

    United(int number)
    {
        this.number = number;
    }

    public void AddCountry(Nation country, int people)
    {
        this.sum += people;
        this.country.add(country);
    }

    public int GetUnitedPeopleAvrg()
    {
        return sum/this.country.size();
    }
}

