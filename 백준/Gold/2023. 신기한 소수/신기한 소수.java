import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int[] nums;
	static int[] p;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dfs(0, 0);
		/*nums = new int[10];
		p = new int[10];
		for (int i = 0; i < 10; i++) {
			nums[i] = i;
			if(10-i >= N) p[i] = 1;
		}
		do {
			int num = 0;
			int ten = 1;
			boolean ans = true;
			for (int i = 0; i < 10; i++) {
				if(p[i] == 1) num = num*ten + nums[i];
				ten *= 10;
				if(isPrime(num)) {
					ans = false;
					break;
				}
			}
			if(ans) {
				sb.append(num +"\n");
			}
		}while(nextComb());*/
		System.out.println(sb.toString());
	}
	/*public static boolean nextComb() {
		int i = 9;
		int j = 9;
		while(i > 0 && p[i-1] >= p[i]) i--;
		if(i <= 0) return false;
		while(p[i-1] >= p[j]) j--;
		int tmp = p[i-1];
		p[i-1] = p[j];
		p[j] = tmp;
		while(i<j) {
			tmp = p[i];
			p[i] = p[j];
			p[j] = tmp;
			i++;
			j--;
		}
		return true;
	}*/
	public static void dfs(int curNum, int n) {
		if(n == N) {
			sb.append(curNum +"\n");
			return;
		}
		int nextNum = 0;
		for (int i = 0; i < 10; i++) {
			if(n == 0 && i == 0) continue; // 처음 자리에 0은 올 수 없다
			if(n == 0) {
				if(i ==  1 || i == 4 || i == 6 || i == 8 || i == 9) continue;
			}
			nextNum = curNum*10+i;
			//System.out.println(nextNum);
			if(!isPrime(nextNum)) continue;
			dfs(nextNum, n+1);
		}
	}
	public static boolean isPrime(int n) {
		for (int i = 2; i<=(int)(Math.sqrt(n)); i++) {
			if(n % i == 0) return false;
		}
		return true;
	}
}
