package com.example.crudapplication.book.Domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
public class BookId implements Serializable {
    private  Integer id;

    public BookId(Integer id) {
        Assert.notNull(id, " id cannot be a null value");
        this.id = id;
    }

    public BookId() {

    }


    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        BookId bookId = (BookId) o;

        return Objects.equals(id, bookId.id);
    }

    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookId{" +
                "id=" + id +
                '}';
    }
}
