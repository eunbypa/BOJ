#include <iostream>
using namespace std;

int main() {
    int arr[10] = {0};
    int max = 0;
    int maxIdx = 0;
    for(int i = 1; i < 10; i++){
        cin >> arr[i];
        if(max < arr[i]) {
            max = arr[i];
            maxIdx = i;
        }
    }
    cout << max << endl;
    cout << maxIdx << endl;
    
    
}