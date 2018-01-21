#include <iostream>
#include <vector>
#include <iterator>
#include <locale>
#include <algorithm>

using namespace std;
int main(){
    vector<string> v{istream_iterator<string>{cin},istream_iterator<string>{}};
    cout << v.size()<< " words read." <<endl;
    for_each(begin(v), end(v), [](string & s){
        transform(begin(s), end(s),begin(s), ::tolower);
    });
    sort(begin(v), end(v));
    v.erase(unique(begin(v), end(v)), end(v));
    cout << v.size()<< " unique words found." <<endl<<"The unique words in alphabetical order:"<<endl;
    copy(begin(v), end(v), ostream_iterator<string>{cout, " "});
    stable_sort(begin(v), end(v),[](string const & first, string const & second){
        return first.length() < second.length();
    });
    cout << endl;
    copy(begin(v), end(v), ostream_iterator<string>{cout, " "});
}