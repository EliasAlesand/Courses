#include <iostream>
#include <vector>
#include <iterator>
#include <list>
using namespace std;

template<typename T, class Container=vector<T>>
class Cycler{
public:
    Cycler(Container & container):container{container}{
        current = begin(container);
    }
    T & next(){
        T & tmp = *current;
        if (++current==end(container)){
            reset();
        }
        return tmp;
    }
    void reset(){
        current = begin(container);
    }
    typename Container::size_type size() const{
        return container.size();
    }
private:
    Container & container;
    typename Container::iterator current;
};

int main(){
    vector<int> v{1,4,9};
    Cycler<int> c{v};
    cout << c.next()<<endl;
    cout << c.next()<<endl;
    cout << c.next()<<endl;
    cout << c.next()<<endl;
    cout << c.size()<<endl;
    list<string> vals2 {"hi", "hello", "foo"};
    Cycler<string, list<string>> c2{vals2};
    for (int i{}; i < 10; ++i)
    {
        cout << c2.next() << endl;
    }
    cout << c2.size()<<endl;
    return 0;
}