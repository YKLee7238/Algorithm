import java.util.Arrays;
import java.util.Scanner;

import jdk.tools.jlink.internal.SymLinkResourcePoolEntry;

public class Greedy {
    public static void main(String[] args) throws Exception {

        // 큰 수의 법칙
        //LagerstNumber();
        // 1이 될때까지
        //UntilOne2(27, 8);

        StrFlip("010100110");

    }

    private static void LagerstNumber()
    {
        // 첫 번째 입력값 : 숫자 갯수
        // 두 번째 입력값 : 더할 숫자갯수
        // 세 번째 입력값 : 연속 제한 값
        // 방법 최고큰값, 두번째로 큰값으로만 수행하면됨.
        Scanner sc = new Scanner(System.in);

         // N, M, K를 공백을 기준으로 구분하여 입력 받기
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        // N개의 수를 공백을 기준으로 구분하여 입력 받기
        int arr[] = new int[n];

        for(int i=0; i<n; i++)
        {
            arr[i] = sc.nextInt();
        }

        // 정렬 하기 작은순대로 들어옴
        Arrays.sort(arr);

        int first = arr[n-1];
        int second = arr[n-2];

         // 가장 큰 수가 더해지는 횟수 계산
         int cnt = (m / (k + 1)) * k;
         cnt += m % (k + 1);
 
         int result = 0;
         result += cnt * first; // 가장 큰 수 더하기
         result += (m - cnt) * second; // 두 번째로 큰 수 더하기
 
         System.out.println(String.format("결과는 : %s", result));

    }
    // 1이 될 때 까지
    private static void UntilOne(int n, int k)
    {
        /*Scanner sc = new Scanner(System.in);

         // N, M 입력 받기
        int n = sc.nextInt();
        int k = sc.nextInt();*/

        // 1. n에서 1을빼기
        // 2. n을 K로 나누기
        int cnt =0;
        while(true)
        {
            if(n==1)
                break;

            // 나누어 떨어지지 않으면 -1 수행    
            if(n % k>0)
                n = n-1;
            else // 나누어 떨어지면 몫
                n = n/k;
            cnt++;
        }
        System.out.println( String.format("실행 횟수는 : %d", cnt));
    }

    private static void UntilOne2(int n, int k)
    {
        /*Scanner sc = new Scanner(System.in);

         // N, M 입력 받기
        int n = sc.nextInt();
        int k = sc.nextInt();*/

        // 1. n에서 1을빼기
        // 2. n을 K로 나누기
        int cnt =0;
        while (true) {
            // N이 K로 나누어 떨어지는 수가 될 때까지만 1씩 빼기
            int target = (n / k) * k;
            cnt += (n - target);
            n = target;
            // N이 K보다 작을 때 (더 이상 나눌 수 없을 때) 반복문 탈출
            if (n < k) break;
            // K로 나누기
            cnt += 1;
            n /= k;
        }

        // 마지막으로 남은 수에 대하여 1씩 빼기
        cnt += (n - 1);
        System.out.println( String.format("실행 횟수는 : %d", cnt));
    }

    private static void StrFlip(String str)
    {
        int countZero =0, countOne =0;
        char p = str.charAt(0);

        if(p == '1')
            countOne ++;
        else
            countZero ++;
        for(int i =1; i<str.length(); i++)
        {
            char c = str.charAt(i);
            if( (p == c) ==false)
            {
                if(c == '1')
                    countOne ++;
                else
                    countZero ++;

                p=c;
            }
        }

        System.out.println(Math.min(countZero, countOne));
        
    }
}
