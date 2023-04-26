package model;

public class Player {
    private int id;
    private String name;
    private int age;

    public Player(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Player: " + "id=" + id + ", name='" + name + ", age=" + age;
    }
}
