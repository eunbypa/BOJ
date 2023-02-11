#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
using namespace std;

int N, answer; // 전깃줄의 개수, 정답
int dp[500];
vector<pair<int, int>> lines; // 각 전깃줄 시작점 끝점
bool cmp(pair<int, int>& a, pair<int, int> & b) {
	return a.first < b.first; // 오름차순 정렬
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	cin >> N;
	lines.resize(N);
	for (int i = 0; i < N; i++)
	{
		int s, e;
		cin >> s >> e;
		lines[i] = {s,e};
	}
	sort(lines.begin(), lines.end()); // 시작점을 기준으로 정렬
	int cnt = 0; // 증가하는 부분 길이
	for (int i = 0; i < N; i++) {
		dp[i] = 1;
		for (int j = i-1; j >= 0; j--) {
			if (lines[i].second > lines[j].second) {
				dp[i] = max(dp[i], dp[j] + 1); // 가장 길게 증가하는 부분을 찾아야 함
			}
		}
		cnt = max(cnt, dp[i]);
	}
	answer = N - cnt;
	cout << answer;
}
