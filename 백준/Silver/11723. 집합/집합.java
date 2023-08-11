import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M;
    static int[] S;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        S = new int[21];
        String command;
        int x;
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            command = st.nextToken();
            switch (command){
                case "add":
                    x = Integer.parseInt(st.nextToken());
                    add(x);
                    break;
                case "remove":
                    x = Integer.parseInt(st.nextToken());
                    remove(x);
                    break;
                case "check":
                    x = Integer.parseInt(st.nextToken());
                    sb.append(check(x)+"\n");
                    break;
                case "toggle":
                    x = Integer.parseInt(st.nextToken());
                    toggle(x);
                    break;
                case "all":
                    all();
                    break;
                case "empty":
                    empty();
                    break;
            }
        }
        System.out.println(sb.toString());
    }

    static void add(int x){
        if(S[x] == 1) return;
        S[x] = 1;
    }

    static void remove(int x){
        if(S[x] == 0) return;
        S[x] = 0;
    }

    static int check(int x){
        if(S[x] == 0) return 0;
        return 1;
    }

    static void toggle(int x){
        if(S[x] == 0) S[x] = 1;
        else S[x] = 0;
    }

    static void all(){
        for (int i = 1; i <= 20; i++) {
            S[i] = 1;
        }
    }

    static void empty(){
        for (int i = 1; i <= 20; i++) {
            S[i] = 0;
        }
    }

}