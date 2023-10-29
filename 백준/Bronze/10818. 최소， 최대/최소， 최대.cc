#include <iostream>
using namespace std;

int main() {
    int N;
    cin >> N;
    int min = 10000001, max = -10000001;
    int tmp;
    for(int i = 0; i < N; i++) {
        cin >> tmp;
        if(min > tmp) min = tmp;
        if(max < tmp) max = tmp;
    }
    cout << min << " " << max << endl;
}    