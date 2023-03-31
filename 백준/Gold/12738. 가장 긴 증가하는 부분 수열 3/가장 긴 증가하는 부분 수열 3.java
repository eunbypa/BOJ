import java.util.*;
import java.io.*;
public class Main {
	static int[] arr, arr2;
	static int n,max, last;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		arr2 = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		findLIS();
		max = last;
		System.out.println(max);
	}
	static void findLIS() {
		for (int i = 0; i < n; i++) {
			if(last == 0) {
				arr2[0] = arr[i];
				last++;
				continue;
			}
			int idx = BS(arr[i]);
			if(idx < 0) {
				idx = -(idx);
			}
			arr2[idx] = arr[i];
			if(idx == last) {
				last++;
			}
			//System.out.println(Arrays.toString(arr2));
		}
	}
	static int BS(int n) {
		int idx = -1;
		int s = 0;
		int e = last-1; // arr2 배열에서 가장 마지막 원소가 있는 곳 
		int mid = (s+e)/2;
		while(s <= e) {
			mid = (s+e)/2;
			if(n < arr2[mid]) { // mid 위치 값보다 찾으려는 값이 작으면
				e = mid-1;
			}else if(n > arr2[mid]) { // mid 위치 값보다 찾으려는 값이 크면
				s = mid+1;
			}else { // 찾으려는 값이 mid 위치 값임
				idx = mid;
				break;
			}
		}
		if(idx == -1) {
			if(e < mid) idx = -mid;
			else idx = -(mid+1);
		}
		return idx;
	}
}