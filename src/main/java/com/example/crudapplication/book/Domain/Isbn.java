package com.example.crudapplication.book.Domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.apache.commons.validator.routines.ISBNValidator;

import java.util.Objects;


@Getter
@Embeddable
public class Isbn {
    private  String value;

    public Isbn(String value) {
        ISBNValidator validator = new ISBNValidator();
        if(!validator.isValid(value) || value == null){
            throw new IllegalArgumentException("invalid ISBN: "+ value);
        }
        this.value = value;
    }

    public Isbn() {

    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if( o == null || getClass() != o.getClass()) return false;

        Isbn isbn = (Isbn) o;

        return Objects.equals(value, isbn.value);


    }

    @Override
    public int hashCode(){
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Isbn{" +
                "value='" + value + '\'' +
                '}';
    }
}


