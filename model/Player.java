package model;

/**
 * Syftet med klassen är att skapa ett spelarobjekt med cirka tio parametrar med tillhörande getter-metoder.
 * Spelarobjektet utifrån databasurvalet i klassen "GetSample"
 */
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

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
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

    public int getWeakFoot() {
        return weakFoot;
    }

    public int getSkillMoves() {
        return skillMoves;
    }

    public String getPosition() {
        return position;
    }

    public int getHeight() {
        return height;
    }

    public int getKitNumber() {
        return kitNumber;
    }
}
