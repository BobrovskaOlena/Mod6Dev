package com.example.mod6dev;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Client {
    public Long id;
    public String name;
    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
