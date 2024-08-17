#include <iostream>
#include <string>
using namespace std;

int main() {
    string s;
    cin >> s;
    int l = s.length();
    int cnt = 0;
    for(int i = 0; i < l; i++) {
        if(s.at(i) == 'a' || s.at(i) == 'e' || 
           s.at(i) == 'i' || s.at(i) == 'o' || 
           s.at(i) == 'u') cnt++;
    }
    cout << cnt << endl;
}