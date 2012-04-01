#include <iostream>

using namespace std;


template <typename T>
int compare(const T &v1, const T &v2)
    {
        if (v1 < v2) return -1;
        if (v1 > v2) return 1;
        return 0;
        
    }

// 内联函数，inline的位置关键
template <typename T>
inline T mmin(const T& a, const T& b)
    {
        return a > b ? b : a;

                
    };


int main()
    {
        cout << "compare(1, 0) is " << compare(1, 0) << endl;
        cout << "compare('hello', 'world') is " << compare("hello", "world") << endl;

        // min function is ambiguous， 库中存在min函数，导致冲突
        cout << "mmin(0, 1) is " << mmin(0, 1) << endl;

        //cout << "mmin('hello', 'world')" << mmin("hello", "world") << endl;
        
        
        
        return 0;
        
        
    }
