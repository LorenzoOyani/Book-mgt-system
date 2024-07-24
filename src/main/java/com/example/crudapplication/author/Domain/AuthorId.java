package com.example.crudapplication.author.Domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.modelmapper.internal.util.Assert;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
public class AuthorId implements Serializable {

    private Long id;

    public AuthorId(Long id){
        Assert.notNull(id, "cant be not be null");
        this.id = id;
    }


    public AuthorId() {

    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        AuthorId authorId = (AuthorId) o;

        return Objects.equals(id, authorId.id);
    }

    public int hashCode(){
        return java.util.Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AuthorId{" +
                "id=" + id +
                '}';
    }
}
