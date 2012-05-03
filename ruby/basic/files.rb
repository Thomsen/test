
print File.read("./frb.txt")

puts

require "fileutils.rb"

FileUtils.cp("./frb.txt", "./frb2.txt")

File.open("./frb2.txt", "a") do |f|
  puts f.to_s()
end

puts File.mtime("./frb2.txt")
puts File.mtime("./frb2.txt").hour