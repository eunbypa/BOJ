import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] divided; // 쇠막대기 각각 나눠진 조각 개수 저장
    static char[] line;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine().toCharArray();
        int length = line.length;
        int size;
        divided = new int[length / 2];
        int idx = 0;
        for (int i = 0; i < length; i++) {
            if(i != length-1 && line[i] == '(' && line[i+1] == ')'){
                // 레이저 등장
                for (int j = 0; j < idx; j++) {
                    divided[j]++;
                }
                i++;
            }
            //쇠막대기 시작
            else if(line[i] == '('){
                divided[idx] = 1;
                idx++;
            }
            //쇠막대기 끝
            else if(line[i] == ')'){
                idx--;
                cnt += divided[idx];
                divided[idx] = 0;
            }
        }
        System.out.println(cnt);
    }



}