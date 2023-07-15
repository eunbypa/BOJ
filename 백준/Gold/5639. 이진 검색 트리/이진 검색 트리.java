import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static class Node{
        int key;
        Node parent;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
        }
    }

    static Map<Integer, Integer> map = new HashMap<>(); // 각 키가 등장한 순서 저장
    static Node[] tree = new Node[10001];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n;
        int key, before = 0; // 현재 키, 전에 확인한 키
        int root, cnt = 0; // 노드 총 개수
        Node cur;
        while((n = br.readLine()) != null && !n.isEmpty()){
            key = Integer.parseInt(n);
            tree[cnt] = new Node(key);
            map.put(key, cnt);
            if(cnt != 0){ // 루트 노드 제외
                if(before > key){ // 현재 노드가 그전 노드의 왼쪽 자식임을 의미
                    root = map.get(before);
                    tree[cnt].parent = tree[root];
                    tree[root].left = tree[cnt];
                }else{ // 현재 노드가 그전에 방문한 노드 중 하나의 오른쪽 자식임을 의미
                    root = map.get(before);
                    cur = tree[root];
                    while(cur.parent != null && key > cur.parent.key){
                        cur = cur.parent;
                    }
                    while(cur.right != null){ // 이미 오른쪽 자식이 있는 경우
                        cur = cur.right;
                    }
                    tree[cnt].parent = cur;
                    cur.right = tree[cnt];
                }
            }
            before = key;
            cnt++;
        }
        dfs(tree[0]); // 루트부터 시작
        System.out.println(sb.toString());
    }

    // 깊이 우선 탐색
    public static void dfs(Node cur) {
        if(cur.left != null){ // 더이상 방문할 왼쪽 자식 노드가 존재하지 않음
            dfs(cur.left);
        }
        if(cur.right != null){ // 더이상 방문할 오른쪽 자식 노드가 존재하지 않음
            dfs(cur.right);
        }
        print(cur.key);
    }

    // 해당 숫자를 출력하는 함수
    public static void print(int n) {
        sb.append(n + "\n");
    }
}