import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int e; // 교차로 끝점
        int w; // 가중치

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n, m, tCnt; // 교차로, 도로, 목적지 후보 개수
        int s, g, h; // 출발지, 예술가들이 지나간 교차로 양 끝점
        int a, b, d; // 교차로 a와 b 사이 길이 d의 도로
        int x; // 목적지 후보
        List<Edge>[] edgeList; // 각 교차로의 도로 리스트
        List<Integer> targetList; // 목적지 후보 리스트
        int[] sDist; // s에서 다른 교차로까지의 최단거리 배열
        int[] gDist; // g에서 다른 교차로까지의 최단거리 배열
        int[] hDist; // h에서 다른 교차로까지의 최단거리 배열

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            tCnt = Integer.parseInt(st.nextToken());

            edgeList = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                edgeList[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());
                // 양방향 도로이므로 각각 추가
                edgeList[a].add(new Edge(b, d));
                edgeList[b].add(new Edge(a, d));
            }

            targetList = new ArrayList<>();
            for (int i = 0; i < tCnt; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                targetList.add(x);
            }

            sDist = new int[n + 1];
            dijkstra(s, sDist, edgeList);
            gDist = new int[n + 1];
            dijkstra(g, gDist, edgeList);
            hDist = new int[n + 1];
            dijkstra(h, hDist, edgeList);

            // 목적지 후보 순회하면서, g 또는 h를 지나는 게 최단거리 경로의 일부인 경우 찾기
            int gTohD = 0;
            int size = edgeList[g].size();
            for (int i = 0; i < size; i++) {
                if(edgeList[g].get(i).e == h) {
                    gTohD = edgeList[g].get(i).w;
                    break;
                }
            }
            List<Integer> answerList = new ArrayList<>();
            int cur;
            for (int i = 0; i < tCnt; i++) {
                cur = targetList.get(i);
                if(lessEqDist(cur, g, h, gTohD, sDist, hDist) ||
                        lessEqDist(cur, h, g, gTohD, sDist, gDist))
                    answerList.add(cur);
            }
            Collections.sort(answerList);
            size = answerList.size();
            for (int i = 0; i < size; i++) {
                sb.append(answerList.get(i));
                if(i == size-1) break;
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // a에서 b를 지나가는게 goal까지의 최단거리에 속하는지 확인
    public static boolean lessEqDist(int goal, int a, int b, int aToB, int[] dist1, int[] dist2) {
        return (dist1[a] + aToB + dist2[goal]) <= dist1[goal];
    }



    // 다익스트라
    public static void dijkstra(int s, int[] dist, List<Edge>[] edgeList) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(s, 0));
        initDist(dist);
        dist[s] = 0;
        Edge cur, next;
        int size;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            if (cur.w > dist[cur.e]) // e 교차로까지의 최단거리보다 큰 경우 스킵
                continue;
            size = edgeList[cur.e].size();
            for (int i = 0; i < size; i++) {
                next = edgeList[cur.e].get(i);
                if(dist[cur.e] + next.w < dist[next.e]) {
                    dist[next.e] = dist[cur.e] + next.w;
                    pq.add(new Edge(next.e, dist[next.e]));
                }
            }
        }
    }

    // dist 배열 초기값 설정
    public static void initDist(int[] dist) {
        int maxD = 2000001;
        int l = dist.length;
        for (int i = 0; i < l; i++) {
            dist[i] = maxD;
        }
    }



}