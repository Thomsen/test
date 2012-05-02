if 3>2
  puts 3
else 
  puts 2
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

p "for in loop"
for i in [1, 2, 3] do
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
while d < 3
  closure d, procsss
  d += 1
end
procsss.each() { |p| p.call }
e = 0
process = []
while e < 3
  lambda { |f| process[f] = proc { puts "lambda: " + f.to_s()}}.call(e)
  e += 1;
end

process.each { |p| p.call }
  
 
  
