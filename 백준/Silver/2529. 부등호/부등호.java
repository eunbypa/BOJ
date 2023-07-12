import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int k;
    static long min, max;
    static String minString, maxString;
    static char[] signs;
    static int[] list; // 순열로 뽑은 경우의 수 저장
    static boolean[] selected;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        signs = new char[k];
        list = new int[k + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            signs[i] = st.nextToken().charAt(0);
        }
        selected = new boolean[10];
        dfs(0);
        sb.append(maxString+"\n");
        sb.append(minString);
        System.out.println(sb.toString());
    }

    //순열 dfs
    public static void dfs(int cur) {
        if(cur == k+1){
            if(isSuccess()){
                StringBuilder result = setNumber();
                long number = 0L;
                if(min == 0) {
                    min = Long.parseLong(result.toString());
                    minString = result.toString();
                }
                else {
                    number = Long.parseLong(result.toString());
                    if(min > number){
                        min = number;
                        minString = result.toString();
                    }
                }
                if(max == 0) {
                    max = Long.parseLong(result.toString());
                    maxString = result.toString();
                }
                else {
                    number = Long.parseLong(result.toString());
                    if(max < number){
                        max = number;
                        maxString = result.toString();
                    }
                }
            }
            return;
        }
        for (int i = 0; i < 10; i++) {
            if(selected[i]) continue;
            selected[i] = true;
            list[cur] = i;
            dfs(cur + 1);
            selected[i] = false;
            list[cur] = 0;
        }
    }

    // 부등호 관계를 만족하는지 검사하는 함수
    private static boolean isSuccess() {
        for (int i = 0; i < k; i++) {
            if(signs[i] == '<'){
                if(list[i] >= list[i+1]) return false;
            }
            if(signs[i] == '>'){
                if(list[i] <= list[i+1]) return false;
            }
        }
        return true;
    }

    // 각 숫자를 합쳐서 하나의 수로 만드는 함수
    public static StringBuilder setNumber() {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i <= k; i++) {
            tmp.append(list[i]);
        }
        return tmp;
    }
}