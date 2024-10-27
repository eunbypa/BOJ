#include <iostream>
#include <string>
using namespace std;

int main() {
    int r1, r2, r3;
    cin >> r1 >> r2 >> r3;
    string type;
    if(r1 == 60 && r2 == 60 && r3 == 60) {
        type = "Equilateral";
    }else if(r1+r2+r3 == 180 && (r1 == r2 || r1 == r3 || r2 == r3)){
        type = "Isosceles";
    }else if(r1+r2+r3 == 180){
        type = "Scalene";
    }else {
        type = "Error";
    }
    cout << type << endl;
}