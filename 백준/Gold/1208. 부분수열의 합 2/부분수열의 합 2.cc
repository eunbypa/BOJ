#include <iostream>
#include <map>

using namespace std;
int n, s;
map<int, int> m;
int nums[41];
long long cnt;
void rightSum(int idx, int sum){
    if(idx == n){
        m[sum]++;
        return;
    }
    rightSum(idx+1, sum);
    rightSum(idx+1, sum+nums[idx]);
}
void leftSum(int idx, int sum){
    if(idx == (n/2)){
        cnt += m[s-sum];
        return;
    }
    leftSum(idx+1, sum);
    leftSum(idx+1, sum+nums[idx]);
}
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> n >> s;
    for(int i = 0; i < n; i++){
        cin >> nums[i];
    }
    rightSum(n/2, 0);
    leftSum(0, 0);
    if(s==0) cout << cnt-1 << endl;
    else cout << cnt << endl;
}