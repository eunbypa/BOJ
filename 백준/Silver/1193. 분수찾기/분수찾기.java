import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int n = 1;
        while(X > n){
            X -= n;
            n++;
        }
        StringBuilder sb = new StringBuilder();
        int r, c;
        if(n % 2 == 0){ // 짝 -> 끝에서 시작
            r = 1 + (X - 1);
            c = n - (X - 1);
        }else{ // 홀 -> 시작에서 시작
            r = n - (X - 1);
            c = 1 + (X - 1);
        }
        sb.append(r +"/"+c);
        System.out.println(sb.toString());
    }
}