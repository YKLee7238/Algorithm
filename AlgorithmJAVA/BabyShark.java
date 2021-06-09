import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BabyShark {
    public static void main(String[] args) throws Exception{
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        // 상 우 하 좌
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        int[][] map = new int[n][n];

        int startX=0;
        int startY=0;
        int shark =2;

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                int val = Integer.parseInt(st.nextToken());
                map[i][j] = val;
                if(val== 9 )
                {
                    startX =i; startY =j;
                }
            }
        }


        System.out.println();      
    }
}
class fish
{
    
}
