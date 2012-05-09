class Post < ActiveRecord::Base
  # validates :name, :presence => true
  # validates :title, :presence => true,
  #                  :length => { :minimum => 5 }
  
  validates_presence_of :name, :title
  validates_length_of :title, :minimum => 5

  has_many :comments
  has_many :tags

  # from rails 2.3
  #accepts_nested_attributes_for :tags, :allow_destroy => :true, 
  #  :reject_if => proc { |attrs| attrs.all? { |k, v| v.blank? }}
end
