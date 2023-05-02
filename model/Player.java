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

    public Player(int id, String name, int age, String nationality, int height, int weakFoot, int kitNumber, int overall) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.height = height;
        this.weakFoot = weakFoot;
        this.kitNumber = kitNumber;
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

    public int getHeight() {
        return height;
    }

    public int getWeakFoot() {
        return weakFoot;
    }

    public int getKitNumber() {
        return kitNumber;
    }

    public int getOverall() {
        return overall;
    }

    @Override
    public String toString() {
        return "Player: " + "id=" + id + ", name='" + name + ", age=" + age;
    }
}
