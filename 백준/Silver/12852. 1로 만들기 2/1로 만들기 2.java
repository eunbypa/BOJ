import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node{
        int n;
        List<Integer> list; // N을 n으로 만들기까지 거쳐온 수를 저장

        public Node(int n, List<Integer> list) {
            this.n = n;
            this.list = list;
        }
    }

    static int N;
    static StringBuilder sb = new StringBuilder();
    static int[] dist; // 출발도시에서 다른 도시까지의 최단 거리 저장
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bfs();
        System.out.println(sb.toString());
    }

    // 너비 우선 탐색
    public static void bfs() {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        List<Integer> list = new ArrayList<>();
        visited[N] = true;
        list.add(N);
        q.offer(new Node(N, new ArrayList<>(list)));
        Node cur;
        int size, n;
        while(!q.isEmpty()){
            cur = q.poll();
            if(cur.n == 1){ // 연산 종료
                // bfs의 특성상 연산 횟수 최소 보장
                size = cur.list.size();
                sb.append((size - 1) + "\n");
                for (int i = 0; i < size; i++) {
                    sb.append(cur.list.get(i));
                    if(i == size - 1) continue;
                    sb.append(" ");
                }
                return;
            }
            // 3으로 나누어 떨어지므로 3으로 나눈다
            if(cur.n % 3 == 0){
                n = cur.n / 3;
                if(!visited[n]) {
                    visited[n] = true;
                    list = new ArrayList<>(cur.list);
                    list.add(n);
                    q.offer(new Node(n, new ArrayList<>(list)));
                }
            }
            // 2로 나누어 떨어지므로 2로 나눈다
            if(cur.n % 2 == 0){
                n = cur.n / 2;
                if(!visited[n]) {
                    visited[n] = true;
                    list = new ArrayList<>(cur.list);
                    list.add(n);
                    q.offer(new Node(n, new ArrayList<>(list)));
                }
            }
            // 1을 뺀다
            n = cur.n - 1;
            if(!visited[n]){
                visited[n] = true;
                list = new ArrayList<>(cur.list);
                list.add(n);
                q.offer(new Node(n, new ArrayList<>(list)));
            }
        }
    }
}