import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<Integer>[] edges;
    static boolean[] visited;
    static boolean find;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }
        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            visited[i] = true;
            check(i, 1);
            visited[i] = false;
            if(find) break; // 찾은 경우 탐색 중단
        }
        if(find) System.out.println(1);
        else System.out.println(0);
    }

    // dfs로 조건에 맞는 A,B,C,D,E 관계를 찾는 함수
    // 찾으면 더이상 탐색 X
    public static void check(int cur, int cnt) {
        if(find) return;
        if(cnt == 5){
            find = true;
            return;
        }
        int size = edges[cur].size();
        int next;
        for (int i = 0; i < size; i++) {
            next = edges[cur].get(i);
            if(visited[next]) continue;
            visited[next] = true;
            check(next, cnt+1);
            visited[next] = false;
        }
    }
}
