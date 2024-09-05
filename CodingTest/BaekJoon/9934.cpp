#include<bits/stdc++.h>
using namespace std;
int k, b;
int a[1030];

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> k;
    b = pow(2,k) - 1;
    for(int i = 0; i < b; i++){
        cin >> a[i];
    }
    int bb = b;
    for(int i = 1; i <= bb; i *= 2){
        b /= 2;
        for(int j = 0; j < i; j++){
            int tmp = pow(2,k);
            cout << a[b + tmp * j] << " ";
        }
        k--;
        cout << '\n';
    }
}