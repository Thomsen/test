# ruby from other languages


# everything has a value
a = 5
b = 8
c = if a < b
  true
else
  false
end

puts c

# symbols are not lightweight strings
puts "symbols object " + (:symbols.object_id() == :symbols.object_id()).to_s() # the object_id is Fixnum
puts "strings object " + ("strings".object_id() == "strings".object_id()).to_s()

# everything is an object
MyClass = Class.new() do
  # read and write instance variables @instance_var
  attr_accessor :instance_var
  
  puts @instance_var
  puts :@instance_var
  
  instance_var = 8
  @instance_var = 9
  
  puts instance_var
  puts @instance_var
  
end

puts MyClass.class()


MyClass2 = Fixnum.class() do
  
end

puts MyClass2.class()

# naming constants

Constant = 10

def Constant 
  11
end

puts Constant
puts Constant()

# fake keyword parameters
def some_keyword_parameters(params) 
  params
end

puts some_keyword_parameters(:params => 21, :params => 22) # result: params22
puts some_keyword_parameters(:params_one => 25, :params_two => 27) # result params_one25params_two27


# universal truth
if 0
  puts "0 is ture"
else
  puts "0 is false"
end


if 1 ; puts "1 is true" else puts "1 is false"
end

# access modifiers apply until the end of scope
class MyClass3
  def public_method0 ; true
  end
  private # no access
  def private_method1 ; true ; end
  def public_method2 ; false ; end
    
  protected # no access
  def protected_method3 ; true 
  end
  
  public 
  def
    public_method4 ; true
  end
end

myClass3 = MyClass3.new
myClass3.public_method0()
puts myClass3.public_method4()

# method access
class MyClass4
  def func
    33
  end
  
  def == (other)
    func == other.func
  end
end

#MyClass4.new(myClass41) # uninitialized constant, because def MyClass4, it class MyClass4
myClass42 = MyClass4.new
myClass43 = MyClass4.new

puts (myClass42 == myClass43).to_s() + "23"
  
class MyClass4
  protected :func
end

puts "protected " + (myClass42 == myClass43).to_s()

class MyClass4
  private :func
end

#puts "privated " + (myClass42 == myClass43).to_s()  #NoMethodError

# classes are open
class Fixnum
  def hours
    self * 3600
  end
  
  alias hour hours
end

puts 12.hour
puts Time.mktime(2012, 03, 20) + 13.hours  # hours new method

# singleton method
class Car
  def inspect 
    "cheap car"
  end
end

bmw = Car.new
puts bmw.inspect


def bmw.inspect()
  "bmw is not cheap car"
end

puts bmw.inspect()

cheap_car = Car.new
puts cheap_car.inspect()

# missing method
def arg_method(id, *arguments)
  puts "Method #{id} was called, these arguments #{arguments.join(", ")}"
end

arg_method :a, :b, 10

#__ :a, :b, 10

# blocks are objects, they just don't know it yet
def block(&the_block) 
  the_block
end

adder = block {|a, b| a + b}

puts adder.class()

method(:puts).call "puts is an object"

# block proc lambda
arr = [1,2,3,4,5]
arr.each{|item| puts item}

a_proc = Proc.new {|a, *b| b.each {|i| i*a}}
puts a_proc.call(9, 1, 2, 3)

a_proc = Proc.new {|a, *b| b.collect {|i| i*a}}
puts a_proc.call(9, 1, 2, 3) 

a_proc = Proc.new {|a, *b| b.map {|i| i*a}}
puts a_proc.call(9, 1, 2, 3)

a_proc = Proc.new {|a, *b| b.inject {|sum, i| sum + i*a}}
#a_proc = Proc.new {|a, *b| b.inject {|i| i*a}}  # multiple values
puts a_proc.call(9, 1, 2, 3)

strings = %w[a,b,c,d,e] # == strings ["a","b","c","d","e"]
strings.each {|i| puts i}

a_lambda = lambda {|i| puts i}
#a_lambda.call("thomsen", "wang") # multiple values error
a_lambda.call("thomsen")  

  
# operators are syntactic sugar
class Fixnum
  def +(other)
    self - other
  end
end

puts 6+4 # result is 2

