import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int[] mbtiCntArr;
    static int min;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int N, cnt = 0;
        String mbti;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            min = 12;
            mbtiCntArr = new int[16];
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                mbti = st.nextToken();
                mbtiCntArr[convertStringToInt(mbti)]++;
            }
            dfs(0, 0, new int[16], new int[3]);
            sb.append(min).append("\n");
        }

        System.out.println(sb.toString());
    }

    // mbti 4자리를 각각 2진수 형태로 보고 정수로 변환
    public static int convertStringToInt(String s) {
        int num = 0;
        char c;
        for (int i = 0; i < 4; i++) {
            c = s.charAt(i);
            switch (c) {
                case 'E':
                case 'S':
                case 'T':
                case 'G':
                    break;
                case 'I':
                case 'N':
                case 'F':
                case 'P':
                    num += (1 << (3-i));
                    break;
            }
        }
        return num;
    }

    // 아무 mbti 3개 조합 구하고, 실제로 답이 되는지 확인
    public static void dfs(int cur, int cnt, int[] selected, int[] list) {
        if(cnt == 3) {
            if(isPossible(selected)) {
                min = Math.min(min, getPsyDist(list[0], list[1]) +
                        getPsyDist(list[1], list[2]) + getPsyDist(list[2], list[0]));
            }
            return;
        }

        for (int i = cur; i < 16; i++) {
            selected[i]++;
            list[cnt] = i;
            dfs(i, cnt+1, selected, list);
            selected[i]--;
        }
    }

    // 고른 3개의 mbti가 모두 기존에 구한 mbti 분류 데이터에 속한 경우 참
    public static boolean isPossible(int[] selected) {
        for (int i = 0; i < 16; i++) {
            if(mbtiCntArr[i] < selected[i]) return false;
        }
        return true;
    }

    // 세 mbti 심리적인 거리 구하기
    public static int getPsyDist(int a, int b) {
        int dist = 0;
        for (int i = 0; i < 4; i++) {
            if(a % 2 != b % 2) dist++;
            a /= 2;
            b /= 2;
        }
        return dist;
    }

}