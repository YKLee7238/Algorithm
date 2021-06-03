import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class DP {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] tArr = new int[n+10];
        int[] pArr = new int[n+10];
        int[] dp = new int[n+10];
        for(int i=1; i<=n ; i++)
        {
            st = new StringTokenizer(br.readLine());
            tArr[i] = Integer.parseInt(st.nextToken());
            pArr[i] = Integer.parseInt(st.nextToken());
                
        }
        int max =0;
        for(int i =1; i<=n+1; i++)
        {
            // 날짜 다음날 시작 - 수익발생
            dp[i] = Math.max(max, dp[i]);
            if(i+tArr[i] <=n+1)
            {
                // 현재날짜 + 상담완료날짜 에 최대수익 세팅
                // MAX(이미산출된 그날까지 수익, 당일까지의수익 + 당일수익)
                dp[i+tArr[i]] = Math.max(dp[i+tArr[i]], dp[i]+pArr[i]);
            }
            if(max<dp[i])
                max = dp[i];
        }

        System.out.println(max);

    }
    
}
