import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Ch17_Q38
{
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 학생의수
        int m = Integer.parseInt(st.nextToken()); // 비교횟수 .간선수

        int[][] arr= new int[n+1][n+1];
        for(int[] a : arr)
            Arrays.fill(a, 99999);

       
        // 1. 자기자신에서 자기로 가는거리는 0
        for(int i=1; i<=n; i++)
            for(int j=1; j<=n; j++)
                if(i ==j )
                    arr[i][j] = 0;
        
        // 2. 입력받은 가능한 노드에서노드 면 10으로 초기화
        
        for(int i= 1; i<=m; i++)
        {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 10;
        }

        for(int k=1; k<=n; k++)
        {
            for(int a=1; a<=n; a++)
            {
                for(int b=1; b<=n; b++)
                {
                    arr[a][b] = Math.min(arr[a][b], arr[a][k] + arr[k][b]);
                }
            }
        }

        String rslt = "";
        for(int i = 1; i<=n; i++)
        {
            boolean isPossible = true;
            for(int j = 1; j<=n; j++)
            {
                if(arr[i][j] < 99999 || arr[j][i] < 99999)
                    continue;
                isPossible = false;
            }
            if(isPossible)
                rslt +=Integer.toString(i);
        }
        System.out.println(rslt);
        
    }
}