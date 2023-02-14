import java.util.*;
import java.io.*;
public class Solution {
	static int win, lose;
	static int[] numbers; // 1~18까지의 숫자
	static int[] nums; // 규영이
	static int[] nums2; // 인영이
	static int scoreSum;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T= Integer.parseInt(br.readLine());
		scoreSum = (18*19)/2;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			win = 0;
			lose = 0;
			nums = new int[9];
			nums2 = new int[9];
			numbers = new int[19];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				nums[i] = Integer.parseInt(st.nextToken()); // 규영이의 카드 패 저장
				numbers[nums[i]] = 1;
			}
			int idx = 0;
			for (int i = 1; i < 19; i++) {
				if(numbers[i] == 0) nums2[idx++] = i; // 인영이의 카드 패 저장
			}
            Arrays.sort(nums2);
			do{
                if(getResult() == 1) win++;
                else if(getResult() == -1) lose++;
            }while(nextPerm());
			sb.append("#"+test_case+" "+win+" "+lose+"\n");
		}
		System.out.println(sb.toString());
	}
    public static int getResult(){
        int sum = 0;
        for(int i = 0; i < 9; i++){
            if(nums[i] > nums2[i]) sum += nums[i] +nums2[i];
        }
        if(sum > scoreSum-sum) return 1; // 이긴 경우
        else if(sum <  scoreSum-sum) return -1; // 진 경우
        else return 0; // 비긴 경우
    }
    public static boolean nextPerm(){
		int i = 8;
        int j = 8;
        while(i > 0 && nums2[i-1] > nums2[i]) i--;
        if(i <= 0) return false;
        while(nums2[i-1] > nums2[j]) j--;
        int tmp = nums2[i-1];
        nums2[i-1] = nums2[j];
        nums2[j] = tmp;
        j = 8;
        while(i<j){
            tmp = nums2[i];
        	nums2[i] = nums2[j];
      	    nums2[j] = tmp;
         	i++;
            j--;
        }
        return true;
    }
}