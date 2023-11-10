import java.io.*;
import java.util.*;

public class Main {
  
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        System.out.println(sb.toString());
    }

}