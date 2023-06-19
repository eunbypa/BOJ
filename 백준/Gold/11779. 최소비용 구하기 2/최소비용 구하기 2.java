import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int n; // 도시 번호
        int cost; // 출발도시에서 해당 도시까지 가는데 소요한 비용 dist
        List<Integer> list; // 경로에 포함된 도시 번호 저장

        public Node(int n, int cost) {
            this.n = n;
            this.cost = cost;
            this.list = new ArrayList<>();
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    static final int INF = 1000000000; // INF
    static int n, m, start, end; // 도시의 개수, 버스의 개수, 출발지, 도착지
    static List<int[]>[] edges;
    static List<Integer> list; // 도착 도시까지 최소 비용을 갖는 경로 저장
    static StringBuilder sb = new StringBuilder();
    static int[] dist; // 출발도시에서 다른 도시까지의 최단 거리 저장
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        edges = new ArrayList[n+1];
        dist = new int[n+1];
        int[][] edges2 = new int[n+1][n+1]; // 한도시에서 다른 도시까지의 경로가 여러개 존재할 수 있으므로 최솟값을 가지는 경로만 저장하기 위함
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
            dist[i] = INF; // 최댓값으로 초기화
            Arrays.fill(edges2[i], -1);
        }
        StringTokenizer st;
        int s, e, c; // 출발도시, 도착도시, 버스 비용
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(edges2[s][e] == -1 || edges2[s][e] > c) edges2[s][e] = c;
//            edges[s].add(new int[]{e, c});
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(edges2[i][j] == -1) continue; // 간선 없음
                edges[i].add(new int[]{j, edges2[i][j]});
            }
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        if(start != end) dijkstra(new Node(start, 0));
        if(start == end){ // 출발지와 도착지가 모두 자기자신인 경우
            list = new ArrayList<>();
            list.add(start);
        }
        sb.append(dist[end]+"\n"); // 최소 비용
        int size = list.size();
        sb.append(size+"\n"); // 최소 비용을 갖는 경로에 포함된 도시 개수
        for (int i = 0; i < size-1; i++) {
            sb.append(list.get(i)+" "); // 최소 비용을 갖는 경로에 포함된 도시 개수
        }
        sb.append(list.get(size - 1));
        System.out.println(sb.toString());
    }

    // 다익스트라
    static void dijkstra(Node s){ // s : 출발 도시 노드
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int size;
        int[] edge;
        Node cur, next;
        dist[start] = 0; // 출발지에서 자기자신까지의 최소 비용은 무조건 0
        s.list.add(start); // 출발점의 경로에 포함된 도시는 출발 도시 자기 자신
        pq.offer(s);
        while(!pq.isEmpty()){
            cur = pq.poll();
            if(cur.n == end) continue; // 도착 도시에 도달한 상태이므로 스킵
            if(cur.cost > dist[cur.n]) continue; // 최단 거리 갱신될 가능성이 없으므로 스킵
            size = edges[cur.n].size();
            for (int i = 0; i < size; i++) {
                edge = edges[cur.n].get(i);
                // 현재 위치한 도시에서 갈 수 있는 도시들 -> edge[0]
                // 해당 도시까지의 dist 값을 현재 도시까지의 dist 값에 해당 도시까지의 비용을 더한 값과 비교
                // dist가 최소값으로 갱신되는 경우 pq에 삽입
                if(dist[edge[0]] > dist[cur.n] + edge[1]){
                    dist[edge[0]] = dist[cur.n] + edge[1];
                    next = new Node(edge[0], dist[edge[0]]);
                    next.list = new ArrayList<>(cur.list); // 현재 도시까지 거쳐온 경로를 그대로 가져옴
                    next.list.add(edge[0]); // 경로 마지막에 다음 도시를 추가
                    pq.offer(next); // pq에 다음 도시 노드를 삽입
                    //다음 도시가 최종 도착 도시인 경우
                    if(edge[0] == end){
                        list = new ArrayList<>(next.list);
                    }
                }
            }
        }
    }


}