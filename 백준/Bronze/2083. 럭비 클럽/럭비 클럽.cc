#include <iostream>
#include <string>
using namespace std;

int main() {
    const string SENIOR = "Senior", JUNIOR = "Junior";
    
    string name;
    int age, weight;
    string group;
    do {
        cin >> name >> age >> weight;
        if(name.compare("#") != 0 || age != 0 || weight != 0) {
            if(age > 17 || weight >= 80) group = SENIOR;
            else group = JUNIOR;
            cout << name << " " << group << endl;
        }
    }while(name.compare("#") != 0 || age != 0 || weight != 0);
    
}