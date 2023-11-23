import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Country implements Comparable<Country> {
        int num;
        int g;
        int s;
        int b;

        public Country(int num, int g, int s, int b) {
            this.num = num;
            this.g = g;
            this.s = s;
            this.b = b;
        }

        @Override
        public int compareTo(Country o) {
            if(this.g == o.g) {
                if(this.s == o.s) {
                    if(this.b == o.b) {
                        return this.num - o.num;
                    }
                    return this.b-o.b;
                }
                return this.s-o.s;
            }
            return this.g-o.g;
        }
    }

    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Country[] countries = new Country[N + 1];
        countries[0] = new Country(0, 0, 0, 0);
        int num, g, s, b;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            countries[num] = new Country(num, g, s, b);
        }
        Arrays.sort(countries);
        int cnt = 0;
        int i = N;

        while(i >= 1){
            if(countries[i].num == K) break;
            if(isSame(countries[i], countries[i-1])) {
                while(i > 1 && isSame(countries[i], countries[i-1])) {
                    if(countries[i].num == K) break;
                    i--;
                }
                if(countries[i].num == K) break;
            }else {
                i--;
            }
            cnt = N - i;
        }
        System.out.println(cnt+1);
    }

    private static boolean isSame(Country country, Country country1) {
        if(country.g == country1.g && country.s == country1.s && country.b == country1.b)
            return true;
        return false;
    }


}