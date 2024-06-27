import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        String s;
        for (int t = 0; t < T; t++) {
            s = br.readLine();
            if(checkVPS(s)) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // 스택을 활용하여 옳은 괄호 문자열인지 확인
    public static boolean checkVPS(String s) {
        Stack<Character> stack = new Stack<>();
        int l = s.length();
        for (int i = 0; i < l; i++) {
            if(stack.isEmpty()) {
                stack.add(s.charAt(i));
                continue;
            }
            if(stack.peek() == '(' && s.charAt(i) == ')')
                stack.pop();
            else
                stack.add(s.charAt(i));
        }

        return stack.isEmpty();
    }

}