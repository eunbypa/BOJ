#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int cnt,n,fl,fl2,a1,a2,l,la,lb,sa,sb;
map<int,int> mp;
stack<int> st;
vector<pair<int,int>> v;
int main(){
    scanf("%d",&n);
    for(int i=1;i<=n;i++){
        int a,b;
        scanf("%d %d",&a,&b);

        if(i==1 || (!fl2 && b>0)){
            v.push_back({a,b});
            continue;
        }
        if(!fl2) sa=a,sb=b;
        fl2=1;

        if((b>0&&lb<0) || (b<0&&lb>0)){
            if(fl) mp[a]=cnt,fl=0;
            else mp[a]=++cnt,fl=1;
        }
        la=a,lb=b;
    }

    for(pair<int,int> p : v){
        int a=p.first,b=p.second;

        if((b>0&&lb<0) || (b<0&&lb>0)){
            if(fl) mp[a]=cnt,fl=0;
            else mp[a]=++cnt,fl=1;
        }

        la=a,lb=b;
    }

    if((sb>0&&lb<0) || (sb<0&&lb>0)){
        if(fl) mp[la]=cnt,fl=0;
        else mp[la]=++cnt,fl=1;
    }

    for(auto it=mp.begin();it!=mp.end();it++){
        if(st.empty()){
            st.push(it->second);
            a1++;
            l=it->second;
        }else if(st.top() == it->second){
            if(st.top() == l) a2++;
            st.pop();
        }else{
            st.push(it->second);
            l=it->second;
        }
    }
    printf("%d %d",a1,a2);
}