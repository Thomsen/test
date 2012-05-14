## bubble
puts 'bubble'
bubble = [3, 2, 4, 32, 24, 21]

1.upto(bubble.length-1) do |i| # why 1 not 0
    (bubble.length-i).times do |j| # because length-i
        if bubble[j] > bubble[j+1]
            bubble[j], bubble[j+1] = bubble[j+1], bubble[j]
        end
    end
end

bubble.each {
    |i|  puts i
}

for i in 0..(bubble.length-2) do # for  nil length+1
    for j in 0..(bubble.length-2) do
        if bubble[j] < bubble[j+1]
           # bubble[j], bubble[j+1] = bubble[j+1], bubble[j]
            temp = bubble[j]
            bubble[j] = bubble[j+1]
            bubble[j+1] = temp
        end
    end
end

(0..bubble.length-2).each do |i|
    (0..bubble.length-2-i).each do |j|
        if bubble[j] > bubble[j+1]  # avoid nil, bubble[i].to_i
            bubble[j], bubble[j+1] = bubble[j+1], bubble[j]
        end
    end
end

# regular
(bubble.length..1).each do |i|
    (0..i-1).each do |j|
        if bubble[j].to_i > bubble[j+1].to_i
            bubble[j], bubble[j+1] = bubble[j+1], bubble[j]
        end
    end
end

for i in 0..bubble.length do # should bubble.length-1 
    puts bubble[i]
end

## select
puts 'select'
select = [3,23,5,2]
result = []

select.size.times do |i|
    min = select.min
    result << min
    select.delete_at(select.index(min))
end

result.each do |i|
    puts i
end

puts "result size: " + result.size.to_s
puts "result length: " + result.length.to_s

(0..result.size-2).each do |i|
    (i+1..result.size-1).each do |j|
        if result[i].to_i < result[j].to_i
            result[i], result[j] = result[j], result[i]
        end
    end
end

result.each {
    |i| puts i
}

## insertion
puts "insertion"
insertion = [3, 23, 22, 34]

# [7, 8, 6] [5, 8, 8] [5, 7, 6] [5, 7, 8]
# [7, 8, 9, 6] [7, 8, 9, 9] [7, 8, 8, 9] [7, 7, 8, 9] [6, 7, 8, 9]

insertion.each_with_index do |ins, i|
    j = i-1    # 22 2
    while j >= 0  # 22 1
        break if insertion[j] <= ins  # exec insertion[j+1] = ins, outter while
        insertion[j+1] = insertion[j] # [2] = 23
        j -= 1 # while j=0 break
    end
    insertion[j+1] = ins # [1] = 22
end

insertion.each do |i|
    puts i
end

## hanoi
puts "hanoi"
def move(from, to)
    puts "#{from} move to #{to}"
end

def hanoi(n, first, second, third)
    if n == 1
        move(first, third)
    else
        hanoi(n-1, first, third, second)
        move(first, third)
        hanoi(n-1, second, first, third)
    end
end
puts 3.to_s + " hanoi"
hanoi(4, "A", "B", "C") # A move to C of n

def nextloc(current, n)
    case current
    when 'A':
        return (n%2==0) ? 'C' : 'B'
    when 'B':
        return (n%2==0) ? 'A' : 'C'
    when 'C':
        return (n%2==0) ? 'B' : 'A'
    else
        return 0
    end
end

puts nextloc('A', 1)

def hanoi(n)
    # n is odd, ACB (A-C,A-B C-B A-C B-A,B-C A-C)
    # ACB (A-C,A-B, C-B A-C B-A,B-C A-C,A-B C-B,C-A B-A...) 


    # n is even, ABC (A-B,A-C B-C A-B C-A,C-B A-B,A-C B-C,B-A C-A B-C A-B A-C,A-C B-C)

end

## shell

## merge

## heap

## quick

## counting

## radix

## bucket

## cocktail

## parity

## pigeonhole
