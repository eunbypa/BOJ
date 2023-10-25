#include <iostream>
#include <string>
using namespace std;

int main() {
    int arr[8] = {0};
    string result;
    for(int i = 0; i < 8; i++) {
        cin >> arr[i];
    }
    if(arr[0] == 1){
     for(int i = 0; i < 8; i++) {
        if(arr[i] != i+1) {
            result = "mixed";
            break;
        }
        if(i == 7) result = "ascending";
     }  
    }else if(arr[0] == 8){
       for(int i = 0; i < 8; i++) {
        if(arr[i] != 8-i){
            result = "mixed";
            break;
        }
        if(i == 7) result = "descending";
     }
    } else result = "mixed";
    cout << result << endl;
}