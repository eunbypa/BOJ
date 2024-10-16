import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final char A = 'A';
    static final String FAIL_MSG = "I'm Sorry Hansoo";

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        int[] apbCntArr = getApbCntArr(name);

        System.out.println(getAnswer(name, apbCntArr));
    }

    static String getAnswer(String name, int[] apbCntArr) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> half = new Stack<>();
        char c, mid = ' ';
        int cur = 0, l = name.length();
        // 중간에 넣어야 할 글자가 이미 있으면 true
        boolean isMidFilled = false;

        for (int i = 0; i < 26; i++) {
            if(apbCntArr[i] == 0) continue;
            if(apbCntArr[i] % 2 == 1) {
               if(l % 2 == 0 || isMidFilled) {
                   return FAIL_MSG;
               }
                mid = (char) (i + A);
                isMidFilled = true;
            }
            for (int j = 0; j < apbCntArr[i]/2; j++) {
                c = (char) (i + A);
                sb.append(c);
                half.add(c);
            }

        }

        if(isMidFilled)
            sb.append(mid);
        while (!half.isEmpty()) {
            sb.append(half.pop());
}

        return sb.toString();
    }


    // 문자열의 알파벳 개수 배열 반환
    static int[] getApbCntArr(String s) {
        int[] arr = new int[26];
        int l = s.length();

        for (int i = 0; i < l; i++) {
            arr[s.charAt(i) - A]++;
        }

        return arr;
    }


}