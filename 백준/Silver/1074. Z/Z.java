import java.util.*;
import java.io.*;
public class Main {
	static int N, r, c, ans;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		//orders = new int[l][l];
		findVisitOrder(N, 0);
		System.out.println(ans);
	}
	public static void findVisitOrder(int n, int order) {
		if(n == 0) {
			ans = order;
			//System.out.println(cr+" "+cc + " "+order );
			return;
		}
		int cnt = 1<< (2*(n-1));
		int jmp = 1<< (n-1);
		//System.out.println(cnt+" "+jmp);
		if(r >= jmp) {
			r -= jmp;
			if(c >= jmp) {
				c -= jmp;
				//System.out.println(order+cnt*3);
				findVisitOrder(n-1, order+cnt*3);
			}else {
				//System.out.println(order+cnt*2);
				findVisitOrder(n-1, order+cnt*2);
			}
		}else {
			if(c >= jmp) {
				c -= jmp;
				//System.out.println(order+cnt*1);
				findVisitOrder(n-1, order+cnt*1);
			}else {
				//System.out.println(order);
				findVisitOrder(n-1, order);
			}
		}
	}
}
