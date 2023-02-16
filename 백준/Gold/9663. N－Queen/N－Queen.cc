#include<iostream>
#include <vector>
#include <cstdlib>
using namespace std;
int cnt;
int N;
void DFS(vector<int> &queen, int idx){ // idx 는 열 기준
    if(queen.size() == N) {
        cnt++;
        return;
    }
    if(idx == N) return;
    bool possible;
    for(int i = 0; i < N; ++i){ // i는 행
        possible = true;
        for(int j = 0; j < queen.size(); ++j){ // j는 열
            if(i == queen[j]) {
                possible = false; // 행 일치
                break;
            }
            if(abs(queen[j] - i) == abs(j  - idx)) {
                possible = false; // 대각선 일치
                break;
            }
        }
        if(possible){
       		queen.push_back(i);
        	DFS(queen, idx + 1);
        	queen.pop_back();
        }
    }
}
int main()
{
	 cnt = 0;
     cin >> N;
     vector<int> queen;
     DFS(queen, 0);
     cout << cnt << "\n";
	return 0;
}