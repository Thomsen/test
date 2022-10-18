class CommentsController < ApplicationController

  def create
      @article = Article.find(params[:article_id])
      @comment = @article.comments.create(comment_params)
      redirect_to article_path(@article)
  end
  
  # private
  #   def comment_params
  #     params.require(:comment).permit(:commenter, :body)
  #   end

  def index
    @article = Article.find(params[:article_id])
    @comments = @article.comments
  end

  def new
    @article = Article.find(params[:article_id])
    @comment = @article.comments.build
  end

  def show
    @article = Article.find(params[:article_id])
    @comment = @article.comments.find(params[:id])
  end

  def edit
    @article = Article.find(params[:article_id])
    @comment = @article.comments.find(params[:id])
  end
  
  def destroy
    @article = Article.find(params[:article_id])
    @comment = @article.comments.find(params[:id])
    @comment.destroy
    redirect_to article_path(@article)
  end

  def comment_params
    params.require(:comment).permit(:commenter, :body, :status)
  end
  
end
