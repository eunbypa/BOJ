import java.util.*;
import java.io.*;
public class Main {
	static int N,r,c, ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		divide(0,0,N,0);
		
		System.out.println(ans);
	}
	public static void divide(int rr, int cc, int n, int order) {
		if(rr == r && cc == c) {
			ans = order;
			return;
		}
		int length = 1 << (n-1);
		int cnt = length*length;
		if(r < rr+length) {
			if(c < cc+length) {
				divide(rr, cc, n-1, order);
			}else {
				divide(rr, cc+length, n-1, order+cnt);
			}
		}else {
			if(c < cc+length) {
				divide(rr+length, cc, n-1, order+cnt*2);
			}else {
				divide(rr+length, cc+length, n-1, order+cnt*3);
			}
		}
	}
}