#include <iostream>

using namespace std;

class Parent
{
    /**
     * struct的默认访问权限为public
     * class的默认访问权限为private
     *
     */
public:
    Parent() {
        
    }
    ~Parent() {
        
    }
public:
    void print() {
        cout << "Parent print" << endl;
        
    }
    virtual void virtual_print() {
        cout << "Parent virtual print" << endl;
        
    }

    int getI() {

        return i;
        
    }
        
    
private:
    static const int i = 100;
        
    void private_print() {

        cout << "Parent private print" << endl;
        
    }
};

class Sub : public Parent  // ok 公有继承，基类的public在派生类中public，基类的protected在派生类中protected
// class Sub ：Parent // ok
// class Sub : protected Parent // error inaccessiable at new object 受保护继承，基类的public和protected在派生类中均为protected
// class Sub : private Parent  // error inaccessiable at new object 私有继承，基类的所有成员在派生类中均为private成员
// class Sub inherits Parent  // error expected initializer before 'Parent'
{
public:
    void print() {

        cout << "Sub print" << endl;
        
    }
    // 继承父类，virtual关键字可有可无，派生类无法改变基类的虚函数
    void virtual_print() {
        cout << "Sub virtual print" << endl;
        
    }
    
};

class ParentFirend
{
public:
    friend class Parent;


private:
    void print(int i) {

        cout << "the parent private i: " << i << endl;
        
    }

public:
    void iprint(int i) {

        print(i);
        
    }
    
    
};

int main()
    {

        /**
         * 要触发动态绑定，一是指定虚函数的成员函数，成员函数默认为非虚函数
         * 二是通过基类类型的引用或指针进行函数调用
         */
        Parent *p = new Sub();
        p->print();
        // 使用虚函数实现方法的重载 
        p->virtual_print();

        //p->private_print();
        
        
        //Parent par = new Parent(); // no-scalar，从这里知道，new的过程就是在内存中新建一块地址，用于存放该对象
        Parent par;  // 亦可使用class Parent par; 该方式从c中继承。还有类对象不需要初始化，不像java那样严格
        
        par.print();
        par.virtual_print();

        ParentFirend pf;

        // 私有的方法，外界无法访问
        //pf.print();

        pf.iprint(par.getI());
        
        
    }
