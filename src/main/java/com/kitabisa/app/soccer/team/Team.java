package com.kitabisa.app.soccer.team;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kitabisa.app.soccer.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Player> player;

}
