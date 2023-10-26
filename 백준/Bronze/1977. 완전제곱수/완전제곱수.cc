#include <iostream>
using namespace std;

int main() {
    int M, N;
    int arr[10001] = {0};
    cin >> M;
    cin >> N;
    for(int i = 1; i <= 100; i++){
        arr[i*i] = 1;
    }
    int sum = 0;
    int min = 0;
    for(int i = M; i <= N; i++) {
        if(arr[i] == 1){
            sum += i;
            if(min == 0) min = i;
        }
    }
    if(sum == 0) cout << "-1" << endl;
    else {
        cout << sum << endl;
        cout << min << endl;
    }
}