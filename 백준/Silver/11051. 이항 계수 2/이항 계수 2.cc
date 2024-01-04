#include <iostream>
using namespace std;

int main() {
    int N, K;
    long dp[1001][1001] = {0,};
    cin >> N >> K;
    dp[1][0] = 1;
    dp[1][1] = 1;
    for(int i = 2; i <= N; i++) {
        for(int j = 0; j <= i; j++) {
            dp[i][j] = (dp[i-1][j-1] % 10007 + dp[i-1][j] % 10007) % 10007;
        }
    }
    cout << dp[N][K] << endl;
}