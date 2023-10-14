#include <iostream>
using namespace std;

int main() {
    int T, A, B;
    cin >> T;
    int res, min, max, end;
    for(int i = 0; i < T; i++){
        cin >> A >> B;
        if(A < B) min = A;
        else min = B;
        if(A > B) max = A;
        else max = B;
        end = A*B;
        for(int j = min; j <= end; j+=min){
            if(j % max == 0) {
                res = j;
                break;
            }
        }
        cout << res << endl;
    }

}