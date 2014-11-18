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
puts 'regular'
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
    when 'A' then
        return (n%2==0) ? 'C' : 'B'
    when 'B' then
        return (n%2==0) ? 'A' : 'C'
    when 'C' then
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
puts 'shell'
shell = [32, 12, 23]
len = shell.size
while (len > 1)
    len = len / 2
    (len..shell.size-1).each do |i|
        j = i
        while (j>0)
            shell[j], shell[j-len] = shell[j-len], shell[j] if shell[j] <= shell[j-len]
        j = j - len
        end
    end
end

shell.each do |i|
    puts i.to_s
end

## merge
puts 'merge'

def merge(l, r)
    result = []
    while l.size > 0 and r.size > 0 do
        if l.first < r.first
            result << l.shift
        else
            result << r.shift
        end
    end
    if l.size > 0
        result += l
    end
    if r.size > 0
        result += r
    end
    return result
end

def merge_sort(arr)
    return arr if arr.size <= 1
    middle = arr.size / 2
    left = merge_sort(arr[0, middle])
    right = merge_sort(arr[middle, arr.size-middle])
    merge(left, right)
end

merge_value = [23, 234, 21]
new_value = merge_sort(merge_value)
(merge_value.length).times do |i|
    puts merge_value[i]
end
0.upto(new_value.length-1) do |i|
    puts new_value[i]
end


## heap
puts 'heap'
def heapify(arr, idx, size)
    left_idx = 2 * idx + 1
    right_idx = 2 * idx + 2
    bigger_idx = idx
    bigger_idx = left_idx if left_idx < size && arr[left_idx] > arr[idx]
    bigger_idx = right_idx if right_idx < size && arr[right_idx] > arr[bigger_idx]
    if bigger_idx != idx
        arr[idx], arr[bigger_idx] = arr[bigger_idx], arr[idx]
        heapify(arr, bigger_idx, size)
    end
end

def build_heap(arr)
    last_parent_idx = arr.length / 2 -1
    i = last_parent_idx
    while i >= 0
        heapify(arr, i, arr.size)
        i = i - 1
    end
end

def heap_sort(arr)
    return arr if arr.size <= 1
    size = arr.size
    build_heap(arr)
    while size > 0
        arr[0], arr[size-1] = arr[size-1], arr[0]
        size = size -1
        heapify(arr, 0, size)
    end
    return arr
end

heap = [ 2, 23, 12]
result = heap_sort(heap)
result.each do |i|
    puts i
end


## quick
puts 'quick'
def quick_sort(arr)
    (x = arr.pop) ? quick_sort(arr.select{|i| i<= x}) + [x] + quick_sort(arr.select{|i| i>x}) : []
end

quick = [33, 26, 23]
result = quick_sort(quick)
result.each do |i|
    puts i
end


## counting
puts 'counting'
def counting_sort(arr)
    min = arr.min
    max = arr.max
    counts = Array.new(max-min+1, 0)

    arr.each do |a|
        counts[a-min] += 1
    end

    (0...counts.size).map{|i| [i+min]*counts[i]}.flatten
end

counting = [2, 5, 2]
result = counting_sort(counting)
result.each do |i|
    puts i
end

## radix
puts 'radix'
def kth_digit(n, i)
    while (i>1)
        n = n / 10
        i = i - 1
    end
    n % 10
end

def radix_sort(arr)
    max = arr.max
    d = Math.log10(max).floor + 1

    (1..d).each do |i|
        tmp = []
        (0..9).each do |j|
            tmp[j] = []
        end

        arr.each do |a|
            kth = kth_digit(a, i)
            tmp[kth] << a
        end
        arr = tmp.flatten
    end
    return arr
end

radix = [22, 21, 23]
result = radix_sort(radix)
result.each do |i|
    puts i
end


## bucket
puts 'bucket'
def first_number(n)
    (n*10).to_i
end

def bucket_sort(arr)
    tmp = []
    (0..9).each do |j|
        tmp[j] = []
    end

    arr.each do |a|
        k = first_number(a)
        tmp[k] << a
    end

    (0..9).each do |j|
        tmp[j] = quick_sort(tmp[j])
    end

    tmp.flatten
end

bucket = [0.63, 0.13, 0.44]
p bucket_sort(bucket)

## cocktail

## parity

## pigeonhole
