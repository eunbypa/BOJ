#include <iostream>
#include <string>
#include <vector>
#include <cmath>
#include <algorithm>
using namespace std;

class FireBall {
public:
	int r;
	int c;
	int m;
	int s;
	int d;
public:
	FireBall(int r, int c, int m, int s, int d) : r(r), c(c), m(m), s(s), d(d) {};
};

int N, M, K, answer;
int dr[8] = { -1,-1,0,1,1,1,0,-1 };
int dc[8] = { 0,1,1,1,0,-1,-1,-1 };
vector<FireBall> map[51][51];
vector<pair<pair<int,int>,FireBall>> result; // 이동 후 위치를 저장한 파이어볼들
//범위 밖인지 검사
bool check(int r, int c) {
	return r >= 1 && c >= 1 && r <= N && c <= N;
}
//파이어볼 움직이기
void moving(int r, int c) {
	for (int i = map[r][c].size() - 1; i >= 0; i--) {
		FireBall fb = map[r][c][i];
		map[r][c].erase(map[r][c].begin() + i);
		int nr = r + dr[fb.d] * (fb.s % N);
		int nc = c + dc[fb.d] * (fb.s % N);
		if (!check(nr, nc)) { // 범위를 벗어남
			if (dr[fb.d] != 0 && nr <= 0 || nr > N) nr = abs(N - abs(nr));
			if (dc[fb.d] != 0 && nc <= 0 || nc > N) nc = abs(N - abs(nc));
		}
		result.push_back({ {nr,nc},FireBall(nr,nc,fb.m,fb.s,fb.d) });
	}
}
//같은 칸에 2개 이상 있는 파이어볼을 합치고 4개로 나누기
void mergeAndDivide(int r, int c) {
	int mSum = 0; //질량
	int sSum = 0; // 속력
	int cnt = map[r][c].size(); // 파이어볼 개수
	int sample = map[r][c][0].d % 2;
	bool same = true; // 모두 홀수거나 짝수인지
	for (int i = map[r][c].size()-1; i >= 0; i--) {
		mSum += map[r][c][i].m;
		sSum += map[r][c][i].s;
		if (map[r][c][i].d % 2 != sample) same = false; // 모두 홀수나 짝수가 아님을 의미
		map[r][c].erase(map[r][c].begin() + i);
	}
	int m, s, d;
	m = mSum / 5;
	s = sSum / cnt;
	if (m == 0) return; // 질량이 0이면 소멸
	if (same) d = 0;
	else d = 1;
	for (int i = 0; i < 4; i++) {
		map[r][c].push_back(FireBall(r,c,m, s, d));
		d += 2;
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	cin >> N >> M >> K;
	int r, c, m, s, d;
	for (int i = 0; i < M; i++)
	{
		cin >> r >> c >> m >> s >> d;
		map[r][c].push_back(FireBall(r,c,m, s, d));
	}
	while (K > 0) {
		//이동
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (map[i][j].size() == 0) continue; // 파이어볼이 없는 칸
				moving(i, j);
			}
		}
		for (int i = result.size() - 1; i >= 0; i--) {
			map[result[i].first.first][result[i].first.second].push_back(result[i].second);
			result.erase(result.begin() + i);
		}
		//이동 끝난 후 
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (map[i][j].size() < 2) continue; // 파이어볼이 2개 미만으로 있는 칸
				mergeAndDivide(i, j);
			}
		}
		K--;
	}
	for (int i = 1; i < N + 1; i++) {
		for (int j = 1; j < N + 1; j++) {
			if (map[i][j].size() == 0) continue; // 파이어볼이 없는 칸
			for (int k = 0; k < map[i][j].size(); k++) {
				answer += map[i][j][k].m;
			}
		}
	}
	cout << answer << endl;
}
