import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static class Bucket implements Comparable<Bucket> {
        int g;
        int x;

        public Bucket(int g, int x) {
            this.g = g;
            this.x = x;
        }

        @Override
        public int compareTo(Bucket o) {
            return Integer.compare(this.x, o.x);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Bucket[] buckets = new Bucket[N];
        int g, x;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            g = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            buckets[i] = new Bucket(g, x);
        }
        Arrays.sort(buckets);

        System.out.println(getMaxSumOfIce(N, K, buckets));
    }

    static int getMaxSumOfIce(int N, int K, Bucket[] buckets) {
        int maxLength = 2 * K;
        int s = 0, e = 0, curLenDiff = 0, curIceSum = 0, maxIceSum = 0;
        int sum, reqLen;
        // 슬라이딩 윈도우
        for (int i = 0; i < N; i++) {
            curLenDiff = buckets[i].x - buckets[s].x;
            if(curLenDiff <= maxLength) {
                curIceSum += buckets[i].g;
            }else {
                reqLen = curLenDiff - maxLength;
                sum = 0;
                while(s < i && reqLen > 0) {
                    reqLen -= buckets[s + 1].x - buckets[s].x;
                    sum += buckets[s].g;
                    s++;
                }
                curIceSum = curIceSum - sum + buckets[i].g;
            }
            maxIceSum = Math.max(maxIceSum, curIceSum);
            e = i;
        }

        return maxIceSum;
    }



}