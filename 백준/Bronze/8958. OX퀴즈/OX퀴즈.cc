#include <iostream>
#include <string>
using namespace std;

int main() {
    int t;
    cin >> t;
    for(int i = 0; i < t; i++){
        string s;
        cin >> s;
        int l = s.length();
        int score = 0;
        int sum = 0;
        for(int j = 0; j < l; j++){
            if(s[j] == 'O'){
                score++;
            }else {
                score = 0;
            }
            sum += score;
        }
        cout << sum << endl;
    }
}