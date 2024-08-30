import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String X = br.readLine();
        nums = new int[X.length()];
        for (int i = 0; i < X.length(); i++) {
            nums[i] = X.charAt(i)-'0';
        }
        StringBuilder sb = new StringBuilder();
        if(nextPerm()) {
            for (int i = 0; i < nums.length; i++) {
                sb.append(nums[i]);
            }
        } else sb.append(0);

        System.out.println(sb.toString());
    }


    // next_perm
    static boolean nextPerm() {
        int i = nums.length-1;
        int j = nums.length-1;

        while(i > 0 && nums[i-1] >= nums[i]) i--;
        // 내림차순 형태로 정렬 완료된 상태이므로 종료
        if(i == 0)
            return false;
        while(nums[i-1] >= nums[j]) j--;
        swap(i-1, j);
        j = nums.length-1;
        while(i < j) {
            swap(i, j);
            i++;
            j--;
        }

        return true;

    }

    static void swap(int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }



}