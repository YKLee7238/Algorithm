import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.CompletionStage;


public class GraphQ43 {
     static int[] parent;
     public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 집
        int m = Integer.parseInt(st.nextToken()); // 도로

        List<Load> loads = new ArrayList<>();
        parent = new int[n];
        for(int i=0; i<n; i++)
            parent[i] =i;

        int totalPay =0;
        for(int i =0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            Load load = new Load(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            loads.add(load);
            totalPay += load.pay;
        }

        Collections.sort(loads);

        int minPay =0;
        for(Load l : loads){
            // 사이클 발생안하는 조건.
            if(find(l.start) != find(l.end))
            {
                //집합 포함.
                union(l.start, l.end);
                minPay +=l.pay;
            }
        }


        System.out.println(totalPay -minPay);
     }

     public static int find(int a)
     {
        if(parent[a] !=a)
            return find(parent[a]);
        return parent[a];
     }

     public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			parent[y] = x;
		}
	}
}

class Load implements Comparable<Load>{
    int start;
    int end;
    int pay;

    public Load(int start, int end, int pay)
    {
        this.start = start;
        this.end = end;
        this.pay = pay;
    }

    @Override
    public int compareTo(Load l)
    {
        if(this.pay == l.pay)
            return Integer.compare(this.start, l.start);
        return Integer.compare(this.pay, l.pay);
    }
}

