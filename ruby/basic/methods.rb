def prt hel
  puts hel
end

prt 1
prt "hello"


def add(a, b)
  puts a+b
end

add(1, 3)

def add(a, b, *c)
  puts a
  puts b
  puts c
end

add(1, 2, 3)
add(1, 2, 'e'=>3)
add(1, 2, [1,2,3])

def resc arg
  begin
    raise ArgumentError, "Bad data"
  rescue => err
    puts err
  ensure
    # clear
  end
end

resc 1
