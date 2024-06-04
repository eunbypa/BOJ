import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static final int MAX_SIZE = 200002;

    static class PQ {
        int[] arr;
        int size; // 현재 배열에 삽입된 원소 개수

        // 생성자 초기화
        public PQ() {
            arr = new int[MAX_SIZE];
            size = 0;
        }


        // 새로운 노드 추가
        public void add(int n) {
            if(size+1 == MAX_SIZE) return; // 포화 상태
            arr[size+1] = n;
            size++;
            if(size > 1) update();
        }

        // 새로운 노드 추가 시, 루트로 타고 올라가면서 최대 힙 상태 재설정
        private void update() {
            int curIdx = size, pIdx;
            int tmp;
            while(curIdx > 1 && (arr[curIdx/2] < arr[curIdx])) {
                pIdx = curIdx/2;
                swap(curIdx, pIdx);
                curIdx = pIdx;
            }
        }

        // 루트 노드 삭제
        public int pop() {
            if(size == 0) return 0;
            int root = arr[1];
            arr[1] = arr[size];
            arr[size] = 0;
            size--;
            if(size > 1) fix();
            return root;
        }

        // pop 이후 최대 힙 상태를 유지하기 위하여, 가장 마지막에 삽입된 노드를 루트로 옮기고
        // 자식들과 비교하여 리프 노드까지 탐색하며 알맞은 위치 재설정
        private void fix() {
            int curIdx = 1, lcIdx, rcIdx;
            int maxNum;
            while(curIdx < MAX_SIZE) {
                lcIdx = curIdx * 2;
                rcIdx = lcIdx + 1;
                if (arr[lcIdx] == 0 && arr[rcIdx] == 0) {
                    break;
                }
                maxNum = Math.max(arr[curIdx], Math.max(arr[lcIdx], arr[rcIdx]));
                if(maxNum == arr[curIdx]) break;
                if(maxNum == arr[lcIdx]) {
                    swap(curIdx, lcIdx);
                    curIdx = lcIdx;
                }
                else {
                    swap(curIdx, rcIdx);
                    curIdx = rcIdx;
                }
            }
        }

        private void swap(int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

    }


    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PQ pq = new PQ();
        N = Integer.parseInt(br.readLine());
        int x;
        for (int i = 0; i < N; i++) {
            x = Integer.parseInt(br.readLine());
            if(x == 0) {
                // 최대값 출력
                sb.append(pq.pop()).append("\n");
            }else {
                pq.add(x);
            }
        }

        System.out.println(sb.toString());
    }

}