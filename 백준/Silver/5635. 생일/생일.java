import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static class Student implements Comparable<Student>{
        String name;
        int year;
        int month;
        int day;

        public Student(String name, int day, int month, int year) {
            this.name = name;
            this.day = day;
            this.month = month;
            this.year = year;
        }

        @Override
        public int compareTo(Student o) {
            if(this.year != o.year) return this.year - o.year;
            // 년이 같음
            if(this.month != o.month) return this.month - o.month;
            // 월이 같음
            return this.day - o.day;
        }
    }
    static int n;
    static Student[] students;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        students = new Student[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            students[i] = new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(students);
        String youngest, oldest;
        youngest = students[n - 1].name;
        oldest = students[0].name;
        sb.append(youngest + "\n");
        sb.append(oldest + "\n");
        System.out.println(sb.toString());
    }

}