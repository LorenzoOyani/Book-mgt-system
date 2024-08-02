package com.example.crudapplication.users;

import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.util.Assert;

import java.util.Objects;

@Data
@Embeddable
public class UserId {

    private Long id;

    public UserId(Long id){
        Objects.requireNonNull(id);
        Assert.notNull(id,"user identity cannot be  empty");
    }

    public UserId() {}


    @Override
    public boolean  equals(Object   o){
        if(this==o)return true;
        if(o.getClass() !=this.getClass())return  false;
        UserId  userId=(UserId) o;
        return  Objects.equals(id,userId.id);
    }

    @Override
    public int  hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserId{" +
                "id=" + id +
                '}';
    }
}
