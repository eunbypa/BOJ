import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int A, B;
    static int[] aPow;

    static final int maxNumber = (int) Math.pow(2, 20);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        aPow = new int[20];
        int tmp = 1;
        int cnt = 0;
        while(tmp < maxNumber) {
            aPow[cnt++] = tmp;
            tmp *= A;
        }
        int m = Integer.parseInt(br.readLine());
        int aNumber = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            aNumber += Integer.parseInt(st.nextToken()) * aPow[m-1-i];
        }
        int div;
        List<Integer> list = new ArrayList<>();
        while(aNumber != 0) {
            list.add(aNumber % B);
            aNumber /= B;
        }
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = size-1; i >= 0; i--) {
            sb.append(list.get(i));
            if(i == 0) break;
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

}