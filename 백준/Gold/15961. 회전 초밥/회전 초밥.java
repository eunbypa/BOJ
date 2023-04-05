import java.util.*;
import java.io.*;
public class Main {
	static int N,d,k,c, max;
	static int[] dp; // i부터 k 범위까지의 중복이 아닌 초밥의 총 개수
	static int[] sushiCnt; // k 범위 내 각 초밥의 개수
	static int[] sushi; // 회전 초밥 벨트에 놓인 초밥들
	static boolean coupoun; // 쿠폰이 포함되었으면 true
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N =  Integer.parseInt(st.nextToken());
		d =  Integer.parseInt(st.nextToken());
		k =  Integer.parseInt(st.nextToken());
		c =  Integer.parseInt(st.nextToken());
		sushi = new int[N];
		sushiCnt = new int[d+1];
		dp = new int[N];
		int n;
		for (int i = 0; i < N; i++) {
			n = Integer.parseInt(br.readLine());
			sushi[i] = n;
			// 슬라이딩 윈도우를 위한 초기값 0~k 범위내 초밥 개수 세팅
			if(i < k) {
				sushiCnt[n]++;
				if(sushiCnt[n] == 1) dp[0]++; // 중복이 아닌 초밥이 들어오면 개수 1증가
			}
		}
		// 쿠폰번호에 해당하는 초밥을 먹은 경우 or 안먹은 경우
		if(sushiCnt[c] == 0) { // 안먹은 경우
			dp[0]++; //먹을 수 있는 종류가 하나 더 추가됨
		}
		max = dp[0]; // 초기 최댓값 세팅
		/*System.out.println("0 번째에서 시작, 초밥 개수 상태");
		System.out.println(Arrays.toString(sushiCnt));
		System.out.println("dp 값 : " +dp[0]);*/
		slidingWindow();
		System.out.println(max);
	}
	static void slidingWindow() {
		int b, a; // 전 후 초밥
		int next;
		for (int i = 1; i < N; i++) {
			b = sushi[i-1];
			sushiCnt[b]--; // 그 전에 있던 초밥 1개 감소
			if(sushiCnt[b] == 0) { // 초밥 종류가 하나 줄어듦
				dp[i] = dp[i-1]-1;
			}else dp[i] = dp[i-1];
			if(b != c && sushiCnt[c] == 0) dp[i]--;
			next = i+k-1;
			if(next >= N) { // index가 N을 넘어가면
				a = sushi[next-N];
				sushiCnt[a]++;
			}else {
				a = sushi[next];
				sushiCnt[a]++;
			}
			if(sushiCnt[a] == 1) { // 초밥 종류가 하나 추가됨
				dp[i]++;
			}
			//쿠폰 번호에 해당되는 초밥을 먹었는지?
			if(sushiCnt[c] == 0) { // 안먹은 경우
				dp[i]++; //먹을 수 있는 종류가 하나 더 추가됨
			}
			/*System.out.println(i+" 번째에서 시작, 초밥 개수 상태");
			System.out.println(Arrays.toString(sushiCnt));
			System.out.println("dp 값 : " +dp[i]);*/
			max = Math.max(dp[i], max);
		}
	}
}