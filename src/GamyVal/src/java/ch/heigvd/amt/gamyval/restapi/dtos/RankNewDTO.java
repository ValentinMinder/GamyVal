package ch.heigvd.amt.gamyval.restapi.dtos;

/**
 * DTO used to create a new rank (input).
 * 
 * @author Valentin Minder
 */
public class RankNewDTO {

    private String name;
    private long score;

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
