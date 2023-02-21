#include <iostream>
#include <vector>
#include <cstdlib>
#include <cstring>
#include <string>
#include <cmath>
using namespace std;
int M;
string number;
int maxCnt = 1e9;
vector<int> broken(10, 0);
void DFS(string entered, int cnt) { // 입력받은 숫자, 버튼 누른 수
    if (entered.length() < 6) {
        for (int i = 0; i < 10; ++i) {
            if (broken[i] == 1) continue;
            //char tmp[6];
            //strcpy_s(tmp, entered);
            //tmp[strlen(tmp)] = (char)i;
            string tmp = entered;
            tmp.push_back((char)(i+48));
            DFS(tmp, cnt + 1);
        }
    }
    //cout << "현재 입력 숫자 : " << stoi(entered) << endl;
    if (entered.length() == 0) { // 입력받은 숫자가 없음, 100번을 시작으로 함
        cnt += abs(100 - stoi(number));
        maxCnt = min(maxCnt, cnt);
        return;
    }
    cnt += abs(stoi(entered) - stoi(number));
    maxCnt = min(maxCnt, cnt);
    //cout << "현재 최소 횟수 : " << maxCnt << endl;
}

int main() {
    cin >> number;
    cin >> M;
    if (stoi(number) == 100) cout << "0" << endl;
    else {
        for (int i = 0; i < M; ++i) {
            int n;
            cin >> n;
            broken[n] = 1; // n번 숫자 버튼 고장남
        }
        DFS("", 0);
        cout << maxCnt << endl;
    }
    return 0;
}