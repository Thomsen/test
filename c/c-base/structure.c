#include "structure.h"

struct books
{
  char title[50];
  char author[20];
  char subject[100];
  int book_id;
};

void print_book(struct books book);
void print_book_bp(struct books *book);

void structure()
{
  struct books book1;
  struct books book2;

  strcpy(book1.title, "C Programing");
  strcpy(book1.author, "Author");
  strcpy(book1.subject, "C Programing Tutorial");
  book1.book_id = 12321231;

  print_book(book1);
  print_book_bp(&book1);
}

void print_book(struct books book)
{
  printf("book one title: %s\n", book.title);
  printf("book one author: %s\n", book.author);
  printf("book one subject: %s\n", book.subject);
}

void print_book_bp(struct books *book)
{
  printf("book one title: %s\n", book->title);
  printf("book one author: %s\n", book->author);
  printf("book one subject: %s\n", book->subject);
}
