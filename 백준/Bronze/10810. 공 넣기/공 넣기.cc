#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n, m;
    cin >> n >> m;
    int i,j,k;
    vector<int> v(n+1);
    for(int x = 0; x < m; x++) {
        cin >> i >> j >> k;
        for(int y = i; y <= j; y++) {
            v[y] = k;
        }
    }

    for(int x = 1; x <= n; x++) {
        cout << v[x];
        if(x == n) break;
        cout << " ";
    }
    
}