import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int length = 8;
    static int N;
    static int[] kingLoc, dollLoc;
    static int[] dr = {0, 0, 1, -1, -1, -1, 1, 1};
    static int[] dc = {1, -1, 0, 0, 1, -1, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String king, doll;
        king = st.nextToken();
        doll = st.nextToken();
        N = Integer.parseInt(st.nextToken());

        kingLoc = convertLoc(king);
        dollLoc = convertLoc(doll);
        String moving;
        for (int i = 1; i <= N; i++) {
            moving = br.readLine();
            move(getDistIdx(moving));
        }
        System.out.println(reConvertLoc(kingLoc));
        System.out.println(reConvertLoc(dollLoc));
    }

    public static int[] convertLoc(String loc) {
        int r = Integer.parseInt(loc.substring(1))- length;
        int c = loc.charAt(0) - 'A';

        return new int[]{Math.abs(r), c};
    }

    public static String reConvertLoc(int[] loc) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) ('A' + loc[1]));
        sb.append((Math.abs(loc[0] - length + 1) + 1));
        return sb.toString();
    }

    public static int getDistIdx(String moving) {
        int idx = -1;
        switch (moving) {
            case "R":
                idx = 0;
                break;
            case "L":
                idx = 1;
                break;
            case "B":
                idx = 2;
                break;
            case "T":
                idx = 3;
                break;
            case "RT":
                idx = 4;
                break;
            case "LT":
                idx = 5;
                break;
            case "RB":
                idx = 6;
                break;
            case "LB":
                idx = 7;
                break;
            default:
        }

        return idx;
    }

    public static void move(int idx) {
        int nr, nc;
        nr = kingLoc[0] + dr[idx];
        nc = kingLoc[1] + dc[idx];
        // 위치 밖이면 이동 x
        if(!canMove(nr, nc)) return;

        // 다음 킹의 위치가 돌의 위치와 같을 때
        if(nr == dollLoc[0] && nc == dollLoc[1]) {
            if(!canMove(dollLoc[0] + dr[idx], dollLoc[1] + dc[idx])) return;
            updateLoc(dollLoc, idx);
        }
        updateLoc(kingLoc, idx);
    }

    public static void updateLoc(int[] loc, int idx) {
        loc[0] += dr[idx];
        loc[1] += dc[idx];
    }


    public static boolean canMove(int r, int c) {
        return r >= 0 && r < length && c >= 0 && c < length;
    }


}