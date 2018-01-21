#include <iostream>
#include <vector>
#include <iomanip>
using namespace std;

class Slab{
public:
    virtual ~Slab() = default;
    Slab(string color, double weight, string size)
    : color{color}, weight{weight}, size{size}{}
    string get_size(){
        return size;
    }
    double get_weight(){
        return weight;
    }
    string get_color(){
        return color;
    }
    virtual  Slab * clone() const = 0;
protected:
    Slab(Slab const &) = default;
private:
    const string color;
    const double weight;
    const string size;
};

class Concrete: public Slab{
public:
    Concrete(string color, double weight, string size, bool is_tumbled = false)
    : Slab{color,weight,size}, is_tumbled{is_tumbled}{}
    bool tumbled() const{
        return is_tumbled;
    }
     Concrete * clone() const override{
        return new Concrete(*this);
    }
private:
    const bool is_tumbled;
};

class Rock:public Slab{
public:
    Rock(double weight, string size, string type)
    :Slab{"Nature", weight, size}, type{type}{}
    string get_type() const {
        return type;
    }
    Rock * clone()const override{
        return new Rock(*this);
    }
private:
    const string type;
};

class Brick:public Slab{
public:
    Brick(string color, double weight, string size)
    : Slab(color, weight, size){}
    Brick * clone()const override{
        return new Brick(*this);
    }
};

void test(Slab const & s)
{
    auto c = s.clone();

    cout <<left<<setw(10);

    if (auto t = dynamic_cast<Concrete const*>(c)){
        cout << "Concrete";
    }
    else if (auto t = dynamic_cast<Rock const*>(c)){
        cout << "Rock";
    }
    else cout << "Brick";

    cout <<setw(10)<<c->get_color()<<setw(10)<<c->get_size()<<setw(10)<<c->get_weight()<<setw(10);

    if (auto t = dynamic_cast<Concrete const*>(c)){
        if (t->tumbled())
            cout << "tumbled";
        else cout <<"not tumbled";
    }
    else if (auto t = dynamic_cast<Rock const*>(c)){
        cout << t->get_type();
    }
    cout << endl;

    delete c;
    //cout <<setw(w)<< c->get_color() <<setw(w)<< c->get_()<<setw(w)<<"Size"<<setw(w)<<"Weight"<<setw(w)<<"type/tumbled"<<endl;
    // Create a copy of the object that s refers to
    //
    // Print the information about the copy. With objects constructed according to the
    // comments in main, the printout should be:
    // Concrete: Gray, 25x12x4, 2.8kg
    // Rock: Granite, Nature, 12x15x10, 10.5kg
    // Brick: Red, 25x10x10, 3.2kg
    // Concrete: Graphite, 20x20x6, 3.9kg, tumbled
    //
    // Destroy the copy
}

int main()
{

    vector<Slab*> v{new Concrete("Gray", 2.8, "25x12x4"), new Rock(10.5, "12x15x10", "Granite"), new Brick("Red", 3.2, "25x10x10"), new Concrete("Graphite", 3.9, "20x20x6", true)};
    
    
    int w = 10;
    cout <<left;
    cout <<setw(w)<< "Type" <<setw(w)<< "Color"<<setw(w)<<"Size"<<setw(w)<<"Weight"<<setw(w)<<"type/tumbled"<<endl;
    cout <<"----------------------------------------------------"<<endl;
    for (auto s : v){
        test(*s);
    }
    // Create the following objects dynamically and store in some kind of container:
    // Type      Color    Size      Weight   type/tumbled
    // --------------------------------------------------
    // Concrete  Gray     25x12x4   2.8
    // Rock               12x15x10  10.5     Granite
    // Brick     Red      25x10x10  3.2
    // Concrete  Graphite 20x20x6   3.9      true

    // pass each object to test.

   return 0;
}