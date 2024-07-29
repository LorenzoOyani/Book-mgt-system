package com.example.crudapplication.book.Domain.bookMapper;


import com.example.crudapplication.book.Domain.Book;
import com.example.crudapplication.book.Domain.BookDto;
import com.example.crudapplication.book.Domain.payload.CreateBookRequest;
import com.example.crudapplication.book.Domain.payload.UpdateBookRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookMapper {


    /**
     * @param request is mapped at {@link CreateBookRequest} to entity {@link Book};
     * @return book to populated with request
     *
     * */

    public static Book map(CreateBookRequest request){
        return  Book.builder()
                .id(request.getId())
                .isbn(request.getIsbn())
                .name(request.getBookName())
                .authorFullName(request.getFullName())
                .stock(request.getStock())
                .price(request.getPrice())
                .version(request.getVersion())
                .build();

    }

    /**
     * @param bookEntity the entity to be updated by mapping {@link  UpdateBookRequest} to the entity
     * @param updateBook the class that maps to the entity class {@link Book}
     * @Return has no return type since the method is a void type
     * */
    public static void mapBookToUpdate(Book bookEntity, UpdateBookRequest updateBook){
        bookEntity.setId(updateBook.getId());
        bookEntity.setIsbn(updateBook.getIsbn());
        bookEntity.setName(updateBook.getBookName());
        bookEntity.setAuthorFullName(updateBook.getFullName());
        bookEntity.setStock(updateBook.getStock());
        bookEntity.setPrice(updateBook.getPrice());
        bookEntity.setVersion(updateBook.getVersion());
    }

    public static BookDto mapToDto(Book savedBook) {
        return BookDto.builder()
                .id(savedBook.getId())
                .isbn(savedBook.getIsbn())
                .name(savedBook.getName())
                .authorFullName(savedBook.getAuthorFullName())
                .stock(savedBook.getStock())
                .version(savedBook.getVersion())
                .build();
    }
}
