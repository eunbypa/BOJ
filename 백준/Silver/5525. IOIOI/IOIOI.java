import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, cnt;
    static char[] S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        S = br.readLine().toCharArray();
        int start = 0;
        for (int i = 0; i < M; i++) {
            if(S[i] == 'I'){
                start = i;
                break;
            }
        }
        check(start);
        System.out.println(cnt);
    }

    // KMP..?
    public static void check(int start) { // start : I가 처음으로 나오는 곳부터 시작
        int cur = 0, next = -1, s = start, e, fin = M-2*N-1;
        while(s <= fin) { // 2*N + 1 문자열을 검사할 수 있는 최대 범위 -> fin
            cur = 0;
            next = -1;
            e = s + 2 * N;
//            System.out.println("현재 s : " + s);
            while (cur <= N + 1) { // 2*N+1 길이인 IOI... 문자열은 대칭관계이므로 절반까지 대칭관계인지 검사
//                if (cur != 0) {
//                    if (S[s + cur] == 'I') next = s + cur; // 다음에 I가 등장하는 지점 찾기
//                }
                if (cur % 2 == 0) { // I 자리
                    if (S[s + cur] == 'O' || S[s + cur] != S[e - cur]) break;
                } else { // O자리
                    if (S[s + cur] == 'I' || S[s + cur] != S[e - cur]) break;
                }
                cur++;
            }
            if (cur > N + 1) {
                cnt++; // PN 문자열을 찾음
                s += 2;
            } else {
                s += cur; // 검사한 곳까지 이동
                if(cur == 0) s++; // 시작부분에서 바로 검사 종료된 경우 1칸 뒤로 이동
                while (s <= fin && S[s] == 'O') {
                    s++;
                }
            }
        }
    }
}