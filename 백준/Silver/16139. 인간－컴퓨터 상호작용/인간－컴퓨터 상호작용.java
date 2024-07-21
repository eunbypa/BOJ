import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int[][] apbCntArr = getapbCntArr(S);
        int q = Integer.parseInt(br.readLine());
        char a;
        int l, r, ans;
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            a = st.nextToken().charAt(0);
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            ans = (l == 0) ? apbCntArr[r][a - 'a'] : apbCntArr[r][a - 'a'] - apbCntArr[l-1][a - 'a'];
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    // 각 구간별로 소문자가 몇개 있는지 카운팅
    public static int[][] getapbCntArr(String S) {
        int length = S.length();
        int[][] apbCntArr = new int[length][26];
        char c;
        for (int i = 0; i < 26; i++) {
            c = (char) (i + 'a');
            for (int j = 0; j < length; j++) {
                if(S.charAt(j) != c)
                    apbCntArr[j][c - 'a'] = (j == 0) ? 0 : apbCntArr[j - 1][c - 'a'];
                else
                    apbCntArr[j][c - 'a'] = (j == 0) ? 1 : apbCntArr[j - 1][c - 'a'] + 1;
            }
        }

        return apbCntArr;
    }


}