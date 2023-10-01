import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    static char[] number;
    static int[] cnt; // 0~9 등장횟수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        number = br.readLine().toCharArray();
        cnt = new int[10];
        Arrays.sort(number);
        int l = number.length;
        String result = null;
        // 마지막 자리는 반드시 0이어야 함
        if(number[0] == '0') {
            // 전체 숫자를 다 더해서 3의 배수가 되는지 확인
            int sum = 0;
            for (int i = 0; i < l; i++) {
                cnt[number[i] - '0']++;
                sum += number[i] - '0';
            }
            if(sum % 3 == 0){
                // 3의 배수, 즉 30의 배수인 상태
                result = getResult();
            }
        }
        if(result == null) result = "-1";
        System.out.println(result);
    }

    public static String getResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < cnt[i]; j++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

}