#include <iostream>
using namespace std;

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    int n;
    cin >> n;
    int sum = 0, cur;
    for (int i = 0; i < n; i++){
        sum += i;
        cin >> cur;
        sum -= cur;
    }
    cout << -sum;
}