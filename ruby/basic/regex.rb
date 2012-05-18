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
