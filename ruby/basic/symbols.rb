
puts :syl.object_id
puts :syl.object_id

puts "str".object_id
puts "str".object_id

puts :"syl".object_id()

str1 = "str"
str2 = "str"

if str1 === str2 then
  puts "str1 === str2"
end

if str1 == str2 then
  puts "str1 == str2"
end

if str1.eql?(str2)
  puts "str1.eql?(str2)"
else 
  puts "! str1.eql?(str2)"
end

if str1.equal?(str2)
  puts "str1.equal?(str2)"
else 
  puts "! str1.equal?(str2)"
end

syl1 = :syl
syl2 = :syl

if syl1.equal?(syl2) then
  puts "syl1.equal?(syl2)"
else
  puts "! syl1.equal?(syl2)"
end

syl3 = @test
syl4 = :@test

if syl3.equal?(syl4) then
  puts "@test equal :@test"
end

# name equal then symbol equal