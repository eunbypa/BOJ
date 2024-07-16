import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static final String SUCCESS = "Nice";
    static final String FAIL = "Sad";

    static int N;
    static int[] line;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        line = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cur = 1;
        Stack<Integer> line2 = new Stack<>();
        for (int i = 0; i < N; i++) {
            line[i] = Integer.parseInt(st.nextToken());
            // 현재 이동해야 할 순서인 경우
            if(cur == line[i]) {
                cur++;
                continue;
            }
            // 또다른 줄에서 가장 앞에 대기중인 사람의 순서가 현재 입장해야 되는 순서인지 확인
            while(!line2.isEmpty() && cur == line2.peek()) {
                line2.pop();
                cur++;
            }
            line2.add(line[i]);
        }

        while(!line2.isEmpty() && cur == line2.peek()) {
            line2.pop();
            cur++;
        }

        String answer = (line2.isEmpty()) ? SUCCESS : FAIL;

        System.out.println(answer);
    }



}