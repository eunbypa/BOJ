import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        int N = Integer.parseInt(br.readLine());
        String word;
        for (int i = 0; i < N; i++) {
            word = br.readLine();
            if(isGood(word)) cnt++;
        }
        System.out.println(cnt);
    }

    // 좋은 단어인지 확인하는 함수
    public static boolean isGood(String s) {
        int l = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < l; i++) {
            if(!stack.isEmpty() && stack.peek() == s.charAt(i)) {
                stack.pop();
            }else stack.add(s.charAt(i));
        }
        return stack.isEmpty();
    }
}