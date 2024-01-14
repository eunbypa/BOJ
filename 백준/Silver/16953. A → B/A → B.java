import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int A,B;
    static int min;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        dfs(A, 1);
        if(min == Integer.MAX_VALUE) min = -1;
        System.out.println(min);
    }

    //dfs
    public static void dfs(long cur, int cnt) {
        if(cnt >= min) return;
        if(cur > B) return;
        if(cur == B) {
            min = Math.min(min, cnt);
            return;
        }
        dfs(cur*2, cnt+1);
        dfs(cur*10+1, cnt+1);
    }



}