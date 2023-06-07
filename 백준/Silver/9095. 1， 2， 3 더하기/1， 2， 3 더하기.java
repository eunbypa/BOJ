import java.util.*;
import java.io.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            cnt = 0;
            n = Integer.parseInt(br.readLine());
            dfs(n);
            sb.append(cnt+"\n");
        }
        System.out.println(sb.toString());
    }
    // 순열
    static void dfs(int rem){ // rem: 남은 수
        if(rem==0){
            cnt++;
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if(rem < i) break; // 남은 숫자보다 더하려는 숫자가 더 크면 불가능
            dfs(rem-i);
        }
    }

}