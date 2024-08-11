import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int l = S.length();
        int zero = 0, one = 0;
        for (int i = 0; i < l; i++) {
            if(S.charAt(i) == '0') zero++;
            else one++;
        }
        int passZeroCnt = 0;
        int removeOneCnt = 0;
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < l; i++) {
            if(S.charAt(i) == '0') {
                if(passZeroCnt < zero/2) {
                    passZeroCnt++;
                    answer.append(0);
                }
            }else {
                if(removeOneCnt < one/2) {
                    removeOneCnt++;
                }else {
                    answer.append(1);
                }

            }
        }
        System.out.println(answer.toString());
    }

}