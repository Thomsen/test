class CommentsController < ApplicationController

  http_basic_authenticate_with name: "writer", password: "secret", except: [:index, :show]
  
  def index
    @post = Article.find(params[:article_id])
    @comments = @post.comments
  end

  def new
    @post = Article.find(params[:article_id])
    @comment = @post.comments.build
  end

  def create
    @post = Article.find(params[:article_id])
    @comment = @post.comments.build(comment_params)
    if @comment.save
      redirect_to article_url(@post, @comment)
    else
      render :action => "new"
    end
  end

  def show
    @post = Article.find(params[:article_id])
    @comment = @post.comments.find(params[:id])
  end

  def edit
    @post = Article.find(params[:article_id])
    @comment = @post.comments.find(params[:id])
  end
  
  def destroy
    @article = Article.find(params[:article_id])
    @comment = @article.comments.find(params[:id])
    @comment.destroy
    redirect_to article_path(@article)
  end
  
  private
    def comment_params
      params.require(:comment).permit(:commenter, :body)
    end

end
