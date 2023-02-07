
import java.util.*;
import java.io.*;
public class Main {
	static int N, S; // 스위치의 개수, 학생 수
	static int[] switches; // 스위치 현재 상태
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		switches = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		S = Integer.parseInt(br.readLine());
		int gender, n; // 학생 성별, 받은 수
		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			changeState(gender, n);
		}
		int line = (N-1)/20;
		for (int i = 0; i < line+1; i++) {
			for (int j = 0; j < N; j++) {
				if(j >= 20) break;
				sb.append(switches[j+20*i]+" ");
			}
			N -= 20;
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	public static void changeState(int g, int n) {
		if(g == 1) { // 남자
			int i = n;
			while(i < N+1) {
				switches[i-1] = (switches[i-1] == 0) ? 1 : 0;
				i += n;
			}
		}else { // 여자
			switches[n-1] = (switches[n-1] == 0) ? 1 : 0;
			int i = 1;
			while(n-i >= 1 && n+i <= N) {
				if(switches[n-i-1] != switches[n+i-1]) break;
				switches[n-i-1] = (switches[n-i-1] == 0) ? 1 : 0;
				switches[n+i-1] = (switches[n+i-1] == 0) ? 1 : 0;
				i++;
			}
		}
	}
}
