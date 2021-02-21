package com.edu.lucas.falacz.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name="posts")
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    @Column(length = 150)
    @NotBlank
    String text;

    @ManyToOne
    User user;

    @ManyToMany
    @JoinTable(
            name = "post_likes",
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<User> likes;

}
