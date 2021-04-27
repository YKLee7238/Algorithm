import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Sort {
     public static void main(String[] args) throws Exception{
        List<Student> students = new ArrayList<>();
        /*
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=0; i<n; i++){
            Student st = new Student(sc.next());
            st.korean = sc.nextInt();
            st.english = sc.nextInt();
            st.math = sc.nextInt();
            
            students.add(st);
        }

        sc.close();*/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            Student st = new Student(stk.nextToken(), Integer.parseInt(stk.nextToken()),
                    Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
            students.add(st);
        }

        
        Collections.sort(students, new Comparator<Student>(){
            public int compare(Student x, Student y)
            {
                int result =0;
                result = Integer.compare(y.korean, x.korean); // 국어 내림차순
                if(result !=0)
                    return result;
                
                result = Integer.compare(x.english, y.english); // 영어 오름차순
                if(result !=0)
                    return result;

                result = Integer.compare(y.math, x.math); // 수학 내림차순
                if(result !=0)
                    return result;
                
                return x.name.compareTo(y.name);
            }
        });
        
        for(Student s : students){
            System.out.println( s.name);
        }
    }
}

class Student{
    public String name;
    public int korean;
    public int english;
    public int math;

    public Student(String name){
        this.name = name;
    }
    public Student(String name, int korean, int english, int math){
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

}
