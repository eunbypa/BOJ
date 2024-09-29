import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int APB_SIZE = 26;

    static final char LOWER_A = 'a';

    static final char ROOT = ' ';

    static class Alphabet {
        char apb;
        Alphabet[] childs;

        public Alphabet(char apb) {
            this.apb = apb;
            childs = new Alphabet[26];
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] S = readStringAsArr(br, N);
        String[] testStringArr = readStringAsArr(br, M);

        System.out.println(getAnswer(S, testStringArr));
    }

    // 검사 결과 반환
    static int getAnswer(String[] S, String[] testArr) {
        Alphabet apbTrie = getAPBTrie(S, S.length);
        int cnt = 0;
        int l = testArr.length;
        for (int i = 0; i < l; i++) {
            if(findString(apbTrie, testArr[i]))
                cnt++;
        }
        return cnt;
    }

    static boolean findString(Alphabet root, String s) {
        Alphabet cur = root;
        int l = s.length();
        int curIdx;
        for (int i = 0; i < l; i++) {
            curIdx = s.charAt(i) - LOWER_A;
            cur = cur.childs[curIdx];
            // 트라이 구조 내 접두사 일치하는 문자열 없음
            if(cur == null)
                return false;
        }

        return true;
    }

    // 읽은 문자열 배열 반환
    static String[] readStringAsArr(BufferedReader br, int l) throws IOException {
        String[] arr = new String[l];
        for (int i = 0; i < l; i++) {
            arr[i] = br.readLine();
        }
        return arr;
    }

    // 트라이 구조 배열 반환
    static Alphabet getAPBTrie(String[] arr, int l) {
        Alphabet root = new Alphabet(ROOT);
        Alphabet cur = root;
        int sl, curIdx;
        for (int i = 0; i < l; i++) {
            sl = arr[i].length();
            cur = root;
            for (int j = 0; j < sl; j++) {
                curIdx = arr[i].charAt(j) - LOWER_A;
                if(cur.childs[curIdx] == null) {
                    cur.childs[curIdx] = new Alphabet((char) (LOWER_A + curIdx));
                }
                cur = cur.childs[curIdx];
            }
        }
        return root;
    }


}