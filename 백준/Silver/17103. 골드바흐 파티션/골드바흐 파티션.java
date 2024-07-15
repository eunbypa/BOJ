import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MAX_VALUE = 1000000;
    static Map<Integer, Integer> map;
    static List<Integer> primeNumList;
    static int[] checkedPrimeNums;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new HashMap<>();
        primeNumList = new ArrayList<>();
        checkedPrimeNums = new int[MAX_VALUE];
        getPrime();
        int T = Integer.parseInt(br.readLine());
        int N;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            sb.append(getGBPartitionCnt(N, t)).append("\n");
        }

        System.out.println(sb.toString());
    }

    // 소수 구하기
    public static void getPrime() {
        for (int i = 2; i <= MAX_VALUE; i++) {
            if(!isPrime(i)) continue;
            checkedPrimeNums[i] = 1;
            primeNumList.add(i);
        }
    }

    public static boolean isPrime(int n) {
        int i = 2;
        while(i*i <= n) {
            if(n % i == 0) return false;
            i++;
        }
        return true;
    }

    // N 까지의 소수에 대하여 N - 소수 값이 소수인지 확인 후 정답이 되는 소수의 합 쌍 개수 구하기
    public static int getGBPartitionCnt(int n, int testNum) {
        int size = primeNumList.size();
        int cnt = 0;
        int t;
        for (int i = 0; i < size; i++) {
            if(n < primeNumList.get(i)) break;
            if(checkedPrimeNums[n - primeNumList.get(i)] == 0) continue;
            if(map.containsKey(primeNumList.get(i))) {
                t = map.get(primeNumList.get(i));
                if(t == testNum) continue;
            }
            map.put(primeNumList.get(i), testNum);
            map.put(n - primeNumList.get(i), testNum);
            cnt++;
        }
        return cnt;
    }

}