package com.codingcoven.curiocollection.DTO;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="items")
public class Items {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column (name="itemid")
    int itemId;

    @Column(name="itemname")
    @NotNull
    String itemName;

    @Column(name="itemabout")
    String itemAbout;

    @Column(name="itemvalue")
    BigDecimal value;

    @Column
    int quantity;

    @Column(name="datefound")
    LocalDate dateFound;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "locid")
    Location location;

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private List<Article> articles = new ArrayList<Article>();

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    List<Categories> categories = new ArrayList<Categories>();

    public Items(@NotNull String itemName, String itemAbout, BigDecimal value, int quantity, LocalDate dateFound, Location location) {
        this.itemName = itemName;
        this.itemAbout = itemAbout;
        this.value = value;
        this.quantity = quantity;
        this.dateFound = dateFound;
        this.location = location;
    }
}
