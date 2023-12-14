import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static char[] S,T;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().toCharArray();
        T = br.readLine().toCharArray();
        int sl = S.length, tl = T.length;
        // T를 S로 만들 수 있는지 확인
        while(sl != tl) {
            if(T[tl-1] == 'A') {
                T[tl-1] = 0;
                tl--;
            }else if(T[tl-1] == 'B') {
                // 문자열 뒤집기
                T[tl-1] = 0;
                tl--;
                reverse(tl);
            } else break;
        }
        // 같은지 확인
        System.out.println(isEqual(sl, tl));
    }

    // 문자열 뒤집기
    public static void reverse(int l) {
        char[] tmp = new char[l];
        for (int i = 0; i < l; i++) {
            tmp[i] = T[i];
        }
        for (int i = 0; i < l; i++) {
            T[i] = tmp[l - 1 - i];
        }
    }

    // 동일한지 확인
    public static int isEqual(int sl, int tl) {
        if(sl != tl) return 0;
        for (int i = 0; i < tl; i++) {
            if(S[i] != T[i]) return 0;
        }
        return 1;
    }

}