import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, cnt;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[] word;
        for (int t = 1; t <= N; t++) {
            word = br.readLine().toCharArray();
            if(checkGroupWord(word)) cnt++;
        }

        System.out.println(cnt);
    }

    // 그룹 단어인지 검사하는 함수
    static boolean checkGroupWord(char[] word){
        int i = 0, length = word.length;
        int[] apb = new int[26];
        char c;
        while(i < length) {
            if(apb[word[i]-'a'] == 0){ // 그전까지 한번도 등장하지 않았던 알파벳이면
                apb[word[i]-'a']++;
                c = word[i];
                while(i < length){
                    if(c != word[i]) break;
                    i++;
                }
            }else return false; // 그 전에 등장한 적이 있는 알파벳 즉, 연속해서 나타나지 않은 상태
        }
        return true;
    }

}