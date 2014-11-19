module Work

  class Computer

    # class variable
    @@count = 0

    # private
    def initialize(w = 1024, h = 768)
      @width, @height = w, h
      @@count += 1
    end

    # default, public
    def printPixels
      @pixels = @width * @height
      puts "pixels (#@width x #@height): #@pixels" # ' print #@pixels
    end

    # class method
    def self.printCount
      puts "computer count is: #@@count"
    end

    # attr_accessor :width, :height
    def getWidth
      @width
    end

    def setWidth=(value)
      @width = value
    end

    def getHeight
      @height
    end

    def say
      puts 'i\'m computer'
    end

  end

end
