#include <iostream>
#include <string>
#include <sstream>
#include <iomanip>

using namespace std;

struct Hexadecimal{
    template <typename T>
    static string manipulate(T val){
        ostringstream os;
        os <<"0x"<< hex << val;
        return os.str();
    }
};

struct Quoted{
    template <typename T>
    static string manipulate(T val){
        ostringstream os;
        os << "\""<<val<<"\"";
        return os.str();
    }
};

template<typename T, class Policy>
class Wrapper
{
public:
	Wrapper(const T value): value_{value}{}
	void set(const T i){
        value_ = i;
    };
	T get() const{
        return value_;
    };
	std::string str() const;
private:
	T value_;
};
template<typename T, class Policy>
string Wrapper<T, Policy>::str() const{
    return Policy::manipulate(value_);
}

int main(){
    Wrapper<int, Hexadecimal> hexint(4711);
    cout << hexint.str()<<endl;
    Wrapper<int, Quoted> citint(4711);
    cout << citint.str()<<endl;
    Wrapper<string, Quoted> cistring("foobar");
    cout <<cistring.str()<<endl;
    return 0;
}