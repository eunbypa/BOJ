#include <iostream>
using namespace std;

int main() {
    int sum = 0;
    int n;
    for(int i = 0; i < 5; i++) {
        cin >> n;
        if(n < 40) n = 40;
        sum += n;
    }
    int avg = sum / 5;
    cout << avg << endl;
    
}