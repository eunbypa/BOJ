import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        int M = Integer.parseInt(br.readLine());
        Stack<Character> leftSt = new Stack<Character>(); // 커서 기준 왼쪽 문자들
        Stack<Character> rightSt = new Stack<Character>(); // 커서 기준 오른쪽 문자들
        for(char c : str) { 
            leftSt.push(c);
        }
        String command;
        char c;
        for(int i = 0; i < M; i++) {
            command = br.readLine();
            c = command.charAt(0);
            switch(c) {
                case 'L':
                    if(!leftSt.isEmpty())
                        rightSt.push(leftSt.pop());
                    break;
                case 'D':
                    if(!rightSt.isEmpty())
                        leftSt.push(rightSt.pop());
                    break;
                case 'B':
                    if(!leftSt.isEmpty()) {
                        leftSt.pop();
                    }
                    break;
                case 'P':
                    char t = command.charAt(2);
                    leftSt.push(t);
                    break;
                default:
                    break;
            }
        }
        // 왼쪽 스택에서 오른쪽 스택으로 옮겨서 pop해야 원래 순서의 문자열이 나온다
        while(!leftSt.isEmpty()) rightSt.push(leftSt.pop());
        StringBuilder sb = new StringBuilder();
        while(!rightSt.isEmpty()) sb.append(rightSt.pop());
        System.out.println(sb.toString());
    }
}