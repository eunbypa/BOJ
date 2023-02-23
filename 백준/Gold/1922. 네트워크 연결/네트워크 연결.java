import java.util.*;
import java.io.*;
public class Main {
	static int N,M, min;
	static int[] p;
	static int[] r;
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
			return Integer.compare(this.w, o.w);
		}
		
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N =  Integer.parseInt(br.readLine());
		M =  Integer.parseInt(br.readLine());
		p = new int[N+1];
		r = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			p[i] = i;
			r[i] = 1;
		}
		StringTokenizer st;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int a, b, c;
		for (int i= 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(a,b,c));
		}
		int cnt = 0;
		while(cnt != N-1) {
			Edge e = pq.poll();
			int x = find(e.s);
			int y = find(e.e);
			if(x == y) continue;
			if(x < y) {
				p[x] = y;
			}else {
				p[y] = x;
			}
			cnt++;
			min+= e.w;
		}
		System.out.println(min);
	}

	public static int find(int x) {
		if(x == p[x]) return x;
		return p[x] = find(p[x]);
	}
}