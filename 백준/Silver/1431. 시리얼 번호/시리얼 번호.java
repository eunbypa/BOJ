import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static class Guitar implements Comparable<Guitar> {
        String serial;

        public Guitar(String serial) {
            this.serial = serial;
        }

        @Override
        public int compareTo(Guitar o) {
            if(this.serial.length() == o.serial.length()) {
                int a = this.getSum(this.serial);
                int b = this.getSum(o.serial);
                if(a == b) {
                    return this.serial.compareTo(o.serial);
                }
                return a - b;
            }
            return this.serial.length() - o.serial.length();
        }
        public int getSum(String s) {
            int l = s.length();
            int sum = 0;
            for (int i = 0; i < l; i++) {
                if(s.charAt(i) - '0' >= 0 && s.charAt(i) - '9' <= 0) {
                    sum += s.charAt(i) - '0';
                }
            }
            return sum;
        }
    }
    static int N;
    static Guitar[] guitars;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        guitars = new Guitar[N];
        for (int i = 0; i < N; i++) {
            guitars[i] = new Guitar(br.readLine());
        }
        Arrays.sort(guitars);
        for (int i = 0; i < N; i++) {
            sb.append(guitars[i].serial).append("\n");
        }
        System.out.println(sb.toString());
    }


}