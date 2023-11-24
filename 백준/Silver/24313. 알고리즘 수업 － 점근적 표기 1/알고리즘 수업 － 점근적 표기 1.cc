#include <iostream>
using namespace std;

int main() {
    int a1, a0, c, n0;
    cin >> a1 >> a0 >> c >> n0;
    int a = c - a1;
    float b;
    int res;
    if(a == 0) {
        if(a0 > 0) res = 0;
        else res = 1;
    }else if(a < 0) {
        res = 0;
    }else {
        b = a0 / (float)a;
        if(n0 >= b) res = 1;
        else res = 0;
    }
    cout << res << endl;
}