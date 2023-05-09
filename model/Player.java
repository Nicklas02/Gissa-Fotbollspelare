package model;

public class Player {
    private String name;
    private int age;
    private String nationality;
    private int overall;
    private String club;
    private String value;
    private String wage;
    private int weakFoot;
    private int skillMoves;
    private String position;
    private int height;
    private int kitNumber;

    public Player(String name, int age, String nationality,
                  int height, int weakFoot, int kitNumber, String value, String wage,
                  int overall, int skill_moves, String club, String position) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.height = height;
        this.weakFoot = weakFoot;
        this.kitNumber = kitNumber;
        this.value=value;
        this.wage=wage;
        this.overall=overall;
        this.skillMoves=skill_moves;
        this.club=club;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    public int getWeakFoot() {
        return weakFoot;
    }

    public void setWeakFoot(int weakFoot) {
        this.weakFoot = weakFoot;
    }

    public int getSkillMoves() {
        return skillMoves;
    }

    public void setSkillMoves(int skillMoves) {
        this.skillMoves = skillMoves;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getKitNumber() {
        return kitNumber;
    }

    public void setKitNumber(int kitNumber) {
        this.kitNumber = kitNumber;
    }
}
