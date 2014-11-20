
def func1

  i = 0
  while i<=2
    puts "func1 at: #{Time.now}"
    sleep(2)
    i += 1
  end

end

def func2
  j = 0
  while j <= 2
    puts "func2 at: #{Time.now}"
    sleep(1)
    j += 1
  end
end


puts "Start at: #{Time.now}"

ta = Thread.new{func1}
tb = Thread.new{func2}

ta.join
tb.join

puts "End at: #{Time.now}"
