#include <iostream>

using namespace std;

class all
{
public:
  class in_a
  {
  public: int a;
  public: int b;
  public: void foo(all &p)
    {
      cout << p.i1 << endl;
      cout << p.i2 << endl;
    }
  };

private:
  class in_b
  {
  public: int a;
  private: int b;
  public: void foo(all &p)
    {
      cout << p.i1 << endl;
      cout << p.i2 << endl;
    }
  };

public:
  in_a ia1;
  in_b ib1;
  int i1;

private:
  in_a ia2;
  in_b ib2;
  int i2;

public:
  void f(in_a &p)
  {
    cout << p.a << endl;
    cout << p.b << endl;  
  }

  void f(in_b &p)
  {
    cout << p.a << endl;
    // cout << p.b << endl;  // in_b b is private
  }

};

int main(int argc, char *argv[] )
{
  all all;
  all::in_a aina;
  //all::in_b ainb;  // class in_b is private

  return 0;
}
