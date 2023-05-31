import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] nums; // 주어진 숫자
	static int[] operators; // 주어진 연산자 각 개수
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		operators = new int[4]; // 순서대로 +, -, *, /
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, nums[0]);
		System.out.println(max);
		System.out.println(min);
	}
	// 연산자 배치 순열 경우의 수 모두 파악하기
	static void dfs(int cnt, int result) { // 현재 배치 완료한 연산자 갯수, 연산 결과
		if(cnt == N-1) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if(operators[i] == 0) continue;
			operators[i]--;
			dfs(cnt+1, calculate(result, nums[cnt+1], i));
			operators[i]++;
		}
	}
	//연산 결과를 반환하는 함수
	private static int calculate(int cur, int next, int op) {
		// TODO Auto-generated method stub
		int result = 0;
		switch (op) {
		case 0: // 덧셈
			result = cur + next;
			break;
		case 1: // 뺄셈
			result = cur - next;
			break;
		case 2: // 곱셈
			result = cur * next;
			break;
		case 3: // 나눗셈
			result = cur / next;
			break;
		}
		return result;
	}
}
