package model;

public class Player {
    private int id;
    private String name;
    private int age;
    private String nationality;
    private int overall;
    private int potential;
    private String club;
    private String value;
    private String wage;
    private String preferredFoot;
    private int internationalRep;
    private int weakFoot;
    private int skillMoves;
    private String workRate;
    private String bodyType;
    private String position;
    private String joinedClub;
    private String validContractUntil;
    private int height;
    private String weight;
    private String releaseClause;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    public int getOverall() {
        return overall;
    }

    public int getPotential() {
        return potential;
    }

    public String getClub() {
        return club;
    }

    public String getValue() {
        return value;
    }

    public String getWage() {
        return wage;
    }

    public String getPreferredFoot() {
        return preferredFoot;
    }

    public int getInternationalRep() {
        return internationalRep;
    }

    public int getWeakFoot() {
        return weakFoot;
    }

    public int getSkillMoves() {
        return skillMoves;
    }

    public String getWorkRate() {
        return workRate;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getPosition() {
        return position;
    }

    public String getJoinedClub() {
        return joinedClub;
    }

    public String getValidContractUntil() {
        return validContractUntil;
    }

    public int getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getReleaseClause() {
        return releaseClause;
    }

    public int getKitNumber() {
        return kitNumber;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    @Override
    public String toString() {
        return "Player: " + "id=" + id + ", name='" + name + ", age=" + age;
    }
}
