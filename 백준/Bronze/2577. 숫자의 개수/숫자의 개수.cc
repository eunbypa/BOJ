#include <iostream>
using namespace std;

int main() {
    long A, B, C;
    cin >> A;
    cin >> B;
    cin >> C;
    long mul;
    mul = A * B * C;
    int arr[10] = {0};
    while(mul > 0) {
        arr[mul % 10]++;
        mul /= 10;
    }
    for(int i = 0; i < 10; i++){
        cout << arr[i] << endl;
    }
}