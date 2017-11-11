#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Score_Variant{
    public:
    virtual string name()=0;
    virtual int score(vector<int> die)=0;
};
class Counted_Dice : public Score_Variant{
    int value;
    public:
    Counted_Dice(int v):value(v){};
    int get_number(){
        return value;
    }
    int score(vector<int> die) override{
        int score = 0;
        for(int i:die){
            if (i==value){
                score+=value;
            }
        }
        return score;
    }
};


class Twos : public Counted_Dice{
    public:
    Twos():Counted_Dice(2){};
    string name() override{
        return "Twos";
    }
};
class Ones : public Counted_Dice{
    public:
    Ones():Counted_Dice(1){};
    string name() override{
        return "ones";
    }
};


class Pair : public Score_Variant{
    public:
    string name() override{
        return "Pair";
    }
    int score(vector<int> die) override{
        int max = 0;
        for(int i:die){
            
            int count = 0;
            for(int j:die){
                if (j==i){
                    count++;
                }
            }
            if (count>1 && 2*i>max){
                max = 2*i;
            }
        }
        return max;
    }
};

int main()
{
    vector<int> die = vector<int>{3,2,2,2,3,5,5,1,1,6};
    vector<Score_Variant*> scores = vector<Score_Variant*>{new Ones(), new Twos(), new Pair()}; 
    for (Score_Variant* s: scores){
        int points = s->score(die);
        if (points > 0){
            cout << s->name() <<": "<<s->score(die);
            if (dynamic_cast<Counted_Dice*>(s)){
                Counted_Dice* p = dynamic_cast<Counted_Dice*>(s);
                cout <<" ("<<points/p->get_number()<<")";
            }
            cout << endl;
        }
    }
}
