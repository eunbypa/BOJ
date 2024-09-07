import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Sticker {
        int R;
        int C;

        public Sticker(int r, int c) {
            R = r;
            C = c;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(br.readLine());
        Sticker[] stickers = new Sticker[N];
        int r, c;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            stickers[i] = new Sticker(r, c);
        }

        System.out.println(getMaxArea(H, W, N, stickers));
    }

    // 조합
    static int getMaxArea(int H, int W, int N, Sticker[] stickers) {
        int maxArea = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i == j) continue;
                // 모눈종이에 둘다 붙일 수 있는지 확인
                if(isAttached(H, W, stickers[i].R, stickers[i].C, stickers[j].R, stickers[j].C)) {
                    maxArea = Math.max(maxArea, stickers[i].R * stickers[i].C + stickers[j].R * stickers[j].C);
                }
            }
        }

        return maxArea;
    }

    static boolean isAttached(int H, int W, int r1, int c1, int r2, int c2) {
        boolean possible = false;
        if(!possible && r1 + r2 <= H) {
            possible = c1 <= W && c2 <= W;
        }
        if(!possible && r1 + r2 <= W) {
            possible = c1 <= H && c2 <= H;
        }
        if(!possible && r1 + c2 <= H) {
            possible = r2 <= W && c1 <= W;
        }
        if(!possible && r1 + c2 <= W) {
            possible = r2 <= H && c1 <= H;
        }
        if(!possible && r2 + c1 <= H) {
            possible = r1 <= W && c2 <= W;
        }
        if(!possible && r2 + c1 <= W) {
            possible = r1 <= H && c2 <= H;
        }
        if(!possible && c1 + c2 <= H) {
            possible = r1 <= W && r2 <= W;
        }
        if(!possible && c1 + c2 <= W) {
            possible = r1 <= H && r2 <= H;
        }
        return possible;
    }


}