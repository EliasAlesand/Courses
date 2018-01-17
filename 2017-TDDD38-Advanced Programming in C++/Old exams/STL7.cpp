#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>
#include <map>
using namespace std;

int main(){
    //1
    vector<string> v{istream_iterator<string>{cin}, istream_iterator<string>{}};
    //2
    map<string, int> m;
    for_each(begin(v), end(v), [&m, &v](string const & i){m.emplace(i, count(begin(v), end(v), i));});
    //3
    vector<pair<string,int>> mv{make_move_iterator(begin(m)), make_move_iterator(end(m))};
    sort(begin(mv), end(mv), [](pair<string, int> p1,pair<string, int> p2){return p1.second > p2.second;});
    //4
    vector<string> replace(10);
    transform(begin(mv), next(begin(mv), 10), begin(replace), [](auto P){return P.first;});
    /*
    for(auto map : replace){
        cout << map <<": "<<map<<endl;
    }
    */
    return 0;
}