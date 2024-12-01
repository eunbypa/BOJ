import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge {
        int start;
        int end;
        int val;

        public Edge(int start, int end, int val) {
            this.start = start;
            this.end = end;
            this.val = val;
        }
    }

    static final long INF = Integer.MAX_VALUE;
    static final int INFINITE = -1;
    static final int NO_WAY = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int A, B, C;
        List<Edge> edgeList = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            // A->B 버스 경로가 여러개 있는 경우 최단 거리만 저장
            edgeList.add(new Edge(A, B, C));
        }

        System.out.println(getAnswer(edgeList, N, M));
    }

    // 벨만포드 알고리즘
    static StringBuilder getAnswer(List<Edge> edgeList, int N, int M) {
        StringBuilder sb = new StringBuilder();
        long[] dp = new long[N + 1];

        if(hasInfiniteCycle(edgeList, dp, N, M)) {
            sb.append(INFINITE);
        }
        else {
            long len;
            for (int i = 2; i <= N; i++) {
                len = (dp[i] == INF) ? NO_WAY : dp[i];
                sb.append(len).append("\n");
            }
        }

        return sb;
    }

    // 최단 경로가 무한정 갱신되는 사이클이 존재하는지 검사
    static boolean hasInfiniteCycle(List<Edge> edgeList, long[] dp, int N, int M) {
        init(dp, INF);
        dp[1] = 0;

        Edge cur;
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                cur = edgeList.get(j);

                // 1번 도시에서 갈 수 없는 도시는 생략
                if(dp[cur.start] == INF) continue;
                if(dp[cur.start] + cur.val >= dp[cur.end]) continue;
                dp[cur.end] = dp[cur.start] + cur.val;
                if(i == N) {
                    // 최단경로가 무한 갱신되는 사이클 존재함을 의미
                    return true;
                }
            }
        }

        return false;
    }

    static void init(long[] arr, long value) {
        int r = arr.length;
        for (int i = 0; i < r; i++) {
            arr[i] = value;
        }
    }

}