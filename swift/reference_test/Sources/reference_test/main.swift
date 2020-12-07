
// value reference
struct S {
    var num = -1
}

var a = S()

var b = a

a.num = 10

print(b.num)

// memory reference
class C {
    var num  = -1
}

var c = C()

var d = c

c.num = 10

print(c.num)