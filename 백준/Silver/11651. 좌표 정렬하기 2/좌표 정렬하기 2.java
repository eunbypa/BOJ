import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int x,y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }


        @Override
        public int compareTo(Node o) {
            if(this.y == o.y) {
                return this.x - o.x;
            }
            return this.y-o.y;
        }
    }
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Node> nodeList = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nodeList.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));    
        }
        Collections.sort(nodeList);
        for (Node node:nodeList) {
            sb.append(node.x).append(" ").append(node.y).append("\n");
        }
        System.out.println(sb.toString());
    }

}