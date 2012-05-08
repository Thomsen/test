# bubble
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

for i in 0..bubble.length do 
    puts bubble[i]
end
