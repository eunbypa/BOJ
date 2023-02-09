import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int[] nums;
	static int[] p = {2,3,5,7};
	static int[] q = {1,3,5,7,9};
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dfs(0, 0);
		System.out.println(sb.toString());
	}

	public static void dfs(int curNum, int n) {
		if(n == N) {
			sb.append(curNum +"\n");
			return;
		}
		int nextNum = 0;
		if(n == 0) {
			for (int i = 0; i < 4; i++) {
				nextNum = curNum*10+p[i];
				if(!isPrime(nextNum)) continue;
				dfs(nextNum, n+1);
			}
		}else {
			for (int i = 0; i < 5; i++) {
				nextNum = curNum*10+q[i];
				if(!isPrime(nextNum)) continue;
				dfs(nextNum, n+1);
			}
		}
	}
	public static boolean isPrime(int n) {
		for (int i = 2; i<=(int)(Math.sqrt(n)); i++) {
			if(n % i == 0) return false;
		}
		return true;
	}
}
