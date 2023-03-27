package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TestPlayer {
    ArrayList<Player> playerArrayList;
    Random rand;

    public TestPlayer() {
        playerArrayList = new ArrayList<Player>();
        rand = new Random();
    }

    private void printName() {
        String line = "";
        String splitBy = ",";

        try
        {
            BufferedReader br = new BufferedReader(new FileReader("FIFA20.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] player = line.split(splitBy);    // uses comma as separator
                System.out.println("Name: " + player[0] + " Team: " + player[1] + " Liga: "
                        + player[2]);
                String[] strNation = player[2].split(" ");
                if (strNation[0]=="Spain"){
                    playerArrayList.add(new Player(player[0],player[1], "Spain"));
                } else{
                    playerArrayList.add(new Player(player[0],player[1], player[2]));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //Main method here
    public static void main(String[] args) {
        TestPlayer testPlayer = new TestPlayer();
        testPlayer.printName(); //Prints the name, club and league for each player (about 14000 in total)
        testPlayer.TestPrintsAPlayer(); //Prints the name, club and league for one well-known player
        testPlayer.GenerateGuesses(); //Starts a game where one can guess which player played in LaLiga in 2021 (based on FIFA21 statistics)
    }

    private void GenerateGuesses() {
        String choices[] = new String[10];
        int n;
        int n2;

        while(true){
            n = rand.nextInt(500);

            if(playerArrayList.get(n).getLiga().startsWith("Spain")){
                n2 = rand.nextInt(100);
                while(playerArrayList.get(n2).getLiga().startsWith("Spain")) {
                    n2 = rand.nextInt(100);
                }
                System.out.println("Which player played in LaLiga in 2021? Answer 1/2");
                System.out.println(playerArrayList.get(n).getName() + " or " + playerArrayList.get(n2).getName());
                Scanner in = new Scanner(System.in);
                if(in.nextInt()==1){
                    System.out.println("Correct!");
                } else{
                    System.out.println("Wrong!");
                }
            }
        }

    }

    private void TestPrintsAPlayer() {
        int n = rand.nextInt(200);
        Player p = playerArrayList.get(n);

        while(p.getTeam()=="Icons") {
            n = rand.nextInt(100);
            p = playerArrayList.get(n);
        }

        System.out.println("Name: " + p.getName() + " Team: " + p.getTeam() + " Nation: " + p.getLiga());

    }

}
