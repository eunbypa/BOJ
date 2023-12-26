import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int burger = 2001, drink = 2001;
        int n;
        for (int i = 0; i < 3; i++) {
            n = Integer.parseInt(br.readLine());
            if(burger > n) burger = n;
        }
        for (int i = 0; i < 2; i++) {
            n = Integer.parseInt(br.readLine());
            if(drink > n) drink = n;
        }
        min = burger + drink - 50;
        System.out.println(min);
    }


}