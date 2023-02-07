import java.util.*;
import java.io.*;
public class Main {
	static char[] lines;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
		lines = br.readLine().toCharArray();
		System.out.println(postfix());
	}
	//연산자가 들어왔을 때, 스택 top에 있는 것보다 낮거나 같은 우선순위의 연산자가 들어오면
	//높은 연산자가 등장할 때까지 pop 해준다.
	public static String postfix() { // 후위식 변환
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lines.length; i++) {
			switch(lines[i]) {
			case '*':
			case '/':
			case '+':
			case '-':
				while(!stack.isEmpty() && priority(lines[i]) >= priority(stack.peek())) {
					sb.append(stack.pop());
				}
				//top = priority(lines[i]);
				stack.push(lines[i]);
				break;
			case '(':
				//top = priority(lines[i]);
				stack.push(lines[i]);
				break;
			case ')':
                // 여는 괄호를 만날때까지 pop
				while(priority(stack.peek()) != 3) {
					sb.append(stack.pop());
				}
				stack.pop();// 여는 괄호 pop
				break;
			default:
				sb.append(lines[i]);
			}
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}
	
	public static int priority(char c) {
		if(c == '(' || c == ')') return 3;
		else if(c == '*' || c == '/') return 1;
		else return 2;
	}
}
