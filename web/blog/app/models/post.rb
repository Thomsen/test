class Post < ActiveRecord::Base
  # validates :name, :presence => true
  # validates :title, :presence => true,
  #                  :length => { :minimum => 5 }
  
  validates_presence_of :name, :title
  validates_length_of :title, :minimum => 5

end
