class ArticlesController < ApplicationController

  http_basic_authenticate_with name: "writer", password: "secret", except: [:index, :show]

  def index
    @posts = Article.all
  end
    
  def new
    @post = Article.new
  end

  def create
    # render text: params[:post].inspect  # {"title"=>"", "text"=>""}
    
    # @post = Post.new(params[:post])  # ActiveModel::ForbiddenAttributesError
    @post = Article.new(post_params)
    if @post.save
      redirect_to @post
    else
      render 'new'
    end
    
  end

  def show
    @post = Article.find(params[:id])
  end

  def edit
    @post = Article.find(params[:id])
  end

  def update
    
  end

  def destroy
    @post = Article.find(params[:id])
    @post.destroy

    redirect_to posts_path
  end
  
  private
    def post_params
      params.require(:post).permit(:title, :text)
    end
    
end
