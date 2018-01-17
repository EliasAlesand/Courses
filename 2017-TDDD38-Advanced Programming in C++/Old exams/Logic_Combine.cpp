#include <vector>
#include <iostream>
#include <iomanip>

struct All{
    bool starting_value = true;
}

template<typename It, typename Policy>
bool logic_combine(It begin, It end, Policy Policy){
    
}
// Main function. The output should be
// true
// false
// false
// true



int main()
{
    std::vector<int> vals {0,0,0,1};
    auto b { begin(vals) };
    auto e { end(vals) };
    using namespace std;
    cout << boolalpha
         << logic_combine(b,e, AtLeast{1}) << '\n'
         << logic_combine(b,e, AtLeast{8}) << '\n'
         << logic_combine(b,e, Any{}) << '\n'
         << logic_combine(b,e) << '\n';
}