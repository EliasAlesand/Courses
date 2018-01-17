#include <vector>
#include <algorithm>
#include <forward_list>
#include <iostream>
using namespace std;

template<typename Iter, typename Func = std::less<typename Iter::value_type>> 
void ssort(Iter begin, Iter end, Func comparison={})
{
    
    for ( auto idx = begin; idx!=end; ++idx )
    {
        auto smallest_at_index {idx};
        for ( auto cur {idx}; cur != end; ++cur )
        {
            if ( comparison(*smallest_at_index, *cur) )
            {
                smallest_at_index = cur;
            }
        }
        iter_swap(idx, smallest_at_index);
    }
}

int main(){
    
    forward_list <string > lst {"hello", "this", "is", "a", "test", "wjwkkawwk"};
    ssort(begin(lst), end(lst),
    [](string a, string b){
    return a.length() < b.length();
    });

    for (string s : lst){
        cout << s<<endl;
    }

    vector<int> v{2, 9, 1, 2, 5, 4};
    ssort(begin(v), end(v));

    for (int i : v){
        cout << i<<endl;
    }
    return 0;
    
}