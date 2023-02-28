import java.util.*;
import java.io.*;
public class Main {
	static int n;
	static int a, b; // 다른 봉우리에 의해 포함되지 않는 봉우리, 다른 봉우리를 포함하지 않는 봉우리
	static List<int[]> bonguri; // 봉우리의 시작점-끝점 쌍을 저장
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		bonguri = new ArrayList<>();
		StringTokenizer st;
		int[][] dots = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			dots[i][0] = Integer.parseInt(st.nextToken());
			dots[i][1] = Integer.parseInt(st.nextToken());
		}
		int start = 0;
		for (; start < n-1; start++) {
			if(dots[start][1] < 0 && dots[start+1][1] > 0) break;
		}
		if(dots[n-1][1] < 0 && dots[0][1] > 0) start = n-1;
		int curY, prevY = dots[start][1] > 0 ? 1 : -1;
		boolean up = false;
		int x1 = 0, x2 = 0;
		int k = start+1;
		for (int i = 0; i < n; i++) {
			if(k==n) k = 0;
			curY = dots[k][1] > 0 ? 1 : -1;
			if(curY != prevY) {
				if(up) {
					x2 = dots[k][0];
					if(x1 > x2) {
						int tmp = x1;
						x1 = x2;
						x2 = tmp;
					}
					bonguri.add(new int[] {x1,x2});
					up = false;
				}else {
					x1 = dots[k][0];
					up = true;
				}
			}
			k++;
			prevY = curY;
		}
		bonguri.sort((o1,o2)->(Integer.compare(o1[0], o2[0]))); // 시작점을 기준으로 오름차순 정렬
		//Stack<int[]> stack = new Stack<>();
		int prevR = Integer.MIN_VALUE, maxR = Integer.MIN_VALUE;
		for (int i = 0; i < bonguri.size(); i++) {
			//System.out.println(Arrays.toString(bonguri.get(i)));
			if(bonguri.get(i)[1] < prevR) {
				b--;
			}
			if(bonguri.get(i)[1] > maxR) {
				a++;
				maxR = bonguri.get(i)[1];
			}
			b++;
			prevR = bonguri.get(i)[1];
		}
		System.out.println(a+" "+b);
	}
	
}