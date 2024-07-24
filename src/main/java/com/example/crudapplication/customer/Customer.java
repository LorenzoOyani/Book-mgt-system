package com.example.crudapplication.customer;

import com.example.crudapplication.customer.Domain.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "customer_id", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Address> address;
}
