#include <iostream>
using namespace std;

int A[5000]{};

int sugar(int n) {
    if(n <= 5) {
        if(n == 5 || n == 3) return 1;
        else return -1;
    }
    if(A[n] != 0) return A[n];
    for(int i = 3; i <= (n/2); i++)
    {
        int a = sugar(i);
        int b = sugar(n-i);
        if((a != -1) && (b != -1)){
            if(A[n] == 0) A[n] = a + b;
            else {
                if(A[n] > a + b) {
                    A[n] = a + b;
                }
            }
        }
    }
    if(A[n] == 0) A[n] = -1;
    return A[n];
}


int main() {
    int n;
    int result;
    cin >> n;
    result = sugar(n);
    cout << result << endl;
}