#include <iostream>
#include <string>
#include <sstream>
using namespace std;

int main() {
    int T;
    cin >> T;
    string s, next;
    for(int i = 0; i < T; i++){
        cin >> s;
        stringstream sst(s);
        int sum = 0;
        while (getline(sst, next, ',')) sum += stoi(next);
        cout << sum << endl;
    }
}