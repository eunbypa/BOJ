import java.util.*;
import java.io.*;

public class Main{
	static int N,S, cnt;
	static int[] nums;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		nums = new int[N];
		st =new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] =Integer.parseInt(st.nextToken());
			
		}
		subset(0,0);
		if(S == 0) cnt--;
		System.out.println(cnt);
	}
	public static void subset(int cur, int sum) {
		if(cur == N) {
			if(sum == S) cnt++;
			return;
		}
		subset(cur+1, sum+nums[cur]);
		subset(cur+1, sum);
	}
}
