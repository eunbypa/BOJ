import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<BigInteger> numberList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String word;
        numberList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            word = br.readLine();
            setNumber(word);
        }
        StringBuilder sb = new StringBuilder();
        int size = numberList.size();
        Collections.sort(numberList);
        for (int i = 0; i < size; i++) {
            sb.append(numberList.get(i)).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void setNumber(String s) {
        int l = s.length();
        int cur = 0, n;
        BigInteger num = BigInteger.ZERO;
        while(cur < l) {
            // 알파벳은 생략
            if(isWord(s.charAt(cur))) {
                cur++;
                continue;
            }
            // 처음 숫자 등장
            while(cur < l && !isWord(s.charAt(cur))) {
                num = num.multiply(BigInteger.TEN);
                n = (s.charAt(cur) - '0');
                num = num.add(BigInteger.valueOf(n));
                cur++;
            }
            numberList.add(num);
            num = BigInteger.ZERO;
        }
    }

    static boolean isWord(char c) {
        return c >= 'a' && c <= 'z';
    }

}