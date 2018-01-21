#include <stdexcept>
#include <iostream>
#include <limits>
using namespace std;

template<typename T, T min=numeric_limits<T>::min(), T max=numeric_limits<T>::max()>
class Modular{
public:
    Modular(T val){
        if (val>max || val<min){
            value = min;
        }
        else value =val;
    }
    Modular & operator=(T const & val){
        if (val>max || val<min){
            throw std::domain_error("Value out of range");
        }
        value = val;
        return *this;
    }
    operator T() const{
        return value;
    }

    template<T o_min, T o_max>
    Modular<T, min, max> operator+(Modular<T,o_min, o_max> const & rhs) const{
        Modular<T,min,max> m{adjust(value+rhs)};
        return m;
    }

    Modular<T, min, max> &  operator++(){
        if ( value == max )
        value = min;
    else
        ++value;
    return *this;
    }

    Modular<T,min,max> operator++(int){
        auto tmp = *this;
        ++*this;
        return tmp;
    }
    
private:
    T adjust(T n) const
    {
        if ( n < max && n > min )
        {
            return n;
        }
        else if (n > max )
        {
            return min + n - max - 1;
        }

        return max + n - min + 1;
    }
    T value{};
};

int main()
{
    Modular<int, 2, 10> m{3};
    cout << m << endl;
    try
    {
        m = 1;
    }
    catch ( std::domain_error const & e)
    {
        cout << e.what() << endl;
    }
    Modular<int, 2,10> m2 {5};
    m = m + m2;
    cout << m << endl;
    m = m + Modular<int,-11,-2>{-10};
    cout << "Should be 7: " << m << endl;
    cout << "Should print 7 9: " << m++ << " ";
    cout << ++m;
    Modular<char,'a','c'> mc{'b'};
    ++mc;
    cout << "\nShould print a: " << ++mc << endl;
}