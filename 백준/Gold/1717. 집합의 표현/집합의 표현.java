import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[] parents; // 각 노드의 부모 인덱스
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parents = new int[n+1];
        for (int i = 0; i < n+1; i++) {
			parents[i] = i;
		}
		int inst, a, b; // inst : 명령
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			inst = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if(inst == 0) {
				mergeSet(a, b);
			}else {
				if(isSameSet(a, b)) System.out.println("YES");
				else System.out.println("NO");
			}
		}
	}
	public static void mergeSet(int a, int b) { // 합집합
		int pa = findParent(a);
		int pb = findParent(b);
		if(pa < pb) {
			parents[pb] = parents[pa];
		}
		else if(pa > pb) {
			parents[pa] = parents[pb];
		}
	}
	public static int findParent(int n) {
		if(n == parents[n]) return n;
		return parents[n] = findParent(parents[n]);
	}
	public static boolean isSameSet(int a, int b) { // 같은 집합인지 찾기
		if(findParent(a) == findParent(b)) return true;
		return false;
	}

}
