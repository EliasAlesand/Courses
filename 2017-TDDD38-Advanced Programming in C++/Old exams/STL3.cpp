using num_type = unsigned long long;
using namespace std;
#include <iostream>
#include <vector>
#include <experimental/random>
#include <algorithm>
#include <iterator>

bool is_prime(num_type val, num_type divisor=2)
{
    if ( divisor*divisor > val )
    {
        return true;
    }
    if ( val % divisor == 0 )
    {
        return false;
    }
    return is_prime(val, ++divisor);
}

int main(){
    vector<num_type> v(5);
    for_each(begin(v),end(v),[=](num_type & i){i = std::experimental::randint(2,75);});
    cout << v[2]<<endl;
    cout << accumulate(begin(v),end(v),1,multiplies<>{})<<endl;
    return 0;
}