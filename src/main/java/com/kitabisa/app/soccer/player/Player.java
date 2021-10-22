package com.kitabisa.app.soccer.player;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kitabisa.app.soccer.team.Team;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String name;
    private Integer number;
    private Integer age;

    @JoinColumn(name = "teamId", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Team team;
}
