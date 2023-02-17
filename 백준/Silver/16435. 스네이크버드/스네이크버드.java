import java.util.*;
import java.io.*;
public class Main {
	static int N,L;
	static int[] heights;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		heights = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(heights);
		for (int i = 0; i < N; i++) {
			if(heights[i] <= L) L++;
			else break;
		}
		System.out.println(L);
	}
}