#include <iostream>
using namespace std;

int main() {
    int arr[7] = {0};
    int sum = 0;
    int min = 1000;
    for(int i = 0; i < 7; i++){
        cin >> arr[i];
        if(arr[i] % 2 == 0) continue;
        sum += arr[i];
        if(arr[i] < min) min = arr[i];
    }
    if(sum == 0) cout << -1 << endl;
    else {
        cout << sum << endl;
        cout << min << endl;
    }

}