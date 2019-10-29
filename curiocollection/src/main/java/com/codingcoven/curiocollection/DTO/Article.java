package com.codingcoven.curiocollection.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="article")
public class Article {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="articleid")
    int articleId;

    @Column(name="articletitle")
    String articleTitle;

    @Column (name="articletext")
    String articleText;

    @Column(name="isfeatured")
    Boolean isFeatured;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "authorid", nullable = false)
    Author author;

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "itemarticles",
            joinColumns = {@JoinColumn(name = "articleid")},
            inverseJoinColumns = {@JoinColumn(name = "itemid")})
    List<Items> items;

    public Article(String articleTitle, String articleText, Boolean isFeatured, Author author) {
        this.articleTitle = articleTitle;
        this.articleText = articleText;
        this.isFeatured = isFeatured;
        this.author = author;
    }
}
