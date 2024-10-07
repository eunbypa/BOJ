import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Flower {
        
        int idx;
        int sm,sd;
        int em,ed;

        public Flower(int idx, int sm, int sd, int em, int ed) {
            this.idx = idx;
            this.sm = sm;
            this.sd = sd;
            this.em = em;
            this.ed = ed;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Flower[] flowers = new Flower[N+1];
        // 해당 날짜에 피어있는 꽃의 지는 날짜가 제일 늦은 꽃의 index를 저장
        int[][] arr = new int[13][32];
        StringTokenizer st;
        int sm, sd, em, ed;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            sm = Integer.parseInt(st.nextToken());
            sd = Integer.parseInt(st.nextToken());
            em = Integer.parseInt(st.nextToken());
            ed = Integer.parseInt(st.nextToken());
            flowers[i] = new Flower(i, sm, sd, em, ed);
            set(arr, flowers, flowers[i]);
        }

        System.out.println(getAnswer(arr, flowers));
    }
    
    static int getAnswer(int[][] arr, Flower[] flowers) {
        int curM = 3, curD = 1;
        Flower next;
        int cnt = 0;
        while(curM <= 11) {
            if(arr[curM][curD] == 0)  // 해당 날짜에 핀 꽃이 없으므로, 조건 만족 불가능
                return 0;
            next = flowers[arr[curM][curD]];
            curM = next.em;
            curD = next.ed;
            cnt++;
        }

        return cnt;
    }

    // 해당 꽃의 꽃이 핀 날짜부터 지는 날짜 전날까지 이동하면서
    // 이 꽃이 가장 나중에 지는 꽃이면 해당 날짜에 index 기록
    static void set(int[][] arr, Flower[] flowers, Flower flower) {
        if(flower.sm == flower.em) {
            setDay(arr, flowers, flower, flower.sm, flower.sd, flower.ed);
            return;
        }
        
        setDay(arr, flowers, flower, flower.sm, flower.sd, 31);

        
        for (int i = flower.sm+1; i < flower.em; i++) {
            setDay(arr, flowers, flower, i, 1, 31);
        }
        
        setDay(arr, flowers, flower, flower.em, 1, flower.ed-1);
    }
    
    // 해당 꽃의 꽃이 핀 날짜부터 지는 날짜 전날까지 이동하면서
    // 이 꽃이 가장 나중에 지는 꽃이면 해당 날짜에 index 기록
    static void setDay(int[][] arr, Flower[] flowers, Flower flower, int m, int sd, int ed) {
        for (int i = sd; i <= ed; i++) {
            if(arr[m][i] == 0) {
                // 해당 날짜에 피어있는 꽃이 없는 상태
                arr[m][i] = flower.idx;
            }else {
                arr[m][i] = getLaterEndDateFlowerIdx(flower, flowers[arr[m][i]]);
            }
        }
    }

    // 두 꽃 중 더 나중에 지는 꽃의 index 반환
    static int getLaterEndDateFlowerIdx(Flower a, Flower b) {
        if(a.em == b. em) {
            return (a.ed > b.ed) ? a.idx : b.idx;
        }

        return (a.em > b.em) ? a.idx : b.idx;
        
    }



}