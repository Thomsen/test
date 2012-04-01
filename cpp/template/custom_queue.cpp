#include <iostream>

using namespace std;


template <class Type> class my_queue;

template <class T>
std::ostream& operator<<(std::ostream&, const my_queue<T>&);

template <class Type>
class my_item {

    // 添加友元关系，不然会无法访问本地的私有方法
    friend class my_queue<Type>;
    friend std::ostream&
    operator<< <Type> (std::ostream&, const my_queue<Type>&);
        
    my_item(const Type &t): item(t), next(0) {
        
    }
    Type item;
    my_item *next;
    
};


// 类模板也是模板
template <class Type> class my_queue
{
    friend std::ostream&
    operator<< <Type> (std::ostream&, const my_queue<Type>&);
    
public:
    my_queue(): head(0), tail(0) {
        
    }
    my_queue(const my_queue &q): head(0), tail(0) {

        copy_elems(q);
        
    }
    my_queue& operator = (const my_queue&);
    ~my_queue() {
        destroy();
        
    }

    // 返回队列的头引用
    Type &front() {

        return head->item;
        
    }
    const Type &front() const {
        return head->item;
        
    }
    
    
    // 对尾增加一项
    void push (const Type &);
    

    // 对头删除一项
    void pop();
    
    // 队列中是否有元素
    bool empty() const {
        return head == 0;
        
    }

private:
    my_item<Type> *head;
    my_item<Type> *tail;

    void destroy();
    void copy_elems(const my_queue&);
    
    
};

template <class Type>
void my_queue<Type>::destroy()
    {
        while(!empty())
            pop();
        
            
    }

template <class Type>
void my_queue<Type>::copy_elems(const my_queue &orig)
    {
        for (my_item<Type> *mt = orig.head; mt; mt = mt->next) {
            push (mt->item);
            
        }
    }

template <class Type>
void my_queue<Type>::pop()
    {
        my_item<Type>* mt = head;
        head = head -> next;
        delete mt;
        
    }

template <class Type>
void my_queue<Type>::push(const Type &val)
    {
        my_item<Type>* mt = new my_item<Type>(val);

        if (empty()) {
            head = tail = mt;
            
        } else {

            tail -> next = mt;
            tail = mt;
            
        }
    }


int main()
    {
        my_queue<int> mqi;
        short s = 42;
        int i = 42;
        
        mqi.push(s);
        mqi.push(i);

        //
        
    }
    
