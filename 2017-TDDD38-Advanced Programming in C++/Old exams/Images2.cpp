#include <sstream>
#include <iterator>
#include <string>
#include <iostream>
#include <vector>
#include <functional>
#include <memory>
#include <numeric>
#include <algorithm>

using namespace std;

// Given, should not be changed
struct Image_Data
{
    Image_Data(string const & line)
    {
        istringstream iss{line};
        iss >> type;
        for ( int d; iss >> d; )
        {
            args.push_back(d);
        }
    }
    
    string type;
    vector<int> args;
};

// Add your classes here
class Image{
    public:
    Image(int width, int height):width{width},height{height}{

    }
    virtual int size()=0;
    protected:
    int width, height;
};
class PNG: public Image{
    public:
    using Image::Image;
    static Image * parse(const Image_Data & data){
        if (data.type == "PNG"){
            return new PNG(data.args[0],data.args[1]);
        }
            else return nullptr;
    }
    int size(){
        return width*height*1.5;
    }
};
class BMP: public Image{
    public:
    using Image::Image;
    static Image * parse(Image_Data const & data){
        if (data.type == "BMP"){
            return new BMP(data.args[0],data.args[1]);
        }
        else return nullptr;
    }
    int size(){
        return width*height*3;
    }
};

class JPG: public Image{
    public:
    JPG(int width, int height, int quality)
    :Image{width,height}, quality{quality}{
    }
    static Image * parse(Image_Data const & data){
        if (data.type == "JPG"){
            return new JPG(data.args[0],data.args[1], data.args[2]);
        }
        else return nullptr;
    }
    int size(){
        return width*height*3*(quality/100.0);
    }
    private:
    int quality;
};


/*
 * Given code. No modifications allowed unless specified!
 */
Image* error_printer(Image_Data const & d)
{
    cout << "!!! " << d.type << " is an invalid file format !!!\n";
    return nullptr;
}
int main()
{
    
    vector<unique_ptr<Image>> images;
    
    vector<function<Image*(Image_Data const &)>> funs { PNG::parse,
                                                        JPG::parse,
                                                        BMP::parse,
                                                        error_printer};
    cout << "Enter one line for each image on the format \"type [type specific data]\". Exit with \"q\".\n\n";
    

    for ( string line; getline(cin, line); )
    {
        if ( line == "q" )
        {
            break;
        }
        Image_Data data{line};
        for ( auto && f: funs )
        {
            if (auto ptr = f(data))
            {
                images.emplace_back(ptr);
                break;
            }
        }
        
    }
    long sum{};
    for(auto && i : images){
        sum+=i->size();
    }
    cout << sum;
// Additions allowed below (calculate and print sum)

}