/*
 * program6.cc
 */
#include <iostream>
#include <string>
using namespace std;


class Celestial_Body{
public:
    Celestial_Body(string name, double size)
    :name{name}, size{size}{}
    string get_name() const {
        return name;
    }
    double get_size() const{
        return size;
    }
    Celestial_Body(const & Celestial_Body) = delete;
    Celestial_Body & operator=(const & Celestial_Body) = delete;
    virtual ~Celestial_Body() = default;
private:
    const string name;
    const double size;
};

class Star:public Celestial_Body{
public:
    Star(string name, double size, string galaxy)
    :Celestial_Body{name, size}, galaxy{galaxy}{}
    string get_galaxy() const{
        return galaxy;
    }
private:
    const string galaxy;
};

class Planet:public Celestial_Body{
public:
    Planet(string name, double size, double orbit_time, Celestial_Body * parent, bool populated = false)
    :Celestial_Body{name, size}, orbit_time{orbit_time}, parent{parent}, populated{populated}{}

    const Celestial_Body * get_celestial_body() const{
        return parent;
    }
    double get_orbit_time() const{
        return orbit_time;
    }
    bool is_populated() const{
        return populated;
    }
    void populate(bool p){
        populated = p;
    }
private:
    const Celestial_Body *parent;
    const double orbit_time;
    bool populated;
};

class Moon:public Planet{
public:
    Moon(string name, double size, double orbit_time, Celestial_Body * parent, bool populated = false)
    :Planet{name, size, orbit_time, parent, populated}{}
};
// Define Celestial_Body classes here

void print(const Celestial_Body& cb)
{
    
    
    cout << cb.get_name() <<": ";
    
    if (auto c = dynamic_cast<Star const*>(&cb)){
        cout << "star, ";
    }
    else if (auto c = dynamic_cast<Planet const*>(&cb)){
        cout << "planet, ";
    }
    else if (auto c = dynamic_cast<Moon const*>(&cb)){
        cout << "moon, ";
    }
    cout << "radius "<< cb.get_size() << " km, ";
    cout <<"belongs to ";

    if (auto c = dynamic_cast<Star const*>(&cb)){
        cout << "galaxy "<< c->get_galaxy()<<endl;
    }
    else if (auto c = dynamic_cast<Planet const*>(&cb)){
        cout << "star "<< c->get_celestial_body()->get_name();
        cout << ", orbit time "<< c->get_orbit_time() << " days, ";
        if (!c->is_populated()){cout<<"not ";}
        cout << "populated"<<endl;
    }
    else if (auto c = dynamic_cast<Moon const*>(&cb)){
        cout << "planet "<< c->get_celestial_body()->get_name();
        cout << ", orbit time "<< c->get_orbit_time() << " days, ";
        if (!c->is_populated()){cout<<"not ";}
        cout << "populated"<<endl;
    }


   // Depending on kind of celestial body, print its correspending data. Output
   // shall be as follows:
   // Helios: star, radius 696342.0 km, belongs to galaxy Milky Way
   // Earth: planet, radius 6371.0 km, belongs to star Helios, orbit time 365.2 days, populated
   // Moon: moon, radius 1737.1 km, belongs to planet Earth, orbit time 27.3 days, not populated
}

int main()
{
    Star s{"Helios", 696342.0, "Milky Way"};
    Planet p{"Earth", 6371.0, 365.2, &s, true};
    Moon m{"Moon", 1737.1, 27.3, &p};

    print(s);
    print(p);
    print(m);
   // Declare statically one object of each of type Star, Planet, and Moon. Use
   // the following data to initialize the objects (use defaults when suitable):
   // Star: name Helios, radius 696342.0, belongs to galaxy Milky Way
   // Planet: name Earth, radius 6371.0, belongs to star Helios, orbit time 365.2 days, populated
   // Moon: name Moon, radius 1737.1, belongs to planet Earth, orbit time 27.3 days, not populated

   // Call print() for each object above.

   return 0;
}  