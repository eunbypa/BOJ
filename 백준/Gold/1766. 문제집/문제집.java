import java.util.*;
import java.io.*;
public class Main {
	static int N,M;
	static int[] degrees;
	static List<Integer>[] edges;
	static List<Integer> orders; // 각 순서에 들어가는 문제들
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N =  Integer.parseInt(st.nextToken());
		M =  Integer.parseInt(st.nextToken());
		degrees = new int[N+1];
		edges = new ArrayList[N+1];
		orders = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a =  Integer.parseInt(st.nextToken());
			b=  Integer.parseInt(st.nextToken());
			edges[a].add(b); // a는 b보다 먼저 풀어야 함
			degrees[b]++;
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		//위상정렬 + 우선순위 큐?
		//degree가 0인 것이 제일 먼저 우선순위큐에 들어간다
		int size;
		for (int i = 1; i <= N; i++) {
			if(degrees[i] == 0) pq.offer(i);
		}
		int cur;
		
		while(!pq.isEmpty()) {
			cur = pq.poll();
			orders.add(cur);
			size = edges[cur].size();
			for (int i = 0; i < size; i++) {
				degrees[edges[cur].get(i)]--;
				if(degrees[edges[cur].get(i)] == 0) {
					pq.offer(edges[cur].get(i));
				}
			}
		}
		for (int i = 0; i < N; i++) {
			sb.append(orders.get(i));
			if(i < N-1) sb.append(" ");
		}
		System.out.println(sb.toString());
	}

}