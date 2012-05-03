class People
  attr_reader :name #attr :name, false
#  def name
#    @name
#  end
  def name= (name)
    @name = name
  end
  attr_writer :age 
#  def age= (age)
#    @age = age
#  end
  def age
    @age
  end
  attr_accessor :school #attr :school, true
  
  
  def toString
    "My name is #{name}" " and age is #{age}"", come from #{school}."
  end
  
end

p = People.new

def p.say
  puts "It's my private method."
end

p.say

p.name = "Thomsen"
p.age = 24

puts p.name
puts p.age

p.school = "hhuwtian"
puts p.school

puts p.toString()
puts p::toString()