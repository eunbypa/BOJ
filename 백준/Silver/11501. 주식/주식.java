import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_VALUE = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N, maxStockPrice;
        int[] stockPriceArr, latestDayArr; // 주식 가격 배열, 해당 주식 가격이 등장한 가장 늦은 날짜 기록 배열
        for (int t = 0; t < T; t++) {
            maxStockPrice = 0;
            N = Integer.parseInt(br.readLine());
            stockPriceArr = new int[N];
            latestDayArr = new int[MAX_VALUE + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                stockPriceArr[i] = Integer.parseInt(st.nextToken());
                maxStockPrice = Math.max(maxStockPrice, stockPriceArr[i]);
                latestDayArr[stockPriceArr[i]] = Math.max(latestDayArr[stockPriceArr[i]], i);
            }
            sb.append(getMaxStockProfit(N, stockPriceArr, maxStockPrice, latestDayArr)).append("\n");
        }

        System.out.println(sb.toString());
    }

    // 최대 이익 반환
    static Long getMaxStockProfit(int N, int[] stockPriceArr, int maxStockPrice, int[] latestDayArr) {
        long profitSum = 0L;
        int curStockPrice, profit;

        for (int i = 0; i < N; i++) {
            curStockPrice = stockPriceArr[i];
            maxStockPrice = getMaxStockPriceAfterToday(i, maxStockPrice, latestDayArr);
            if(maxStockPrice < curStockPrice) profit = 0;
            else profit = maxStockPrice - curStockPrice;
            profitSum += profit;
        }

        return profitSum;
    }

    // 오늘 날짜를 기준으로, 그 이후 날들중 가장 큰 가격을 구해서 반환
    static int getMaxStockPriceAfterToday(int today, int curMaxStockPrice, int[] latestDayArr) {
        int nextMaxStockPrice = 0;
        for (int i = curMaxStockPrice; i >= 1; i--) {
            if(latestDayArr[i] >= today) {
                nextMaxStockPrice = i;
                break;
            }
        }
        return nextMaxStockPrice;
    }


}