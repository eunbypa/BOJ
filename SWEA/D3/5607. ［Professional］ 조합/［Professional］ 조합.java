import java.util.*;
import java.io.*;

public class Solution {
	static int N,R;
	static int mod = 1234567891;
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
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			//페르마의 소정리
			int k = mod-2;
			long a = 1L;
			long res = 1L;
			for (int i = R+1; i <= N; i++) {
				res = (res*i)%mod;
			}
			for (int i = 1; i <= N-R; i++) {
				a = (a*i)%mod;
			}
			while(k > 0) { // 분할정복을 이용한 거듭제곱
				if(k%2 == 1) {
					res =(res*a)%mod;
				}
				a = (a*a)%mod;
				k /=2;
			}
			sb.append("#"+test_case+" "+res+"\n");
		}
		System.out.println(sb.toString());
	}
	

}