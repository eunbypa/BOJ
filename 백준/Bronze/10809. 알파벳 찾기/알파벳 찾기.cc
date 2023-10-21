#include <iostream>
#include <string>
#include <string.h>
using namespace std;

int main() {
    int apb[26] = {0};
    memset(apb, -1, sizeof(int) * 26);
    string s;
    cin >> s;
    int l = s.length();
    for(int i = 0; i < l; i++){
        if(apb[s[i]-'a'] == -1){
            apb[s[i]-'a'] = i;
        }
    }
    for(int i = 0; i < 26; i++){
        cout << apb[i];
        if(i == 25) break;
        cout << " ";
    }
    cout << endl;
}