package com.codingcoven.curiocollection.DTO;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="author")
public class Author {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="authorid")
    int authorId;

    @Column(name="authorname")
    String authorName;

    @Column(name="username")
    String userName;

    @Column(name="authorpassword")
    String authorPassword;

    @Column(name="isenabled")
    Boolean isEnabled;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Role> roles;

    @OneToMany(mappedBy = "author", cascade=CascadeType.ALL, orphanRemoval = true)
    List<Article> articles;

    public Author(String authorName, String userName, String authorPassword) {
        this.authorName = authorName;
        this.userName = userName;
        this.authorPassword = authorPassword;
    }
}
