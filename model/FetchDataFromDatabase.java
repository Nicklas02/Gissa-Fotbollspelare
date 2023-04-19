package model;

import controller.DatabaseController;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class FetchDataFromDatabase {
    Connection conn;
    private ArrayList<Player> currPlayersList =new ArrayList<>();
    private DatabaseController databaseController;

    public FetchDataFromDatabase(DatabaseController databaseController) {
        conn = getDatabaseConnection();
        this.databaseController = databaseController;
    }

    /**
     * Upprättar en förbindelse med jdbc till databasen gissa fotbollsspelare. Därefter upprättas en query som skrivs
     * till psql för att sedan returnera och skriva ut data.
     * @return metoden returnerar en uppkoppling.
     */
    public Connection getDatabaseConnection() {
        String url = "jdbc:postgresql://pgserver.mau.se/gissa_fotbollsspelare";
        Properties props = new Properties();
        props.setProperty("user", "an5411");
        props.setProperty("password", "7sbqoxrh");
        //String backup user = "am7496"; "xlousk70"
        try {
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Connection Established");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

        public void questionsAboutAge() {
            try {
                String QUERY = "SELECT * FROM \"spelare\"";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);
                Player currPlayer = null;
                Random rand = new Random();
                int limit = 400;
                int strata = rand.nextInt(390);
                int count =0;

                strata = 3;

                while (rs.next() && count<limit) {
                    //if (count > 3) {
                      //  break;
                    //}
                    //System.out.print("ID: " + rs.getString("id"));
                    //System.out.print(" Name: " + rs.getString("namn"));
                    //System.out.print(" Ålder: " + rs.getString("ålder"));
                    //System.out.println();
                    if(count > strata && count < strata +3) {
                            currPlayer = new Player(rs.getString("id"), rs.getString("namn"));
                            currPlayer.setAge(rs.getString("ålder"));
                            currPlayersList.add(currPlayer);
                    }

                    count++;
                }
                stmt.close();
                //conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public ArrayList<Player> getCurrPlayersList() {
        return currPlayersList;
    }

    public String verifyAnswer(int answer) {
        //if questionVariabel == age / highestAge

        Player answerPlayer = currPlayersList.get(answer - 1);
        int answerPlayerAge = Integer.parseInt(answerPlayer.getAge());
        System.out.println("YOU ANSWERED " + answerPlayer.getName());
        boolean correct = true;

        for (Player p : currPlayersList){
            int comparingPlayerAge = Integer.parseInt(p.getAge());
            if (comparingPlayerAge > answerPlayerAge){
                correct = false;
            }
        }

        String userOutput;
        if (correct){
            userOutput = "Correct! ";
        } else{ userOutput = "False! ";}

        userOutput += "\n" + "--------------------" + "\n" + "The answers are: ";
        for (Player p : currPlayersList){
            userOutput += p.getName() + " " + p.getAge() + " --- ";
        }

        return userOutput;
    }


    public String getAlternatives() {
        String str = "";
        int alternative = 1;
        for (Player p : currPlayersList){
            str += alternative;
            str+= ": " + p.getName();
            str += "\n";
            alternative++;
        }
        System.out.println(str);
        return str;
    }
}
