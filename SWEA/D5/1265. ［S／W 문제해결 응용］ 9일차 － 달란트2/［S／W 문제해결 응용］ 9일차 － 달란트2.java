import java.util.*;
import java.io.*;
public class Solution {
	static int N, P;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			long max = 1L;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			int n = N/P;
			if(N%P == 0) {
				max = (int)Math.pow(n, P);
			}else {
				int rem = N%P;
				while(rem > 0) {
					P--;
					rem--;
					max *= (n+1);
				}
				while(P > 0) {
					P--;
					max *= n;
				}
			}
			sb.append("#"+test_case+" "+max+"\n");
		}
		System.out.println(sb.toString());
	}

}