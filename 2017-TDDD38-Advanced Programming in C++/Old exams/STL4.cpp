#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>
#include <numeric>
#include <cmath>
#include <iomanip>

using namespace std;

int main(){
    vector<int> v{istream_iterator<int>{cin}, istream_iterator<int>{}};
    int n_of_elements = v.size();
    int sum = accumulate(begin(v), end(v), 0);
    double mean = static_cast<double>(sum)/static_cast<double>(n_of_elements);
    int mid = n_of_elements/2;
    nth_element(begin(v), begin(v)+mid, end(v));
    double median = v.at(mid);
    if (n_of_elements % 2 == 0){
        nth_element(begin(v), begin(v)+mid-1, end(v));
        median = (median+v.at(mid-1))/2;
    }
    double diffs{};
    for_each(begin(v), end(v),[&diffs, mean](int val){diffs+=pow((val-mean), 2);});
    double stddev = diffs/n_of_elements;
    stddev = sqrt(stddev);
    auto minmax = minmax_element(begin(v), end(v));
    cout << setfill('.')<<left;
    cout <<setw(18)<<"min value"<<": "<< *minmax.first<<endl;
    cout <<setw(18)<<"max value"<<": "<< *minmax.second<<endl;
    cout <<setw(18)<<"mean"<<": "<< mean<<endl;
    cout <<setw(18)<<"median"<<": "<< median<<endl;
    cout <<setw(18)<<"standard deviation"<<": "<< stddev<<endl;
    return 0;
}