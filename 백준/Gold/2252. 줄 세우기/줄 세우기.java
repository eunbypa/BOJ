import java.util.*;
import java.io.*;
public class Main {
	static int N,M;
	static int[] degrees;
	static int[] lines;
	static List<Integer>[] edges;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N+1];
		degrees = new int[N+1];
		lines= new int[N+1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		int f,s;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken());
			s =  Integer.parseInt(st.nextToken());
			degrees[s]++;
			edges[f].add(s); // f는 s보다 앞에 서야 함
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if(degrees[i] == 0) {
				q.offer(i); // 차수가 0이면 큐에 삽입 
				degrees[i] = -1;
			}
		}
		int cnt = 0,cur, size, next; // 줄 세운 사람 수
		while(cnt != N) {
			cnt++;
			cur = q.poll();
			lines[cnt] = cur;
			size = edges[cur].size();
			for (int i = 0; i < size; i++) {
				next = edges[cur].get(i);
				degrees[next]--;
				if(degrees[next] == 0) {
					q.offer(next); // 차수가 0이면 큐에 삽입 
					degrees[next] = -1;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			sb.append(lines[i]);
			if(i < N) sb.append(" ");
		}
		System.out.println(sb.toString());
	}
}