if 3>2 then
  puts 3
end

if 3>2
  puts 3
else 
  puts 2
end

if 3>4
  puts 3
elsif 3>5
  puts 3
else
  puts 1
end
    

unless 3>2
  puts 3
else
  puts 2
end

puts "ternary operator"

if 3.nil?
  puts 3
else 
  puts 2
end

puts (3 ? 3.to_s : 2.to_s)

ca = 0
case ca
when 1
  puts "ok one"
when 2
  puts "ok two"
when 0
  puts "ok zero"
else 
  puts "ok"
end

p "for in loop"
for i in [1, 2, 3] do
  if i < 2 then
    next
    #redo from now
    #retry from start
  end
  p i
end
p "loop out"
p i

p "each loop"
#j = 4
[1, 2, 3].each() do |j|
  p j
end
p "loop out"
#p j  # undefined local variable or method

g = 0
begin
puts "g" + g.to_s()
g += 1
end while g < 3 

h = 0
(puts h ; h += 1) while h < 3

k = 4
until k < 3 do
  puts "k" + k.to_s
  k -= 1
end

puts "diff in Proc"

procs = []

for a in[1, 2, 3] do
  procs << Proc.new { puts "for: " + "#{a}"}
end

#a = 4
#procs.each() { |p| p.call }

[1, 2, 3].each() do |b|
procs << Proc.new { puts "each: " + "#{b}"}
end

procs.each() { |p| p.call }

# for at Proc closure
c = 0;
procss = []
while c < 3
procss[c] = proc {|cp| puts "while: " + cp.to_s()}
c += 1
end
  
#procs.each() { |p| p.call }
for p in 0..procss.length do
  #procss[p].call(p)  # undefined method `call' for nil:NilClass
end

puts "abstract to solve"
procsss = []
def closure c, procss
procss[c] = proc {puts "abstract: " + c.to_s()}
end
d = 0
while d < 3 do
  closure d, procsss
  d += 1
end
procsss.each() { |p| p.call }
e = 0
process = []
while e < 3
  
  lambda { |f| process[f] = proc { puts "lambda: " + f.to_s()}}.call(e)
  e += 1;
  
  break if e === 2
  
end

process.each { |p| p.call }
  
3.times() { puts "times" }
1.upto(9) { |m| print m if m < 8} 
9.downto(0) { |m| print m if m > 0}
print "\n"
0.step(13,3) { |m| print m }
  
#yeild placeholder
 
puts ''
  
[1,2,3].each do |i|
    while i<3
        break if i>1
        puts "while inner " + i.to_s
        i += 1
    end
    puts "each inner " + i.to_s
end

5.times { |x| puts "times " + x.to_s }
