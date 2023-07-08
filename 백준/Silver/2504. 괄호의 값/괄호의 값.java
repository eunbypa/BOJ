import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        System.out.println(check());
    }

    // 올바른 괄호열인지 확인하며 값을 계산하는 함수
    public static int check() {
        int ans = 0, result = 0;
        int l = input.length;
        Stack<Character> st = new Stack<>(); // 올바른 괄호열인지 판단하는 스택
        Stack<Integer> st2 = new Stack<>(); // 괄호값 계산용 스택
        for (int i = 0; i < l; i++) {
            if(st.isEmpty()){ // 스택이 비어있는 경우
                if(input[i] == ')' || input[i] == ']') return 0; // 닫는 괄호열이 먼저 등장하면 안되므로 올바른 괄호열이 아님
                st.add(input[i]);
            }else{
                if((st.peek() == '(' && input[i] == ']') || (st.peek() == '[' && input[i] == ')')){
                    // 올바른 괄호열이 아님
                    return 0;
                }
                // 여는 괄호가 오는 경우
                if(input[i] == '(' || input[i] == '['){
                    st.add(input[i]);
                    st2.add(-1); // -1로 각 괄호 안의 계산값을 구분해 줌
                }
                // 닫는 괄호가 오면 현재까지의 괄호 값을 계산
                else if(input[i] == ')' || input[i] == ']'){
                    if(st.isEmpty()) return 0; // 올바른 괄호열이 아님
                    else st.pop();
                    result = 0;
                    while(!st2.isEmpty() && st2.peek() != -1){
                        result += st2.pop();
                    }
                    if(!st2.isEmpty() && st2.peek() == -1) st2.pop(); // 현재 괄호값 계산이 완료되었으므로 -1은 pop해줌
                    if(input[i] == ')'){
                        if(result == 0) result = 2; // ()
                        else result *= 2; // (x)
                    }else if(input[i] == ']'){
                        if(result == 0) result = 3; // []
                        else result *= 3; // [x]
                    }
                    // 현재 괄호열이 모두 닫힌 상태라는 의미이므로 값을 스택에 다시 삽입하지 않고 더해줌
                    if(st.isEmpty()) ans += result;
                    else st2.add(result);
                }
            }

        }
        // 모든 연산이 끝난 후 스택이 비어있어야 올바른 괄호열
        if(!st.isEmpty()) return 0;
        return ans;
    }
}