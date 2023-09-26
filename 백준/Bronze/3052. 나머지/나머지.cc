#include <iostream>
using namespace std;

int main(){
    int arr[42] = {};
    int A;
    int B = 42;
    for(int i = 0; i < 10; i++){
        cin >> A;
        arr[A%B]++;
    }
    int cnt = 0;
    for(int i = 0; i < 42; i++){
        if(arr[i] == 0) continue;
        cnt++;
    }
    cout << cnt << endl;
    
}