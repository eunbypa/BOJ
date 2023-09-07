import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        int[] arr = new int[2001]; // -1000 ~ 1000 (+1000) 더한 값
        int n;
        for (int i = 0; i < N; i++) {
            n = Integer.parseInt(st.nextToken());
            if(arr[n+1000] == 1) continue; // 중복
            arr[n+1000] = 1;
            list.add(n);
        }
        Collections.sort(list);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append(list.get(i));
            if(i == size-1) break;
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

}