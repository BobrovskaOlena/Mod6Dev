package com.example.mod6dev;

import lombok.*;
@Setter
@NoArgsConstructor
@ToString

public class Client {
    public Long id;
    public String name;
    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
