import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int k = Integer.parseInt(br.readLine());
        merge_sort(0, N-1, arr, new int[N], 1, k);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N-1; i++) {
            sb.append(arr[i]).append(" ");
        }
        sb.append(arr[N - 1]);
        System.out.println(sb.toString());
    }

    // merge sort
    static void merge_sort(int l, int r, int[] arr, int[] tmp, int cnt, int k) {
        // 더이상 분할 불가능
        if(l == r)
            return;
        int m = (l + r) / 2;
        // divide and conquer
        merge_sort(l, m, arr, tmp, cnt*2, k);
        merge_sort(m+1, r, arr, tmp, cnt*2, k);
        // 구하고자하는 단계까지만 정렬 수행
        if(k <= cnt)
            merge(l, r, arr, tmp, k);
    }

    // 정렬 & 합병
    static void merge(int l, int r, int[] arr, int[] tmp, int k) {
        int m = (l + r) / 2;
        int s = l, e = m+1, i = l;
        // 왼쪽 검사
        while(s <= m && arr[s] < arr[e])
            tmp[i++] = arr[s++];
        // 오른쪽 검사
        if(s <= m) {
            while (e <= r && arr[e] < arr[s])
                tmp[i++] = arr[e++];
        }
        // 나머지 검사
        while(s <= m || e <= r) {
            if(s <= m && e <= r) {
                if(arr[s] < arr[e]) {
                    tmp[i] = arr[s++];
                }else {
                    tmp[i] = arr[e++];
                }
            }else if(s <= m) {
                tmp[i] = arr[s++];
            }else if(e <= r) {
                tmp[i] = arr[e++];
            }
            i++;
        }
        // 원래 배열에 정렬된 부분 갱신
        for (int j = l; j <= r; j++) {
            arr[j] = tmp[j];
        }
    }

}