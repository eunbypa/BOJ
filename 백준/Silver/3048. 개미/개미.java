import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N1, N2;
    static char[] arr;
    static char[] tmp;
    static int[] apbD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N1 = Integer.parseInt(st.nextToken());
        N2 = Integer.parseInt(st.nextToken());
        apbD = new int[26];
        arr = new char[N1 + N2];
        tmp = new char[N1 + N2];
        String A, B;
        A = br.readLine();
        B = br.readLine();
        for (int i = 0; i < N1; i++) {
            apbD[A.charAt(N1 - 1 - i)-'A'] = 1;
            arr[i] = A.charAt(N1 - 1 - i);
        }
        for (int i = 0; i < N2; i++) {
            apbD[B.charAt(i)-'A'] = -1;
            arr[N1+i] = B.charAt(i);
        }
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            move();
        }
        StringBuilder sb = new StringBuilder();
        int l = N1+N2;
        for (int i = 0; i < l; i++) {
            sb.append(arr[i]);
        }
        System.out.println(sb.toString());
    }

    public static void move() {
        int l = N1+N2;
        int n;
        for (int i = 0; i < l; i++) {
            n = i + apbD[arr[i] - 'A'];
            if(n < 0 || n >= l) {
                tmp[i] = arr[i];
                continue;
            }
            if(apbD[arr[i]-'A'] == apbD[arr[n]-'A']) {
                tmp[i] = arr[i];
            }else {
                tmp[i] = arr[n];
                tmp[n] = arr[i];
            }
        }
        for (int i = 0; i < l; i++) {
            arr[i] = tmp[i];
        }
    }




}