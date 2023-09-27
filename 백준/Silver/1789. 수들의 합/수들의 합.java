import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Long S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Long.parseLong(br.readLine());
        int N = 0;
        long sum = 0L;
        while(sum < S){
            N++;
            sum += N;
        }
        if(sum > S) N--;
        System.out.println(N);
    }

}