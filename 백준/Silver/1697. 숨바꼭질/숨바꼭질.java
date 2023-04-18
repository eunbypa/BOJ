import java.util.*;
import java.io.*;
public class Main {
	static int N,K;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N =  Integer.parseInt(st.nextToken());
		K =  Integer.parseInt(st.nextToken());
		BFS();
	}

	private static void BFS() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {N,0});
		 visited = new boolean[200001];
		 visited[N] = true;
		int[] cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			if(cur[0] == K) {
				System.out.println(cur[1]);
				return;
			}
			if(cur[0]*2 < 200001 && !visited[cur[0]*2]) {
				visited[cur[0]*2] = true;
				q.offer(new int[] {cur[0]*2, cur[1]+1});
			}
			if(cur[0]+1 < 200001 && !visited[cur[0]+1]) {
				visited[cur[0]+1] = true;
				q.offer(new int[] {cur[0]+1, cur[1]+1});
			}
			if(cur[0]-1 >= 0 && cur[0]-1 < 200001 && !visited[cur[0]-1]){
				visited[cur[0]-1] = true;
				q.offer(new int[] {cur[0]-1, cur[1]+1});
			}
		}
	}
}