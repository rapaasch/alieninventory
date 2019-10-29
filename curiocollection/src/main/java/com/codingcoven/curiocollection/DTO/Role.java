package com.codingcoven.curiocollection.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode
@Setter
@AllArgsConstructor
@Table(name="roles")
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="rolesid")
    int roleId;

    @Column(name="rolesname")
    @NotNull
    String roleName;

    @ManyToMany(fetch=FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "userroles",
            joinColumns = {@JoinColumn(name = "rolesid")},
            inverseJoinColumns = {@JoinColumn(name = "authorid")})
    List<Author> users;
}
