import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String document, word;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        document = br.readLine();
        word = br.readLine();
        int l = document.length();
        int l2 = word.length();
        int cnt = 0, cnt2 = 0;
        for (int i = 0; i < l; i++) {
            cnt2 = 0;
            if(i+l2 > l) break;
            for (int j = 0; j < l2; j++) {
                if(document.charAt(i+j) == word.charAt(j)){
                    cnt2++;
                }else break;
            }
            if(cnt2 == l2){
                // 찾음
                cnt++;
                i += l2 - 1;
            }
        }
        System.out.println(cnt);
    }
}