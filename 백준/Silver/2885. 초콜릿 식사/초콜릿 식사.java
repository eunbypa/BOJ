import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        List<Integer> oneList = new ArrayList<>();
        int cur = K, one = 1, idx = 0;
        while(cur > 0) {
            if((cur & one) == 1) {
                oneList.add(idx);
            }
            idx++;
            cur >>= 1;
        }
        int chocoSize, cnt = 0;
        int size = oneList.size();
        if(size == 1)
            chocoSize = (int) Math.pow(2, oneList.get(0));
        else{
            cnt = 1;
            chocoSize = (int) Math.pow(2, (oneList.get(size-1) + 1));
        }

        for (int i = size-1; i >= 1; i--) {
            cnt += (oneList.get(i) - oneList.get(i - 1));
        }

        StringBuilder sb = new StringBuilder();
        sb.append(chocoSize).append(" ").append(cnt);
        System.out.println(sb.toString());
    }


}