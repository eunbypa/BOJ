import java.util.*;
import java.io.*;
public class Main {
	static class Edge implements Comparable<Edge>{
		int s;
		int e;
		int w;
		public Edge(int s, int e, int w) {
			super();
			this.s = s;
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.w, o.w); // 오버플로우 방지, Integer.compare 쓰는게 안전
		}
		
	}
	static int V,E;
	static long min;
	static int[] p;
	//static List<Edge>[] edges;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V =  Integer.parseInt(st.nextToken());
		E =  Integer.parseInt(st.nextToken());
		p = new int[V+1];
		for (int i = 0; i < V+1; i++) {
			p[i] = i;
		}
		//edges = new ArrayList[V];
		int a,b,c;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			/*if(edges[a] == null) edges[a] = new ArrayList<>();
			if(edges[b] == null) edges[b] = new ArrayList<>();
			edges[a].add(new Edge(a,b,c));
			edges[b].add(new Edge(b,a,c));*/
			pq.offer(new Edge(a,b,c));
		}
		kruskal();
		System.out.println(min);
	}
	public static void kruskal() {
		int cnt =0;
		while(cnt != V-1) {
			Edge e = pq.poll();
			int px = find(e.s);
			int py = find(e.e);
			if(px==py) continue;
			if(px < py) {
				p[py] = px;
			}else {
				p[px] = py;
			}
			min+=e.w;
			cnt++;
		}
	}
	public static int find(int x) {
		if(x==p[x]) return x;
		return p[x] = find(p[x]);
	}
}