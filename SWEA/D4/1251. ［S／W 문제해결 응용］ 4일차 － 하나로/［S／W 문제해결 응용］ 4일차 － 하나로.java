import java.util.*;
import java.io.*;
//최소 신장 트리 문제
public class Solution {
	static class Edge implements Comparable<Edge>{
		int s;
		int e;
		long cost;
		public Edge(int s, int e, long cost) {
			super();
			this.s = s;
			this.e = e;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Long.compare(this.cost,o.cost);
		}
		
	}
	static int N;
	static long min;
	static int[][] loc;
	static long[][] edges;
	static boolean[] visited;
	static Double tax;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// st = new StringTokenizer(br.readLine());
		// Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			loc=new int[N][2];
			edges = new long[N][N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				loc[i][0] =  Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				loc[i][1] =  Integer.parseInt(st.nextToken());
			}
			tax = Double.parseDouble(br.readLine());
			long x, y;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					x =  Math.abs(loc[i][0]-loc[j][0]);
					y =  Math.abs(loc[i][1]-loc[j][1]);
					edges[i][j] = (long)((long)(x*x)+(long)(y*y));
				}
			}
			prim();
			sb.append("#"+test_case+" "+min+"\n");
		}
		System.out.println(sb.toString());
	}
	static void prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		visited = new boolean[N];
		pq.offer(new Edge(0,0,0));
		Edge edge;
		long length = 0L;
		while(!pq.isEmpty()) {
			edge = pq.poll();
			if(visited[edge.e]) continue;
			visited[edge.e] = true;
			length += edge.cost;
			for (int i = 0; i < N; i++) {
				if(edges[edge.e][i] == 0 || visited[i]) continue; // 간선이 없음
				pq.offer(new Edge(edge.e,i,edges[edge.e][i]));
			}
		}
		min = Math.round(length*tax);
	}

}