#include <iostream>
#include <vector>
#include <memory>
#include <algorithm>
using namespace std;

class Sauna{
public:
    virtual ~Sauna() = default;

    Sauna * get_clone() const{
        return clone();
    }
    Sauna(int default_temp = 22):default_temp{default_temp}
    {
        is_on = false;
        temp = 22;
    }
    int get_temp(){
        return temp;
    }
    void set_temp(int t){
        temp = t;
    }
    void turn_on(){
        if (!is_on){
            temp = default_temp;
            is_on = true;
        }
    }
    void turn_off(){
        if (is_on){
            temp = 22;
            is_on = false;
        }
    }

    friend ostream & operator<<(ostream & os, Sauna & obj){
        os << obj.str();
        if (obj.is_on){
            os << " [ON] ";
        }
        else os << " [OFF] ";
        os << obj.temp << "degrees";
        return os;
    }
    Sauna(Sauna const &) = default;
    virtual string str() const = 0;
    int temp;
private:
    virtual Sauna * clone() const = 0;
    const int default_temp;
    bool is_on;
    
};

class Steam_Heater : public Sauna{
public:
    Steam_Heater()
    :Sauna{38}{}
    string str() const override {
        return "Steam sauna";
    }
    Steam_Heater(Steam_Heater const &) = default;
private:
    Steam_Heater * clone() const override{
        return new Steam_Heater{*this};
    }
};

class Wood_Heater : public Sauna{
public:
    Wood_Heater():Sauna{}{
    }
    string str() const override{
        return "Wood-burning sauna";
    }
    void add_log(){
        temp += 5;
    }
    Wood_Heater(Wood_Heater const &) = default;
private:
    Wood_Heater * clone() const override{
        return new Wood_Heater(*this);
    }
};

class Electrical_Heater:public Sauna{
public:
    Electrical_Heater():Sauna{85}{
    }
    string str() const override{
        return "Electrical heater";
    }
    Electrical_Heater(Electrical_Heater const &) = default;
private:
    Electrical_Heater * clone() const override{
        return new Electrical_Heater{*this};
    }
};

void print(Sauna *p){
    cout << *p<<endl;
    p->turn_on();
    if (auto ws = dynamic_cast<Wood_Heater*>(p)){
        ws->add_log();
        ws->add_log();
    }
    cout << *p<<endl;
}

int main(){
    vector<shared_ptr<Sauna>> saunas{make_shared<Steam_Heater>(),make_shared<Wood_Heater>(),make_shared<Electrical_Heater>()};
    for_each(begin(saunas), end(saunas), [=](auto s){
        print(s->get_clone());

    });
    return 0;
}