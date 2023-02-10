import java.util.*;
import java.io.*;
// 백준 DNA 비밀번호
public class Main {
	static int S,P;
	static char[] dna;
	static int[] dp;
	static int[] req;
	static int ans;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		dp = new int[4];
		req = new int[4];
		dna = br.readLine().toCharArray();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			req[i] = Integer.parseInt(st.nextToken());
		}
		findPassword();
		System.out.println(ans);
	}

	public static void findPassword() {
		int s = 0;
		int e = P-1;
		//초기 상태 설정
		for (int i = 0; i < P; i++) {
			dp[getIdx(dna[i])]++;
		}
		if(isAns(s)) ans++;
		while(e < S) {
			s++;
			e++;
			if(e == S) break;
			dp[getIdx(dna[s-1])]--;
			dp[getIdx(dna[e])]++;
			if(isAns(s)) ans++;
		}
	}
	public static boolean isAns(int idx) {
		for (int i = 0; i < 4; i++) {
			if(req[i] > dp[i]) return false;
		}
		return true;
	}
	public static int getIdx(char c) {
		if(c == 'A') {
			return 0;
		}else if(c == 'C') {
			return 1;
		}else if(c == 'G') {
			return 2;
		}return 3;
	}
}
