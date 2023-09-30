import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Student implements Comparable<Student>{

        String name;

        int korean;

        int english;

        int math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }


        @Override
        public int compareTo(Student o) {
            if(this.korean > o.korean) return -1;
            else if(this.korean == o.korean){
                if(this.english < o.english) return -1;
                else if(this.english == o.english){
                    if(this.math > o.math) return -1;
                    else if(this.math == o.math){
                        return this.name.compareTo(o.name);
                    }
                    return 1;
                }
                return 1;
            }
            return 1;
        }
    }


    static int N;
    static List<Student> studentList;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        studentList = new ArrayList<>();
        String name;
        int korean, english, math;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            name = st.nextToken();
            korean = Integer.parseInt(st.nextToken());
            english = Integer.parseInt(st.nextToken());
            math = Integer.parseInt(st.nextToken());
            Student student = new Student(name, korean, english, math);
            studentList.add(student);
        }
        Collections.sort(studentList);
        for (Student student : studentList) {
            sb.append(student.name).append("\n");
        }
        System.out.println(sb.toString());
    }



}