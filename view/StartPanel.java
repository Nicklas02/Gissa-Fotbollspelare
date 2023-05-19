package view;

import controller.Controller;
import model.Difficulty;
import model.GameType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Panelen för startvyn i spelet.
 */
public class StartPanel extends JFrame {

    private JButton start, showlist, help;
    private JScrollPane scrollPane;
    private boolean show = false;
    private JLabel label, playerNameLabel, backgroundLabel;
    private JTextField playerNameJTextField;
    private JPanel startPanel;
    private int width = 800;
    private int height = 800;
    private Font font = new Font("Arial", Font.BOLD, 24);
    private JTextArea scoresJTextArea;
    private JComboBox<String> difficultyOptions;
    private JComboBox<String> gameTypeOptions;
    private Controller controller;

    public StartPanel(Controller controller) {
        this.controller = controller;
        this.setTitle("Gissa Fotbollsspelare");
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }

    /**
     * Lägger Startpanelen i fönstret och visar den.
     * @param highScoreList HighScorlistan visas i startpanelen.
     */
    public void addStartPanel(String[] highScoreList) {
        startPanel = new JPanel();
        startPanel.setLayout(null);
        createLabels();
        createTextField();
        createButtons();
        createTextArea(highScoreList);
        createRadioButtons();
        createHelpButton();
        startPanel.add(playerNameLabel);
        startPanel.add(playerNameJTextField);
        startPanel.add(start);
        startPanel.add(label);
        startPanel.add(scrollPane);
        startPanel.add(showlist);
        startPanel.add(help);
        startPanel.add(difficultyOptions);
        startPanel.add(gameTypeOptions);
        startPanel.add(backgroundLabel);
        startPanel.setBounds(0, 0, width, height);
        this.add(startPanel);
        this.setVisible(true);
    }

