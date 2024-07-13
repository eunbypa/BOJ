import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T;
        T = Integer.parseInt(br.readLine());
        String gangsan;
        for (int t = 0; t < T; t++) {
            gangsan = br.readLine();
            sb.append(getPassword(gangsan));
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // 저장한 키로그를 바탕으로 비밀번호 찾아서 반환
    public static String getPassword(String keyLog) {
        int L = keyLog.length();
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        char key;
        for (int i = 0; i < L; i++) {
            key = keyLog.charAt(i);
            switch (key) {
                case '<':
                    if(left.isEmpty()) break;
                    right.add(left.pop());
                    break;
                case '>':
                    if(right.isEmpty()) break;
                    left.add(right.pop());
                    break;
                case '-':
                    if(left.isEmpty()) break;
                    left.pop();
                    break;
                default:
                    left.add(key);

            }
        }

        StringBuilder sb = new StringBuilder();
        while(!right.isEmpty()) {
            sb.append(right.pop());
        }
        sb = sb.reverse();
        while(!left.isEmpty()) {
            sb.append(left.pop());
        }
        return sb.reverse().toString();
    }


}