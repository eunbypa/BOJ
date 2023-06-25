import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Integer> list = new ArrayList<>();
    static List<Integer> sorted = new ArrayList<>();
    static Map<Integer, Integer> map = new HashMap<>(); // 정렬되었을 때 위치 저장용
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X;
        for (int i = 0; i < N; i++) {
            X = Integer.parseInt(st.nextToken());
            list.add(X); // 순서대로 Xn 저장
            sorted.add(X);
        }
        int cnt = 0;
        Collections.sort(sorted);
        for (int i = 0; i < N; i++) {
            X = sorted.get(i);
            if(!map.containsKey(X)) map.put(X, cnt++);
        }

        for (int i = 0; i < N; i++) {
            sb.append(map.get(list.get(i)));
            if(i == N-1) continue;
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }


}