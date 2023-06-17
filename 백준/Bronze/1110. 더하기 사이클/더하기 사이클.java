import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, cycle;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if(N == 0) cycle = 1;
        else {
            int next = N, ten, one, right, sum; // 다음 수, 십의 자리, 일의 자리, 주어진 수의 가장 오른쪽 자리, 자리 수 합
            do { // 사이클 탐색
                right = next % 10;
                if(next < 10) {
                    next = next*10;
                }
                cycle++;
                ten = next / 10;
                one = next % 10;
                sum = ten + one;
                next = right*10 + (sum%10);
            }while(next != N);
        }
        System.out.println(cycle);
    }


}