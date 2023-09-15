import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        int l;
        Stack<Character> st;
        String res;
        while(!(line = br.readLine()).equals(".")){
            // 검사
            st = new Stack<>();
            l = line.length();
            res = "yes";
            for (int i = 0; i < l; i++) {
                if(line.charAt(i) == '(' || line.charAt(i) == '[') {
                    st.push(line.charAt(i));
                }else if(line.charAt(i) == ')'){
                    if(st.isEmpty() || (!st.isEmpty() && st.peek() == '[')){
                        res = "no";
                        break;
                    }
                    if(!st.isEmpty() && st.peek() == '('){
                        st.pop();
                    }
                }else if(line.charAt(i) == ']'){
                    if(st.isEmpty() || (!st.isEmpty() && st.peek() == '(')){
                        res = "no";
                        break;
                    }
                    if(!st.isEmpty() && st.peek() == '['){
                        st.pop();
                    }
                }
            }
            if(!st.isEmpty()) res = "no";
            sb.append(res + "\n");
        }
        System.out.println(sb.toString());
    }


}