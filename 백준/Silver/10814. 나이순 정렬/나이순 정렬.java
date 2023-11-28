import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class User implements Comparable<User> {

        int idx;
        int age;
        String name;

        public User(int idx, int age, String name) {
            this.idx = idx;
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(User o) {
            if(this.age == o.age)
                return this.idx - o.idx;
            return this.age - o.age;
        }

    }


    static int N;
    static List<User> userList = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int age;
        String name;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            age = Integer.parseInt(st.nextToken());
            name = st.nextToken();
            userList.add(new User(i, age, name));
        }
        Collections.sort(userList);
        for (int i = 0; i < N; i++) {
            sb.append(userList.get(i).age).append(" ").append(userList.get(i).name).append("\n");
        }
        System.out.println(sb.toString());
    }



}