/*
 * program9.cc
 */
#include <string>
#include <iostream>
#include "demangle.h"
#include <typeinfo>

using namespace std;

template<typename T>
class Object_Tracer{
    public:
    Object_Tracer();
    ~Object_Tracer();
};
template<typename T>
Object_Tracer<T>::Object_Tracer(){
    cout << "Object created: "<< typeid(T).name() <<" "<< this<< endl;
}
template<typename T>
Object_Tracer<T>::~Object_Tracer(){
    cout << "Object destroyed: "<<typeid(T).name() <<" "<< this<< endl;;
}

template<typename T>
class Wrapper:public Object_Tracer<T> 
{
public:
   Wrapper(T value = T{}) 
   :Object_Tracer<T>{}, value_{ value } {}
private:
   T value_;
};


int main()
{
   Wrapper<int> wi{ 1 };                  // to be Wrapper<int>
   Wrapper<int>* wip = new Wrapper<int>{ 2 };  // to be Wrapper<int>*
   Wrapper<double> wd{ 2.3 };
   delete wip;
   Wrapper<char> wch;

   return 0;
}