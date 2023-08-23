import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 스택 구현
    static class Stack {
        int[] arr;
        int size;

        public Stack() {
            this.arr = new int[10001];
            this.size = 0;
        }

        public void push(int n){
            arr[++this.size] = n;
        }

        public int pop(){
            if(this.size == 0) return -1;
            return arr[this.size--];
        }

        public int isEmpty(){
            return (size == 0) ? 1 : 0;
        }

        public int top() {
            if(this.size == 0) return -1;
            return arr[this.size];
        }
    }

    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack stack = new Stack();
        N = Integer.parseInt(br.readLine());
        String cmd; // 명령어
        StringTokenizer st;
        int n;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken();
            switch (cmd){
                case "push":
                    n = Integer.parseInt(st.nextToken());
                    stack.push(n);
                    break;
                case "top":
                    sb.append(stack.top()+"\n");
                    break;
                case "size":
                    sb.append(stack.size + "\n");
                    break;
                case "empty":
                    sb.append(stack.isEmpty() + "\n");
                    break;
                case "pop":
                    sb.append(stack.pop() + "\n");
                    break;
            }
        }
        System.out.println(sb.toString());
    }

}