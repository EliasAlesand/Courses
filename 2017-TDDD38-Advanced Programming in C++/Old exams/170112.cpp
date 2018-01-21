#include <vector>
#include <iostream>
#include <sstream>
using namespace std;

class Game{
public:
    
    Game(string n,int min,int max):game_name(n),players{min,max}{
    }
    string name() const{
        return game_name;
    }
    virtual bool choose() const{
        cout << "How many players are you?"<< endl;
        int p;
        cin>>p;
        return p >= players.first && p <= players.second;
    }
    virtual void print(ostream &o) const{
        o << name() << "is a "<<players.first<<"-"<<players.second<<" player game"<<endl;
    } 
    virtual ~Game()=default;
    Game (Game const &) = delete;
    Game &operator=(Game const &) = delete;
private:
    string game_name;
    pair<int,int> players;
};

class Board_Game : public Game{
public:
    Board_Game(string n,int min, int max, int size_x,int size_y):
    Game(n,min,max), board_size{size_x,size_y}
    {
    }
    bool choose() const override{
        if (!Game::choose()){
            return false;
        }
        cout <<"The board size is" << size() << "do you have room for it? [Y/n]" <<endl;
        char a{};
        cin >> a;
        return a != 'n' && a != 'N';
    }
    void print(ostream &o) const override{
        Game::print(o);
        o<<"and has a board size of "<<size()<<" cm";
    }
    string size() const {
        ostringstream oss;
        oss << board_size.first<<"x"<<board_size.second;
        return oss.str();
    }

private:
    pair<int,int> board_size;

};
class Cooperative : public Board_Game{
public:
    Cooperative(string n, int min, int max, int size_x, int size_y, bool traitor=false)//TESTA TA BORT = FALSE
    : Board_Game(n,min,max,size_x,size_y), has_traitor{traitor}
    {
    }
    bool choose() const override{
        if (!Board_Game::choose()){
            return false;
        }
        cout << "Do you want a traitor? [Y/n]"<<endl;
        char a;//LÄGG TILL {} OM INTE FUNKAR
        cin >> a;
        return a != 'N' && a != 'n';
    }
    void print(ostream &o) const override{
        Board_Game::print(o);
        if (has_traitor)
            o << "and has the possibility of a traitor"<<endl;
    }
private:
    bool has_traitor;
};

class Card_Game : public Game{
public:
    Card_Game(string n, int min, int max, bool special_cards)
    : Game(n,min,max), has_special_cards{special_cards}
    {
    }
    bool choose() const override{
        if (!Game::choose()){
            return false;
        }
        cout << "Do you want to use special cards? [Y/n]" << endl;
        char a;
        cin >> a;
        return a != 'n' && a != 'N';
    }
    void print(ostream &o) const override{
        Game::print(o);
        if (has_special_cards){
            cout << "and has a special deck of cards";
        }
    }
private:
    bool has_special_cards;
};

void select_game(vector<Game*> const & games)
{
    for ( Game const * g: games )
    {
        if ( g->choose() )
        {
            g->print(cout);
            cout << endl;
        }
    }
}


int main()
{
    /* Create the following objects dynamically and store them in a vector
     *
     * Board game "Monopoly" has 2-4 players with a board of size 51x51cm
     * Cooperative board game "Pandemic" has 2-5 players with a board size 41x57cm
     * Card game "Solitaire" has 1 player and is played with a normal deck
     * Card game "Bang!" has 3-7 players and is played with a special deck
     * Cooperative board game "Battle Star Galactica" has 4-7 players with a board size of 100x120cm and can have a traitor
     */
    
     // call select_game, below are two examples:
     //
     /* 1:
      * How many players?: 2
      * Do you have space for 51x51cm [y/n]: y
      * -> Board game "Monopoly" has 2-4 players with a board of size 51x51cm
      * How many players?: 1
      * How many players?: 2
      * How many players?: 4
      * -> Card game "Bang!" has 3-7 players and is played with a special deck
      * How many players?: 5
      * Do you have space for 100x120cm? [y/n]: y
      * Do you want a traitor? [y/n]: y
      * -> Cooperative board game "Battle Star Galactica" has 4-7 players with a board size of 100x120cm and can have a traitor

      * 2:
      * How many players?: 2
      * Do you have space for 51x51cm? [y/n]: n
      * How many players?: 3
      * Do you have space for 41x57cm? [y/n]: y
      * Do you want a traitor? [y/n]:
      * -> Cooperative board game "Pandemic" has 2-5 players with a board size 41x57cm
      * How many players?: 5
      * How many players?: 5
      * -> Card game "Bang!" has 3-7 players and is played with a special deck
      * How many players?: 2
      */

}