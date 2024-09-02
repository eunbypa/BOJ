import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final String zero = "0";
    static final String zeroAdd = "0000";
    static final int ipv6SepCnt = 8;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String shortedIpv6Add = br.readLine();
        List<String> addList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(shortedIpv6Add, ":");
        // : 으로 주소 문자열 각각 분리
        while(st.hasMoreTokens())
            addList.add(st.nextToken());
        // :: 가 나오는 지점 어디인지 찾기
        int twoColIdx = getTwoColIdx(shortedIpv6Add);
        System.out.println(recoverAdd(addList, twoColIdx));
    }

    // :: 가 나오는 번지수 찾아서 반환
    static int getTwoColIdx(String add) {
        int e = add.length();
        int idx = 1;
        for (int i = 0; i < e-1; i++) {
            if(add.charAt(i) == ':' && add.charAt(i+1) == ':') {
                if (i == 0) idx = 0;
                return idx;
            }
            else if(add.charAt(i) == ':')
                idx++;
        }
        // 없는 경우
        return -1;
    }

    static String recoverAdd(List<String> addList, int twoColIdx) {
        StringBuilder sb = new StringBuilder();
        int size = addList.size();
        // 주소 복원
        int l, cnt = 0;

        for (int i = 0; i < size; i++) {
            if(i == twoColIdx) {
                // 0으로만 이루어져 생략된 부분 복구
                for (int j = 0; j < 8-size; j++) {
                    cnt++;
                    sb.append(zeroAdd);
                    if(cnt == ipv6SepCnt) break;
                    sb.append(":");
                }
            }
            l = addList.get(i).length();
            cnt++;
            while(l++ < 4) {
                sb.append(zero);
            }
            l = addList.get(i).length();
            for (int j = 0; j < l; j++)
                sb.append(addList.get(i).charAt(j));
            if(cnt != ipv6SepCnt)
                sb.append(":");
        }
        if(cnt != ipv6SepCnt) {
            // 0으로만 이루어져 생략된 부분 복구
            for (int j = 0; j < 8-size; j++) {
                cnt++;
                sb.append(zeroAdd);
                if(cnt == ipv6SepCnt) break;
                sb.append(":");
            }
        }

        return sb.toString();
    }


}