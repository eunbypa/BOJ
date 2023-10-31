#include <iostream>
using namespace std;

int main() {
    int A, B, C;
    cin >> A >> B >> C;
    int ans = A;
    if(ans < B && ans < C){
        if(B < C) ans = B;
        else ans = C;
    }else if(ans > B && ans > C){
        if(B < C) ans = C;
        else ans = B;
    }else ans = A;
    cout << ans << endl;
    
}