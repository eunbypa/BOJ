import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] cnt; // 각 숫자의 생성자 개수를 저장
    public static void main(String[] args) throws IOException {
        cnt = new int[10001];
        for (int i = 1; i < 10001; i++) {
            function(i);
        }
        for (int i = 1; i < 10001; i++) {
            if(cnt[i] == 0){ // 셀프 넘버
                sb.append(i+"\n");
            }
        }
        System.out.println(sb.toString());
    }

    // d(n)
    static void function(int n){
        int tmp = n, rem, sum = n;
        while(tmp > 0){
            rem = tmp % 10; // 자릿수 구하기
            sum += rem;
            tmp /= 10;
        }
        if(sum > 10000) return; // 10000까지만 확인함
        cnt[sum]++; // sum의 생성자 1증가
    }


}