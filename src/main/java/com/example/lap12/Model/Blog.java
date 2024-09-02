package com.example.lap12.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title should be not empty")
    @Size( min = 5 , message = "title should be at least 5 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String title;

    @NotEmpty(message = "body should be not empty")
    @Column(columnDefinition = "varchar(200) not null")
    private String body;

    @ManyToOne
    @JsonIgnore
    private User user;
}
