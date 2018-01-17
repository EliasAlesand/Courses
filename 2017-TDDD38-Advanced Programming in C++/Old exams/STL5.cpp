#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>
#include <numeric>
#include <cmath>

using namespace std;

int main(){
    //1
    vector<int> v{istream_iterator<int>{cin}, istream_iterator<int>{}};
    //2
    cout << v.size()<<" values read."<<endl;
    copy(begin(v), end(v), ostream_iterator<int>{cout, " "});
    //3
    sort(begin(v), end(v), std::greater<int>());
    cout << endl;
    //4
    auto last = unique(begin(v), end(v));
    v.erase(last, end(v));
    //5
    cout << "Unique values in descending order:"<<endl;
    copy(begin(v), end(v), ostream_iterator<int>{cout, " "});
    cout << endl;
    //6
    int fraction = v.size()*0.05;
    //7
    v.erase(begin(v), begin(v)+fraction);
    v.erase(end(v)-fraction, end(v));
    copy(begin(v), end(v), ostream_iterator<int>{cout, " "});
    //8
    double mean = accumulate(begin(v), end(v), 0)/v.size();
    cout << endl << "Mean value: "<<mean<<endl;
    //9
    double diff{};
    for_each(begin(v), end(v), [mean, &diff](int & i){diff+=abs(i-mean);});
    cout << "Sum of differences: "<<diff;
    return 0;
}