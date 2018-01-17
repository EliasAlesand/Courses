#include <iostream>
#include <vector>
#include <iterator>
#include <map>
#include <algorithm>
using namespace std;
int main(){
    vector<string> words{istream_iterator<string>{cin},istream_iterator<string>{}};

    map<string, int> freq;
    for_each(begin(words), end(words),
    [&freq](string s){freq[s]++;});

    freq.sort();
    return 0;
}