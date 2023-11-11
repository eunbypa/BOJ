import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<String> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < 5; i++) {
            list.add(br.readLine());
            if(max < list.get(i).length()) max = list.get(i).length();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < 5; j++) {
                if(list.get(j).length() <= i) continue;
                sb.append(list.get(j).charAt(i));
            }
        }
        System.out.println(sb.toString());
    }



}