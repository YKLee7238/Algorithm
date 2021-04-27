import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SearchCity {
     public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int cityCnt = sc.nextInt();
        int roadCnt = sc.nextInt();
        int shortDistance = sc.nextInt();
        int startCity = sc.nextInt();

        int[] dist = new int[cityCnt + 1];
        Boolean[] visit = new Boolean[cityCnt + 1];

        Arrays.fill(dist, 0);
        Arrays.fill(visit, false);
        System.out.println(String.format("도시 갯수: %d, 도로갯수 : %d, 최단거리 : %d, 시작도시 : %d", cityCnt, roadCnt, shortDistance, startCity));

        // A도시에서 B도시 갈 수 있는 곳.
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for(int i =0 ;i <roadCnt; i++)
        {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(map.containsKey(a) ==false)
            {
                map.put(a, new ArrayList<>());
            }

            map.get(a).add(b);
        }
        sc.close();

        System.out.println("Search Start");
        Queue<Integer> que = new LinkedList<>();
        que.add(startCity);

        while(que.isEmpty() ==false)
        {
            int cur = que.poll();
            if(map.containsKey(cur) ==false)
                continue;
            ArrayList<Integer> road = map.get(cur);
            for(int i : road)
            {
                if(visit[i] ==true)
                    continue;
                dist[i] = dist[cur] +1;
                visit[i] = true;
                que.offer(i);
            }
        }
        
        Boolean isno = false;
        for(int i = 0; i< dist.length; i++)
        {
            if(dist[i] == shortDistance)
            {
                System.out.println("최단거리 도시 : " + i);
                isno = true;
            }
        }

        if(isno == false)
            System.out.println("최단거리 도시 : " + -1);
        
    }
}
