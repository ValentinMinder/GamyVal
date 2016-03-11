/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gamyval.models.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity (pojo) that represent a Rank: each rank is reached at a certain level
 * of points.
 *
 * @author Valentin Minder
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Rank.findGreaterThan", query = "SELECT r FROM Application a INNER JOIN a.apiKey ak INNER JOIN a.ranks r WHERE ak.apiKey = :apiKey AND r.score > :score ORDER BY r.score ASC"),
    @NamedQuery(name = "Rank.findSmallerThan", query = "SELECT r FROM Application a INNER JOIN a.apiKey ak INNER JOIN a.ranks r WHERE ak.apiKey = :apiKey AND r.score <= :score ORDER BY r.score DESC"),
    @NamedQuery(name = "Rank.findAllOrdered", query = "SELECT r FROM Application a INNER JOIN a.apiKey ak INNER JOIN a.ranks r WHERE ak.apiKey = :apiKey ORDER BY r.score ASC")
})
@XmlRootElement
public class Rank extends AbstractDomainModelEntity<Long> {

    private String name;
    private long score;

    public Rank() {

    }

    public Rank(String name, long score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

}
