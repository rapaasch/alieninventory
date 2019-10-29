package com.codingcoven.curiocollection.DTO;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "location")
public class Location {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "locid")
    int locId;

    @Column(name = "locname")
    String locName;

    @Column
    String civilization;

    @Column
    String era;

    @Column
    String galaxy;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Items> items;

    public Location( String locName, String civilization, String era, String galaxy) {
        this.locName = locName;
        this.civilization = civilization;
        this.era = era;
        this.galaxy = galaxy;
    }
}
