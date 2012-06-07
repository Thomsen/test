puts /regex/.class

puts /do(es)\?/

puts /th./

puts /Mrs?\.?/

if /th./.match('thom')
    puts "/th./ is thom ok"
else 
    puts ". is regex single char"
end

if /th*/.match('thom')
    puts "/th*/ is thom ok"
end

puts "my nick name thom"  =~ /thom/

puts /thom/.match("my nick name thom")

puts /\d/.match("1a2h3")

puts /\d/.match("a2h3")

#puts /\\d/.match("1a2h3")

#puts /\\d/.match("4b3cd")

puts /[\d]*/.match("1a2h3")

#puts /[\\d]/.match("1a2h3")
