import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final String TRUE = "T";
    static final String FALSE = "F";


    static int n, m;
    static int[][] edges;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        edges = new int[26][26];
        char s, e;
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            s = st.nextToken().charAt(0);
            st.nextToken(); // is는 버리기
            e = st.nextToken().charAt(0);
            edges[s-'a'][e-'a'] = 1;
        }

        for (int i = 0; i < 26; i++) {
            setEdges(i);
        }

        m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            s = st.nextToken().charAt(0);
            st.nextToken(); // is는 버리기
            e = st.nextToken().charAt(0);
            if(edges[s-'a'][e-'a'] == 1) sb.append(TRUE);
            else sb.append(FALSE);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    // bfs 로 서로 이어져 있는 경로 있는지 표시
    public static void setEdges(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        int[][] visited = new int[26][26];
        int cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            for (int i = 0; i < 26; i++) {
                // 명제가 이어지지 않거나, 이미 결론을 도출한 경우
                if(edges[cur][i] == 0 || visited[cur][i] == 1) continue;
                edges[s][i] = 1;
                visited[cur][i] = 1;
                q.offer(i);
            }
        }
    }


}