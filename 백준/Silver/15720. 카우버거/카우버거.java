import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] burgerPriceArr = new int[B];
        init(burgerPriceArr, st);
        st = new StringTokenizer(br.readLine());
        int[] cidePriceArr = new int[C];
        init(cidePriceArr, st);
        st = new StringTokenizer(br.readLine());
        int[] drinkPriceArr = new int[D];
        init(drinkPriceArr, st);

        Arrays.sort(burgerPriceArr);
        Arrays.sort(cidePriceArr);
        Arrays.sort(drinkPriceArr);

        StringBuilder sb = new StringBuilder();
        int totPriceSum = getPriceSum(burgerPriceArr) + getPriceSum(cidePriceArr) + getPriceSum(drinkPriceArr);
        int maxSetCnt = Math.min(B, Math.min(C, D));
        int discountMinPriceSum = getMinPriceSum(burgerPriceArr, maxSetCnt) + getMinPriceSum(cidePriceArr, maxSetCnt) + getMinPriceSum(drinkPriceArr, maxSetCnt);
        sb.append(totPriceSum).append("\n").append(discountMinPriceSum);

        System.out.println(sb.toString());
    }


    // 배열 입력값 초기화
    static void init(int[] arr, StringTokenizer st) {
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    // 배열 원소 합 구하기
    static int getPriceSum(int[] priceArr) {
        int sum = 0;
        int l = priceArr.length;
        for (int i = 0; i < l; i++) {
            sum += priceArr[i];
        }
        return sum;
    }

    // 세트 할인 적용 시 최소 금액 합 구하기
    // 오름차순 정렬된 배열 끝에서 cnt만큼 할인된 가격으로 더하기
    static int getMinPriceSum(int[] priceArr, int cnt) {
        int sum = 0, discountRate;
        int l = priceArr.length;
        for (int i = l-1; i >= 0; i--) {
            discountRate = (priceArr.length - i > cnt) ? 0 : 10;
            sum += getDiscountPrice(priceArr[i], discountRate);
        }
        return sum;
    }

    static int getDiscountPrice(int price, int discountRate) {
        return price - ((price * discountRate) / 100);
    }



}