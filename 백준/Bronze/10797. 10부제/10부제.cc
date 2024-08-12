#include <iostream>
using namespace std;

int arr[5];

int main() {
    int day;
    cin >> day;
    int cnt = 0;
    for(int i = 0; i < 5; i++) {
        cin >> arr[i];
        if(day == arr[i]) cnt++;
    }
    cout << cnt << endl;
}