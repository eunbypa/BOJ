#include <iostream>
using namespace std;

int main() {
    int N;
    int arr[110] = {0,};
    cin >> N;
    for(int i = 1; i <= N; i++) {
        cin >> arr[i];
    }
    int min = 0;
    for(int i = N; i > 0; i--) {
        while(arr[i] <= arr[i-1]){
            arr[i-1]--;
            min++;
        }
    } 
    cout << min << endl;
}