class Article < ActiveRecord::Base
  validates :title, presence: true,
                    length: {minimum: 2}

  has_many :comments,  # @article.comments
            dependent: :destroy
           
end
