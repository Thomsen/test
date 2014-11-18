$LOAD_PATH << '.'

require 'trig'
require 'moral'

class Sample

  include Trig
  include Moral

  def cos
    puts 'cos no param'
  end

end
