#include <iostream>
#include <iterator>
#include <vector>
#include <list>
using namespace std;

struct Descending{
    
    template<typename T>
    static bool Order(T first, T second){
        if (first > second){
            return true;
        }
        else return false;
    }
};

struct Ascending{
    template<typename T>
    static bool Order(T first, T second){
        if (first < second){
            return true;
        }
        else return false;
    }
};

template<typename T, class ordering_policy = Ascending>
struct Sort{
    template<typename Iter>
    static void sort(Iter begin, Iter end)
    {
        for ( ; next(begin) != end; ++begin)
        {
            Iter min = begin;
            for (Iter pos = next(begin); pos != end; ++pos)
                if (ordering_policy::Order(*pos,*min))
                    min = pos;
            std::iter_swap(begin, min);
        }
    }
};

int main()
{
    /*
    int arr[] = {2,3,5,1,6,8};
    ::sort(begin(arr), end(arr));
    bool first {true};
    for ( auto i : arr )
    {
        if ( !first )
            cout << ", ";
        first = false;
        cout << i;
    }
    cout << endl;
    */
    
    vector<int> values {2,3,6,8,3};
    Sort<int, Ascending>::sort(begin(values), end(values));
    

    list<string> words {"hi", "hello", "all", "students"};
    Sort<string, Ascending>::sort(begin(words), end(words));
    
    bool first {true};
    for ( auto i : words )
    {
        if ( !first )
            cout << ", ";
        first = false;
        cout << i;
    }
    cout << endl;
}