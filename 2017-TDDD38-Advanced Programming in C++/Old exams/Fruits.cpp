#include <iostream>
#include <vector>
using namespace std;

class Fruit{
public:
    Fruit(string const &name, int const &mass, double const &concentration, bool const &dehiscent)
    : n{name}, m{mass}, c{concentration}, d{dehiscent}
    {
    }
    string name() const{
        return n;
    }
    double vitamin_c_content() const{
        return m*c/100;
    }
    bool dehiscant() const{
        return d;
    }
    int mass() const{
        return m;
    }
    virtual ~Fruit() = default;
private:
    string n;
    int m;
    double c;
    bool d;
};

class Berry : public Fruit{
public:
    Berry(string const &name, int const &mass, double const &concentration)
    : Fruit{name, mass, concentration, false}
    {
    }
};
class Lemon : public Berry{
public:
    Lemon(int const &mass)
    : Berry{"Lemon", mass, 53}
    {
    }
};

class Apple : public Fruit{
public:
    Apple(int const &mass)
    : Fruit{"Apple", mass, 4.6, false}
    {
    }
};

class Pea : public Fruit{
public:
    Pea(int const &mass)
    : Fruit{"Pea", mass, 40, true}
    {
    }
};

void print(Fruit const & f)
{
    // Print Berry if f is a berry or Fruit otherwise
    if ( dynamic_cast<Berry const *>(&f) != nullptr)
    cout << "Berry ";
else
    cout << "Fruit ";

    cout << f.name() << " with mass " << f.mass() << "g [";
    if ( !f.dehiscant() )
    {
        cout << "in";
    }
    cout << "dehiscant]\n";

};

int main()
{ 
    // create a vector containing the following fruits:
    //  Apple, mass: 150g
    //  Lemon, mass: 75g
    //  Pea, mass: 25g
    //
    
    // Iterate through the vector and pass each fruit to print above, should give the following output
    // Fruit, Apple with mass 150g [indehiscant]
    // Berry, Lemon with mass 75g [indehiscant]
    // Fruit, Pea with mass 25g [dehiscant]

   int total_content {};

   vector<Fruit> v{Apple(150), Lemon(75), Pea(25)};
   for (Fruit f : v){
       print(f);
       total_content += f.vitamin_c_content();
   }
    // Calculate the total vitamin C content of all fruit

    cout << "Total vitamin C content: " << total_content << endl;
}