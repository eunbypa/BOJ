import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String word = br.readLine();
        String shortWord = getShortWord(word);

        System.out.println(getAnswer(shortWord));
    }

    static int getAnswer(String shortWord) {
        int[] colorCnt = new int[2];
        int l = shortWord.length();
        for (int i = 0; i < l; i++) {
            switch (shortWord.charAt(i)) {
                case 'R':
                    colorCnt[0]++;
                    continue;
                case 'B':
                    colorCnt[1]++;
                    continue;
                default:
            }
        }

        return (colorCnt[0] >= colorCnt[1]) ? 1 + colorCnt[1] : 1 + colorCnt[0];
    }

    static String getShortWord(String word) {
        StringBuilder sb = new StringBuilder();
        int l = word.length();
        char cur = word.charAt(0);
        sb.append(word.charAt(0));
        for (int i = 1; i < l; i++) {
            if(cur == word.charAt(i)) continue;
            sb.append(word.charAt(i));
            cur = word.charAt(i);
        }

        return sb.toString();
    }

}