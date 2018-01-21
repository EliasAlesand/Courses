#include <iostream>
using namespace std;

class Student{
public:
    Student(string n, string sn, string pn, char g, int p)
    : name{n}, surname{sn}, pnr{pn}, grade{g}, points{p}
    {
    }
    string get_name() const{
        return name;
    }
    string get_surname() const{
        return surname;
    }
    string get_pnr() const{
        return pnr;
    }
    string get_grade() const{
        return grade;
    }
    string get_points() const{
        return points;
    }
private:
    string name, surname, pnr;
    char grade;
    int points;
}

int main(){
    return 0;
}