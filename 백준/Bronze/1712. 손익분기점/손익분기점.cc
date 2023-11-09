#include <iostream>
using namespace std;

int main() {
    int A,B,C;
    cin >> A >> B >> C;
    if(B >= C) cout << -1 << endl;
    else {
        int n = A / (C-B);
        cout << n+1 << endl;
    }
}