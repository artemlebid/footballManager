package org.artemlebid.footballmanager.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private Double balance;
    private Integer commissionRate;
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Player> players;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name) && Objects.equals(country, team.country) && Objects.equals(balance, team.balance) && Objects.equals(commissionRate, team.commissionRate) && Objects.equals(players, team.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, balance, commissionRate, players);
    }
}
