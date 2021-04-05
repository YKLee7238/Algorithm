import java.util.*;


public class Greedy {
    public static void main(String[] args) throws Exception {

        // 큰 수의 법칙
        //LagerstNumber();
        // 1이 될때까지
        UntilOneByAuthor(27, 8);

        StrFlip("111111");

        MultipleOrAdd("567");

        System.out.println(Mujilive(new int[]{3,1,2},5));
        ;

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

    private static void UntilOneByAuthor(int n, int k)
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
        // 백준 1439
        int countZero =0, countOne =0;
        char p = str.charAt(0);

        // 1로 바꾸는것과 0으로 바꾸는것 둘다 Count
        if(p == '1')
            countOne ++;
        else
            countZero ++;
        for(int i =1; i<str.length(); i++)
        {
            char c = str.charAt(i);
            //앞 글자와 같은경우는 Count 하지 않음
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

    private static void MultipleOrAdd(String str)
    {
        
        String[] sp= str.split("");
        int result = Integer.parseInt(sp[0]);
        for(int i=1; i<sp.length; i++)
        {
            int s = Integer.parseInt(sp[i]);

            if(s <=1 || result == 0)
                result +=s;
            else
                result *= s;
        }
        System.out.println(result);

    }

    private static int Mujilive(int[] food_times, long k)
    {
        // 전체 음식을 먹는 시간보다 k가 크거나 같다면 -1
        long summary = 0;
        for (int i = 0; i < food_times.length; i++) {
            summary += food_times[i];
        }
        if (summary <= k) return -1;

        // 시간이 작은 음식부터 빼야 하므로 우선순위 큐를 이용
        PriorityQueue<Food> pq = new PriorityQueue<>();
        for (int i = 0; i < food_times.length; i++) {
            // (음식 시간, 음식 번호) 형태로 우선순위 큐에 삽입
            pq.offer(new Food(food_times[i], i + 1));
        }

        summary = 0; // 먹기 위해 사용한 시간
        long previous = 0; // 직전에 다 먹은 음식 시간
        long length = food_times.length; // 남은 음식의 개수

        // summary + (현재의 음식 시간 - 이전 음식 시간) * 현재 음식 개수와 k 비교
        while (summary + ((pq.peek().getTime() - previous) * length) <= k) {
            int now = pq.poll().getTime();
            summary += (now - previous) * length;
            length -= 1; // 다 먹은 음식 제외
            previous = now; // 이전 음식 시간 재설정
        }

        // 남은 음식 중에서 몇 번째 음식인지 확인하여 출력
        ArrayList<Food> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }
        // 음식의 번호 기준으로 정렬
        Collections.sort(result, new Comparator<Food>() { 
            @Override
            public int compare(Food a, Food b) {
                return Integer.compare(a.getIndex(), b.getIndex());
            }
        });
        return result.get((int) ((k - summary) % length)).getIndex();
    }

    static class Food implements Comparable<Food> {

        private int time;
        private int index;
    
        public Food(int time, int index) {
            this.time = time;
            this.index = index;
        }
    
        public int getTime() {
            return this.time;
        }
    
        public int getIndex() {
            return this.index;
        }
    
        // 시간이 짧은 것이 높은 우선순위를 가지도록 설정
        @Override
        public int compareTo(Food other) {
            return Integer.compare(this.time, other.time);
        }
    }
}
 
