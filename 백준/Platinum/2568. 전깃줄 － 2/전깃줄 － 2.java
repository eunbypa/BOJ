import java.util.*;
import java.io.*;


public class Main {
	static class Line implements Comparable<Line>{
		int s;
		int e;
		public Line(int s, int e) {
			super();
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(Line o) {
			// TODO Auto-generated method stub
			return this.s-o.s;
		}
		
	}
	static int N, last;
	static Line[] lis; // lis 길이 측정을 위한 배열
	static int[] record; // 각 index 원소의 lis에서의 순서
	static List<Line> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		lis = new Line[N];
		record = new int[N];
		StringTokenizer st;
		int s, e;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			list.add(new Line(s,e));
		}
		Collections.sort(list); // 시작점을 기준으로 정렬
		last = 1;
		lis[0] = list.get(0);
		record[0] = 0;
		for (int i = 1; i < N; i++) {
			int idx = binarySearch(list.get(i).e);
			if(idx < 0) idx = -(idx);
			lis[idx] = list.get(i);
			record[i] = idx;
			if(idx == last) last++; // 끝에 추가되었다는 뜻
			//System.out.println(Arrays.toString(record));
		}
		int removeCnt = N-last;
		int order = last-1;
		List<Integer> removeList = new ArrayList<>();
		for (int i = N-1; i >= 0; i--) {
			if(order == record[i]) {
				order--;
			}else {
				removeList.add(list.get(i).s);
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(removeCnt+"\n");
		for (int i = removeList.size()-1; i >= 0; i--) {
			sb.append(removeList.get(i)+"\n");
		}
		System.out.println(sb.toString());
	}
	static int binarySearch(int n) {
		int idx = -1;
		int s = 0;
		int e = last-1;
		int mid = (s+e)/2;
		while(s <= e) {
			mid = (s+e)/2;
			if(lis[mid].e < n) {
				s = mid+1;
			}else if(n < lis[mid].e) {
				e = mid-1;
			}else {
				idx = mid;
				break;
			}
		}
		// n을 못찾은 상태
		if(idx == -1) {
			if(e < mid) idx = -mid;
			else idx = -(mid+1);
		}
		
		return idx;
	}
}
