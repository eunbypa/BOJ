import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, cnt;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] board;
        board = br.readLine().toCharArray();
        int l = board.length;
        int s = 0, tmp = 0, cnt = 0;
        boolean success = true;
        while(s < l) {
            if(board[s] == '.') {
                s++;
                continue;
            }
            cnt = 0;
            tmp = s;
            while(tmp < l && board[tmp] == 'X') {
                cnt++;
                tmp++;
            }
            if(cnt % 2 == 1) {
                // X가 홀수개 남음 -> 덮을수없다
                success = false;
                break;
            }
            // 사전순으로 빠르도록 AAAA로 먼저 채우기
            for (int i = 0; i < (cnt/4); i++) {
                for (int j = 0; j < 4; j++) {
                    board[s + 4 * i + j] = 'A';
                }
            }
            // 만약 4로 채우고 남은 2자리가 있다면 BB로 채우기
            if(cnt % 4 != 0) {
                board[s + cnt - 2] = 'B';
                board[s + cnt - 1] = 'B';
            }
            s = tmp;
        }
        if(success) System.out.println(String.valueOf(board));
        else System.out.println(-1);
    }

}