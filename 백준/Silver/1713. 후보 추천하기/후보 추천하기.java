import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Candidate implements Comparable<Candidate> {

        int num; // 번호
        int created_time; // 후보로 게시된 시각
        int recommendCnt; // 추천 횟수

        public Candidate(int num, int time) {
            this.num = num;
            this.created_time = time;
            this.recommendCnt = 1;
        }

        @Override
        public int compareTo(Candidate o) {
            // 추천수가 같은 경우 게시된 시간 기준으로 정렬
            if(this.recommendCnt == o.recommendCnt) {
                return this.created_time - o.created_time;
            }
            return this.recommendCnt - o.recommendCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int totalRecommendCnt = Integer.parseInt(br.readLine());
        int[] recommendStudents = new int[totalRecommendCnt];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < totalRecommendCnt; i++) {
            recommendStudents[i] = Integer.parseInt(st.nextToken());
        }
        List<Integer> finalCandidateList = getFinalCandidates(N, recommendStudents);
        StringBuilder sb = new StringBuilder();
        int size = finalCandidateList.size();
        for (int i = 0; i < size; i++) {
            sb.append(finalCandidateList.get(i));
            if(i == size-1) break;
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    // 최종 후보의 번호 배열 반환
    static List<Integer> getFinalCandidates(int N, int[] recommendStudents) {
        int l = recommendStudents.length;
        Candidate[] candidates = new Candidate[N];
        int registeredCnt = 0; // 사진틀 게시된 후보수
        int recStd, idx;
        Candidate newCandidate;
        for (int i = 0; i < l; i++) {
            recStd = recommendStudents[i];
            idx = getCandidateIdx(candidates, recStd);
            if(idx == -1) {
                // 사진틀 게시 안된 경우
                newCandidate = new Candidate(recStd, i);
                if(registeredCnt == N) {
                    // 사진틀 만석
                    candidates[0] = newCandidate;
                }else {
                    // 아직 사진틀 공석 존재
                    candidates[registeredCnt++] = newCandidate;
                }
            }else {
                // 사진틀 게시된 경우
                candidates[idx].recommendCnt++;
            }
            Arrays.sort(candidates, 0, registeredCnt);
        }

        List<Integer> finalCandidateList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if(candidates[i] == null) break;
            finalCandidateList.add(candidates[i].num);

        }
        Collections.sort(finalCandidateList);
        return finalCandidateList;
    }

    // 번호와 일치하는 후보자의 배열상 idx 반환 / 없으면 -1 반환
    static int getCandidateIdx(Candidate[] candidates, int num) {
        int l = candidates.length;
        for (int i = 0; i < l; i++) {
            if(candidates[i] == null) break;
            if(candidates[i].num == num)
                return i;
        }
        return -1;
    }

}