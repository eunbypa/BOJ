#include <iostream>
using namespace std;

int main() {
    int a, b;
    cin >> a >> b;
    int minA = 0, minB = 0, maxA = 0, maxB = 0;
    int ten = 1;
    while(a > 0 || b > 0) {
        if(a > 0) {
            if(a % 10 == 5) {
                maxA += 6*ten;
                minA += 5*ten;
            }else if(a % 10 == 6) {
                maxA += 6*ten;
                minA += 5*ten;
            }else {
                maxA += (a % 10)*ten;
                minA += (a % 10)*ten;              
            }
        }
        if(b > 0) {
            if(b % 10 == 5) {
                maxB += 6*ten;
                minB += 5*ten;
            }else if(b % 10 == 6) {
                maxB += 6*ten;
                minB += 5*ten;
            }else {
                maxB += (b % 10)*ten;
                minB += (b % 10)*ten;  
            }
        }
        a /= 10;
        b /= 10;
        ten *= 10;
    }
    cout << (minA+minB) << " " << (maxA+maxB) << endl;
   
}