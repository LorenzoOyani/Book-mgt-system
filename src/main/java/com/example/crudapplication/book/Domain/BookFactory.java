package com.example.crudapplication.book.Domain;


import com.example.crudapplication.book.Domain.payload.CreateBookRequest;


public class BookFactory {
    public static Book createBook(CreateBookRequest request){
        if(!validRequestEntries(request)){
            throw new IllegalArgumentException("invalid book request data");
        }
    Book book = new Book();
     book.setIsbn(request.getIsbn());
     book.setName(request.getBookName());
     book.setAuthorFullName(request.getFullName());
     book.setStock(request.getStock());
     book.setPrice(request.getPrice());

     return book;
    }

    private static boolean validRequestEntries(CreateBookRequest request){
        if(request.getId() != null){
            return false;
        }
        if(request.getIsbn() == null || request.getIsbn().getValue().length() > 13){
            return false;
        }
        if(request.getBookName() == null || request.getBookName().isEmpty()){
            return false;
        }
        if (request.getFullName() == null || request.getFullName().isEmpty()) {
            return false;
        }
        if (request.getStock() == null) {
            return false;
        }
        return request.getPrice() != null;
    }
}
