#include<bits/stdc++.h>
using namespace std;
int n, check[10];
char a[14];
vector<string> ret;
void go(string s, int index){
    // 길이가 원하는 만큼 되어야지 스탑
    if(index == n + 1){
        ret.push_back(s);
        return;
    }
    for(int i = 0; i <= 9; i++){
        if(check[i]) continue;
        if(index == 0){
            check[i] = 1;
            go(s + to_string(i), index + 1);
            check[i] = 0;
            continue;
        }
        else if(a[index - 1] == '>'){
            if(s[index - 1] > i + '0'){
                check[i] = 1;
                go(s + to_string(i), index + 1);
                check[i] = 0;
                continue;
            }
        }
        else if(a[index - 1] == '<'){
            if(s[index - 1] < i + '0'){
                check[i] = 1;
                go(s + to_string(i), index + 1);
                check[i] = 0;
                continue;
            }
        }
    }

}
int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> n;
    for(int i = 0; i < n; i++){
        cin >> a[i];
    }
    go("", 0);
    sort(ret.begin(), ret.end());
    cout << ret[ret.size() - 1] << '\n' << ret[0] << '\n';
}