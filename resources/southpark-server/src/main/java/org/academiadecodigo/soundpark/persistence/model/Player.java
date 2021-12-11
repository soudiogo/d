package org.academiadecodigo.soundpark.persistence.model;


import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player extends AbstractModel {

    private String userName;
    private String password;
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the first name of the customer
     *
     * @return the customer last name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the first name of the customer
     *
     * @param userName the name to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {

        // printing recipients with lazy loading
        // and no session will cause issues
        return "Customer{" +
                "firstName='" + userName + '\'' +
                ", score='" + score + '\'' +
                "} " + super.toString();
    }


}
