#include <iostream>
using namespace std;

int main() {
    int n;
    cin >> n;
    if(n % 2 == 0) // n이 짝수
        cout << "SK" << endl;
    else // n이 홀수
        cout << "CY" << endl;
}