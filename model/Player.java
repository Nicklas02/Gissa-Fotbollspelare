package model;

public class Player {
    String name;
    String team;
    String liga;

    public Player(String name, String team, String liga) {
        this.name = name;
        this.team = team;
        this.liga = liga;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }
}
