import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] nums;
	static int[] selected;
	static List<Integer> list = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N+1];
		selected = new int[N+1];
		for (int i = 0; i < N; i++) {
			nums[i+1] = Integer.parseInt(br.readLine());
		}
		List<Integer> nList;
		for (int i = 1; i < N+1; i++) {
			if(selected[i] == 1) continue;
			selected[i] = 1; 
			nList = new ArrayList<>();
			nList.add(i);
			if(!dfs(i, i, nList)) 
				selected[i] = 0;
		}
		Integer[] resultList = new Integer[list.size()];
		Arrays.sort(list.toArray(resultList));
		sb.append(list.size() + "\n");
		for (int i = 0; i < list.size(); i++) {
			sb.append(resultList[i]+ "\n");
		}
		System.out.println(sb.toString());
	}
	public static boolean dfs(int s, int curNum, List<Integer> nList) { // 현재 1번쨰 줄에서 찾으려는 숫자, 뽑은 개수
		if(s == nums[curNum]) { // 시작으로 돌아오는 사이클 발생
			for (int i = 0; i < nList.size(); i++) {
				list.add(nList.get(i));
			}
			return true;
		}
		boolean result = false;
		if(selected[nums[curNum]] == 0) {
			selected[nums[curNum]] = 1; 
			nList.add(nums[curNum]);
			result = dfs(s, nums[curNum], nList);
			if(!result)
				selected[nums[curNum]] = 0;
		}
		return result;
	}
}