    /**
     * Lägg till bakgrundsbild för gränssnittet.
     */
    private void createLabels() {
        // Lägg till en bild i panelen
        ImageIcon imageIcon = new ImageIcon("images/bluestart.jpg");
        Image image = imageIcon.getImage();
        Image scaled = image.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaled);
        backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0, 0, width, height);
        playerNameLabel = new JLabel("Användarnamn: ");
        playerNameLabel.setFont(font);
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setBounds(145, height / 2 - 125, 200, 50);
        // Skapa en label och en knapp
        label = new JLabel("Välkommen till Gissa Fotbollsspelare!");
        label.setForeground(Color.WHITE);
        label.setBounds(170, 50, width - 100, 50);
        Font labelFont = new Font(Font.SANS_SERIF,Font.BOLD, 26);
        label.setFont(labelFont);

    }

    /**
     * Skapa och hantera spelarnamn
     */
    private void createTextField() {
        playerNameJTextField = new JTextField("");
        playerNameJTextField.setFont(font);
        playerNameJTextField.setBackground(Color.DARK_GRAY);
        playerNameJTextField.setForeground(Color.WHITE);
        playerNameJTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playerNameJTextField.setBounds(330, height / 2 - 115, 230, 30);
        playerNameJTextField.addKeyListener(new KeyAdapter() {


            /**
             * Tlllåter inte andvändaren att skriva siffror med sitt namn.
             * @param e the event to be processed
             */
            @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isLetter(c)) {
                e.consume();
            }
        }
    });
    }

    /**
     * Visar highscore-listan i start skärmen.
     * @param highScoreList en array med highscore-resultaten.
     */
    private void createTextArea(String[] highScoreList) {
        String scores = "";
        for (String s : highScoreList) {
            scores += s + "\n";
        }
        scoresJTextArea = new JTextArea("Topp 10 Resultat:\n" + scores);
        scoresJTextArea.setFont(font);
        scoresJTextArea.setEditable(false);
        scoresJTextArea.setLineWrap(true);
        scoresJTextArea.setWrapStyleWord(true);
        scoresJTextArea.setBackground(Color.DARK_GRAY);
        scoresJTextArea.setForeground(Color.WHITE);
        scrollPane = new JScrollPane(scoresJTextArea);
        scrollPane.setVisible(false);
        scrollPane.setBounds(10, height / 2 - 70, 300, height / 2 - 55);
    }

    /**
     * Skapar två knappar "Starta spelet" och "Topplista"
     */
    private void createButtons() {
        start = new JButton("  Starta spelet  ");
        start.setFont(font);
        start.setBackground(Color.LIGHT_GRAY);
        start.setForeground(Color.WHITE);
        start.setFocusPainted(false);
        start.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        start.setBounds(330, 380, 180, 40);
        start.addActionListener(e -> controller.displayQuestions());
        showlist = new JButton("  Topplista  ");
        showlist.setFont(font);
        showlist.setBackground(Color.LIGHT_GRAY);
        showlist.setForeground(Color.WHITE);
        showlist.setFocusPainted(false);
        showlist.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        showlist.setBounds(330, height / 2 - 70, 180, 40);
        showlist.addActionListener(e -> {
            show = !show;
            scrollPane.setVisible(show);
        });
    }

    /**
     *  Lägger till en panel med frågor i fönstret.
     * @param questionPanel QuestionPanel JPanel-objektet som innehåller frågorna.
     */
    public void addQuestionsPanel(JPanel questionPanel) {
        this.remove(startPanel);
        questionPanel.setBounds(0, 0, width, height);
        this.add(questionPanel);
        repaint();
    }

    /**
     * Skapar JComboBox för svårighetsgrad och fem olika GameType.
     */
    private void createRadioButtons() {
        String[] diffOptions = {"Normal", "Svår"};
        difficultyOptions = new JComboBox<>(diffOptions);
        difficultyOptions.setBounds(550, 430, 120, 30);
        difficultyOptions.setSelectedIndex(0);
        String[] gameOptions = {"PremierLeague", "LaLiga", "Bundesliga", "Ligue1", "SerieA"};
        gameTypeOptions = new JComboBox<>(gameOptions);
        gameTypeOptions.setBounds(333, 430, 200, 30);
        gameTypeOptions.setSelectedIndex(0);
    }

    public GameType getGameType() {
        String selectedGameType = (String) gameTypeOptions.getSelectedItem();
        switch (selectedGameType) {
            case "PremierLeague":
                return GameType.PremierLeague;
            case "LaLiga":
                return GameType.LaLiga;
            case "Bundesliga":
                return GameType.Bundesliga;
            case "Ligue1":
                return GameType.Ligue1;
            case "SerieA":
                return GameType.SerieA;
            default:
                return null;
        }
    }

    /**
     * Läser in en beskrivning av programmet från en textfil och skapar en popup ruta som den
     * visas på när man trycker på knappen "hjälp"
     */
    public void createHelpButton(){
        String text = "";
        try (BufferedReader br = new BufferedReader(new FileReader("files/HjälpText.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                text += line + "\n";
            }
        } catch (IOException e) {
            System.err.println("Error reading questions from file: " + e.getMessage());
            System.exit(1);
        }
        JFrame popupFrame = new JFrame("Gissa fotbollsspelare");
        popupFrame.setLocationRelativeTo(null);
        JTextArea helpText = new JTextArea(text);
        helpText.setEditable(false);
        popupFrame.setSize(600, 425);
        PopupFactory pf = new PopupFactory();
        JPanel popupPanel = new JPanel();
        popupPanel.setBackground(Color.WHITE);
        popupPanel.add(helpText);
        Popup p = pf.getPopup(popupFrame, popupPanel, 180, 100);
        popupFrame.add(popupPanel);

        help = new JButton(  "  Hjälp  ");
        help.setFont(font);
        help.setBackground(Color.LIGHT_GRAY);
        help.setForeground(Color.WHITE);
        help.setFocusPainted(false);
        help.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        help.setBounds(width-200, height-100, 180,40);
        help.addActionListener(e -> {
            popupFrame.setVisible(true);
            p.show();
        });
    }

    public Difficulty getDifficulty() {
        String selectedDifficulty = (String) difficultyOptions.getSelectedItem();
        if (selectedDifficulty.equals("Normal")) {
            return Difficulty.Normal;
        } else {
            return Difficulty.Hard;
        }
    }

    public String getPlayerName() {
        return playerNameJTextField.getText().trim();
    }

}
