import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] bookNumArr = new int[N+1];
        int[] bookIdxArr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            bookNumArr[i] = Integer.parseInt(br.readLine());
            bookIdxArr[bookNumArr[i]] = i;
        }

        System.out.println(getAnswer(N, bookNumArr, bookIdxArr));
    }

    // 그리디?
    static int getAnswer(int N, int[] bookNumArr, int[] bookIdxArr) {
        int cur = N;
        int top = 0;
        int cnt = 0;
        while(cur != 1) {
            // 현재 번호 책 아래 부분에 다음 번호 책이 존재함
            // 즉, 위로 올려야 함!
            if(bookIdxArr[cur] < bookIdxArr[cur-1]) {
                cnt++;
                bookIdxArr[cur-1] = top--;
            }
            cur--;
        }

        return cnt;
    }

}