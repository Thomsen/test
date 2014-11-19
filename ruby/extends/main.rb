$LOAD_PATH << '.'

require 'founder'
require 'acer'

founder = Founder.new

founder.say
founder.printPixels


acer = Acer.new(1366, 768)

acer.say
acer.printPixels


Work::Computer.printCount

founder.setWidth= 1366
fx = acer.getWidth()
puts "founder width is: #{fx}"

acer2 = Acer.allocate

Work::Computer.printCount
puts "acer2 width is: #{acer2.getWidth}"

acer.freeze
if (acer.frozen?)
  puts 'acer object is forzen object'
else
  puts 'acer object is noraml object'
end

acer.setWidth = 1564
x = acer.getWidth()
