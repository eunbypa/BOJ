import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static String[] dnaArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dnaArr = new String[N];

        for (int i = 0; i < N; i++) {
            dnaArr[i] = br.readLine();
        }

        int[][] acgtCnt = new int [M][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                switch (dnaArr[i].charAt(j)) {
                    case 'A':
                        acgtCnt[j][0]++;
                        break;
                    case 'C':
                        acgtCnt[j][1]++;
                        break;
                    case 'G':
                        acgtCnt[j][2]++;
                        break;
                    case 'T':
                        acgtCnt[j][3]++;
                        break;
                }
            }
        }

        int mIdx = 0;
        int[] tmp = new int[4];
        int cur = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 4; j++) {
                tmp[j] = acgtCnt[i][j];
            }
            Arrays.sort(tmp);
            for (int j = 0; j < 4; j++) {
                if(tmp[3] == acgtCnt[i][j]) {
                    cur = j;
                    break;
                }
            }
            switch (cur) {
                case 0:
                    sb.append('A');
                    break;
                case 1:
                    sb.append('C');
                    break;
                case 2:
                    sb.append('G');
                    break;
                case 3:
                    sb.append('T');
                    break;
            }
        }
        String ans = sb.toString();
        sb.append("\n");
        sb.append(getHammingDistanceSum(ans));
        System.out.println(sb.toString());
    }

    public static int getHammingDistanceSum(String s) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(s.charAt(j) == dnaArr[i].charAt(j)) continue;
                sum++;
            }
        }
        return sum;
    }


}