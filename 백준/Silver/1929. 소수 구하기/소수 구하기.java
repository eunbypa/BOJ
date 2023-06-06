import java.util.*;
import java.io.*;
public class Main {
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		for (int i = M; i <= N; i++) {
			if(isPrime(i)) sb.append(i+"\n");
		}
		System.out.println(sb.toString());
	}
	//에라토스테네스의 체
	static boolean isPrime(int n) {
		if(n == 1) return false;
		for (int i = 2; i*i <= n; i++) {
			if(n % i == 0) return false; // 소수가 아님
		}
		return true;
	}
}
