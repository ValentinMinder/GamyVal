package ch.heigvd.amt.gamyval.restapi.dtos;

/**
 * DTO used to send information about an end-user (output), that includes all
 * the computed statistics of the user (score, rank, next rank, etc)
 *
 * @author Valentin Minder
 */
public class EndUserSummaryDTO {

    private String userID;
    private int score;
    private String rank;
    private String nextRank;
    private long nextRankMissing;

    public EndUserSummaryDTO() {

    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUserID() {
        return userID;
    }

    public int getScore() {
        return score;
    }

    public String getRank() {
        return rank;
    }

    public void setNextRank(String nextRank) {
        this.nextRank = nextRank;
    }

    public void setNextRankMissing(long nextRankMissing) {
        this.nextRankMissing = nextRankMissing;
    }

    public String getNextRank() {
        return nextRank;
    }

    public long getNextRankMissing() {
        return nextRankMissing;
    }

}
