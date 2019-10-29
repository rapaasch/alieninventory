package com.codingcoven.curiocollection.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Categories {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="catid")
    int catId;

    @Column(name="catname")
    @NotNull
    String catName;

    @Column(name="catabout")
    String catAbout;

    @ManyToMany(fetch=FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "itemtype",
            joinColumns = {@JoinColumn(name = "catid")},
            inverseJoinColumns = {@JoinColumn(name = "itemid")})
    List<Items> items;

    public Categories(@NotNull String catName, String catAbout) {
        this.catName = catName;
        this.catAbout = catAbout;
    }
}
