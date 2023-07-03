import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] list; // 서류 성적을 기준으로 1등부터 차례대로 해당하는 면접 성적을 저장하는 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            list = new int[N+1]; // idx : 서류 순위, list[idx] : 면접 순위
            int dRank, iRank; // 서류성적, 면접성적
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                dRank = Integer.parseInt(st.nextToken());
                iRank = Integer.parseInt(st.nextToken());
                list[dRank] = iRank;
            }
            int limit = list[1]; // 서류 1등의 면접 성적보다 높은 성적을 가져야 함
            int cnt = 1; // 1등은 자동 포함
            for (int i = 2; i <= N; i++) {
                if(list[i] > limit) continue;
                limit = list[i];
                cnt++;
            }
            sb.append(cnt+"\n");
        }
        System.out.println(sb.toString());
    }

}