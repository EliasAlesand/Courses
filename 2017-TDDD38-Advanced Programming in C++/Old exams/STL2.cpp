#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
#include <numeric>

using namespace std;
int main(){
    int N,T;
    cin >> N >> T;
    vector<int> input_values(N);
    copy_n(istream_iterator<int>(cin), N, begin(input_values));
    vector<int> sums(N);
    partial_sum(begin(input_values), end(input_values), begin(sums));

    int t;
    while (cin>>t){cout << sums[t-1];}
    return 0;
}