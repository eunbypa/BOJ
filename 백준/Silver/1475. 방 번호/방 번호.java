import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String N;
    static int[] cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
        cnt = new int[10];
        int length = N.length();
        int min = 0;
        int idx;
        for (int i = 0; i < length; i++) {
            idx = N.charAt(i) - '0';
            cnt[idx]++;
            if(idx == 6 || idx == 9){
                min = Math.max(min, (cnt[6] + cnt[9]) / 2 + (cnt[6] + cnt[9]) % 2);
            }else{
                min = Math.max(min, cnt[idx]);
            }
        }
        System.out.println(min);
    }
}