import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JFrame implements ActionListener {
    private static JFrame frame;
    private static ArrayList<Label> labelList = new ArrayList<>();
    private static ArrayList<JButton> jButtonList = new ArrayList<>();
    private JTextField hapInput;
    private JLabel scoreField;
//    private static ArrayList<String> solutionList = new ArrayList<>();
    private static JTextPane list;
    private static JScrollPane listPane;
    private static Game game;

    private static JPanel introPanel;
    private static JPanel howToPlayPanel;
    private static JPanel howToPlayTwoPanel;
    private static JPanel chooseLevelPanel;
    private Font bigFont = new Font( "Andale Mono",Font.PLAIN,30);
    private Font instFont = new Font( "Andale Mono",Font.PLAIN,25);

    public boolean isBeginner = false;


    private Main() throws IOException {
        frame = new JFrame("Gyul Hap Game");

        // Intro test
        introPanel = new JPanel();
        JLabel introPic = new JLabel(new ImageIcon("./data/Pages/page01.jpeg"));
        JButton jmpIntro = new JButton ("HOW TO PLAY");
        jmpIntro.setActionCommand("btnJmpIntro");
        jmpIntro.addActionListener(this);
        introPanel.add(introPic);
        introPanel.add(jmpIntro);
        frame.setContentPane(introPanel);
        frame.pack();
        frame.setVisible(true);

        // ... original place
    }

    private void howToPlay() {
        frame.remove(introPanel);
        howToPlayPanel = new JPanel();
        JLabel howToPlayPic = new JLabel (new ImageIcon("./data/Pages/page02.jpeg"));
        JButton jmpHTP = new JButton ("NEXT");
        jmpHTP.setActionCommand("btnJmpHTP");
        jmpHTP.addActionListener(this);
        howToPlayPanel.add(howToPlayPic);
        howToPlayPanel.add(jmpHTP);

        frame.setContentPane(howToPlayPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void howToPlayTwo() {
        frame.remove(howToPlayPanel);
        howToPlayTwoPanel = new JPanel();
        JLabel howToPlayPic = new JLabel (new ImageIcon("./data/Pages/page03.jpeg"));
        JButton jmpHTPTwo = new JButton ("PLAY");
        jmpHTPTwo.setActionCommand("btnJmpHTPTwo");
        jmpHTPTwo.addActionListener(this);
        howToPlayTwoPanel.add(howToPlayPic);
        howToPlayTwoPanel.add(jmpHTPTwo);

        frame.setContentPane(howToPlayTwoPanel);
        frame.pack();
        frame.setVisible(true);
    }


    private void chooseLevel() {
        frame.remove(howToPlayTwoPanel);
        chooseLevelPanel = new JPanel();
        chooseLevelPanel.setPreferredSize(new Dimension (500, 500));
        JLabel levelLabel = new JLabel ("Choose the Difficulty Level");
        levelLabel.setFont(instFont);
        JButton beginnerBtn = new JButton("BEGINNER");
        JButton intermediateBtn = new JButton("INTERMEDIATE");
        beginnerBtn.setActionCommand("btnBeginner");
        beginnerBtn.addActionListener(this);
        intermediateBtn.setActionCommand("btnIntermediate");
        intermediateBtn.addActionListener(this);
        chooseLevelPanel.add(levelLabel);
        chooseLevelPanel.add(beginnerBtn);
        chooseLevelPanel.add(intermediateBtn);

        frame.setContentPane(chooseLevelPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void mainHelper() {
        // Destroy first page

        frame.remove(chooseLevelPanel);

        JPanel panel = new JPanel(new GridLayout(6,3));
//        JPanel panel2 = new JPanel();
        list = new JTextPane();
        list.setSize(30,70);
        list.setEditable(false);
        list.setVisible(true);
        list.setText("");
        list.setFont(new Font("Andale Mono", Font.PLAIN, 11));
        listPane = new JScrollPane(list);
        JPanel answer = new JPanel();
        JPanel score = new JPanel();
        JSplitPane board = new JSplitPane(JSplitPane.VERTICAL_SPLIT, answer, score);
        board.setDividerLocation(80);

        JSplitPane top = new JSplitPane(JSplitPane.VERTICAL_SPLIT, listPane, board);
        top.setDividerLocation(400);
        JPanel bottom = new JPanel();
        JSplitPane panel2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, top, bottom);
        panel2.setPreferredSize(new Dimension(300,1000));
        panel2.setDividerLocation(600);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, panel2);
        splitPane.setPreferredSize(new Dimension(900,1000));
        splitPane.setDividerLocation(500);
        panel.setPreferredSize(new Dimension(600,1000));

//        frame.setContentPane(panel);
        frame.setContentPane(splitPane);
        panel.add(labelList.get(0));
        panel.add(labelList.get(1));
        panel.add(labelList.get(2));

        setButtons();

        panel.add(jButtonList.get(0));
        panel.add(jButtonList.get(1));
        panel.add(jButtonList.get(2));

        panel.add(labelList.get(3));
        panel.add(labelList.get(4));
        panel.add(labelList.get(5));
        panel.add(jButtonList.get(3));
        panel.add(jButtonList.get(4));
        panel.add(jButtonList.get(5));

        panel.add(labelList.get(6));
        panel.add(labelList.get(7));
        panel.add(labelList.get(8));
        panel.add(jButtonList.get(6));
        panel.add(jButtonList.get(7));
        panel.add(jButtonList.get(8));

        // Score Board

        JLabel hapLabel = new JLabel("HAP: ");
        hapLabel.setFont(new Font("Andale Mono", Font.PLAIN, 15));
        JLabel hapNote = new JLabel("(** Enter 3 numbers without any space)");
        hapNote.setFont(new Font("Andale Mono", Font.ITALIC, 12));
        hapInput = new JTextField(3);

        JButton hapBtn = new JButton ("Submit");
        hapBtn.setActionCommand("btnHap");
        hapBtn.addActionListener(this);
        hapBtn.setFont(new Font("Andale Mono", Font.ITALIC, 15));
        answer.add(hapLabel);
        answer.add(hapNote);
        answer.add(hapInput);
        answer.add(hapBtn);

        // Display Score
        Font scoreF = new Font("Andale Mono", Font.BOLD, 20);
        JLabel scoreLabel = new JLabel("SCORE: ");
        scoreLabel.setFont(scoreF);
        scoreLabel.setForeground(Color.DARK_GRAY);
        score.add(scoreLabel);

        scoreField = new JLabel(String.valueOf(game.currentScore));
        scoreField.setFont(scoreF);
        scoreField.setForeground(Color.MAGENTA);
        score.add(scoreField);


        // Gyul Button
        JButton gyul = new JButton("GYUL");
        gyul.setFont(bigFont);
        gyul.setForeground(Color.orange);
        gyul.setActionCommand("btnGyul");
        gyul.addActionListener(this);
        bottom.add(gyul);

        // Stop Button
        JButton stop = new JButton ("STOP");
        stop.setFont(bigFont);
        stop.setForeground(Color.red);
        stop.setActionCommand("btnStop");
        stop.addActionListener(this);
        bottom.add(stop);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void main(String[] args) throws IOException {

        // Test Button
        Main main = new Main();

        Font labelFont = new Font("Andale Mono", Font.PLAIN, 18);
        for (int i=1; i<=9; i++) {
            Label l = new Label(Integer.toString(i));
            l.setFont(labelFont);
            labelList.add(l);
        }
        // original place

    }

    private void gameSetUp() {
        game = new Game(isBeginner);
        game.printPictures();
        for (int i=0; i<9; i++) {
            Picture p = game.pictureList.get(i);
            String fileName = "./data/Shapes/" + p.shape + "/" + p.shapeColor + p.shape + ".png";
            ImageIcon shapeImg = new ImageIcon(fileName);
            Image resized = shapeImg.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT);
            JButton btn = new JButton(new ImageIcon(resized));
            Color c;
            if (p.bColor.equals("grey")) {
                c = Color.GRAY;
            } else if (p.bColor.equals("black")) {
                c = Color.BLACK;
            } else {
                c = Color.white;
            }
            btn.setBackground(c);
            btn.setOpaque(true);
            btn.setBorderPainted(false);
            jButtonList.add(btn);

            // Arbitrary Solution added to the solution list
//            solutionList.add("123");
        }

        mainHelper();
    }

    private void setButtons() {
        jButtonList.get(0).setActionCommand("btnOne");
        jButtonList.get(1).setActionCommand("btnTwo");
        jButtonList.get(2).setActionCommand("btnThree");
        jButtonList.get(3).setActionCommand("btnFour");
        jButtonList.get(4).setActionCommand("btnFive");

        jButtonList.get(5).setActionCommand("btnSix");
        jButtonList.get(6).setActionCommand("btnSeven");
        jButtonList.get(7).setActionCommand("btnEight");
        jButtonList.get(8).setActionCommand("btnNine");

        for (int i=0; i<9; i++) {
            jButtonList.get(i).addActionListener(this);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnJmpIntro":
                howToPlay();
                break;
            case "btnJmpHTP":
                howToPlayTwo();
                break;
            case "btnJmpHTPTwo":
                chooseLevel();
                break;
            case "btnBeginner":
                isBeginner = true;
                gameSetUp();
                break;
            case "btnIntermediate":
                gameSetUp();
                break;
            case "btnHap":
                hapHelper();
                break;
            case "btnGyul":
                gyulHelper();
                break;
            case "btnStop":
                stopHelper();
                break;
            case "btnOne":
                System.out.println("Button # 1 chosen");
                break;
//            case "btnTwo":
//                System.out.println("Button # 2 chosen");
//                break;
//            case "btnThree":
//                System.out.println("Button # 3 chosen");
//                break;
//            case "btnFour":
//                System.out.println("Button # 4 chosen");
//                break;
//            case "btnFive":
//                System.out.println("Button # 5 chosen");
//                break;
//            case "btnSix":
//                System.out.println("Button # 6 chosen");
//                break;
//            case "btnSeven":
//                System.out.println("Button # 7 chosen");
//                break;
//            case "btnEight":
//                System.out.println("Button # 8 chosen");
//                break;
//            case "btnNine":
//                System.out.println("Button # 9 chosen");
//                break;

        }
        // We only want to update 'Score' when we're in the middle of the game
        if (!(e.getActionCommand().equals("btnJmpIntro") || e.getActionCommand().equals("btnJmpHTP"))) {
            scoreField.setText(String.valueOf(game.currentScore));
        }
    }

    private void hapHelper() {
        String userAnswer = hapInput.getText();
        if (userAnswer.length() != 3) {
            System.out.println("Throw Invalid Input Exception");
        } else {

            // Divide user's input into three different integers and put them into an array
            ArrayList<Integer> userAnswerList = new ArrayList<>();
            userAnswerList.add(Integer.parseInt(String.valueOf(userAnswer.charAt(0))));
            userAnswerList.add(Integer.parseInt(String.valueOf(userAnswer.charAt(1))));
            userAnswerList.add(Integer.parseInt(String.valueOf(userAnswer.charAt(2))));

//            Collections.sort(userAnswerList);
            Sort qsort = new QuickSort();
            ArrayList<Integer> userAnswerSorted = qsort.sort(userAnswerList, 0, 2);

            // Check if user already got that combination right - NO DUPLICATE TRIES ALLOWED
            if (game.userSolutionList.contains(userAnswerSorted)) {
                list.setText(list.getText() + "\r\n" + "YOU ALREADY GOT THIS COMBINATION RIGHT");
            } else {
                // Otherwise process the following:
                checkUserAnswer(userAnswer, userAnswerList);
            }

        }

        hapInput.setText("");
    }

    private void checkUserAnswer(String userAnswer, ArrayList<Integer> userAnswerList) {
        // Check if input is correct
        if (game.solutionList.contains(userAnswerList)) {
            // Correct Answer
            list.setText(list.getText() + "\r\n" + "CORRECT: "
                    + userAnswer.charAt(0) + " "
                    + userAnswer.charAt(1) + " "
                    + userAnswer.charAt(2));

            // Add to the userSolutionList
            game.userSolutionList.add(userAnswerList);

            // Update Score (+ 100)
            game.currentScore += 100;

        } else {
            // Wrong Answer
            list.setText(list.getText() + "\r\n" + "SORRY, WRONG ANSWER! ");

            // Update Score (- 100)
            game.currentScore -= 100;
        }

    }

    private void gyulHelper() {
        if (game.userSolutionList.size() == game.solutionList.size()) {
            // Correct Gyul
            list.setText(list.getText() + "\r\n" + "CORRECT: NO MORE COMBINATIONS LEFT!");

            // Update Score (+ 300)
            game.currentScore += 300;

        } else {
            // Incorrect Gyul
            list.setText(list.getText() + "\r\n" + "SORRY, THERE ARE STILL COMBINATIONS NOT FOUND.");

            // Update Score (- 20)
            game.currentScore -= 200;

        }

    }

    private void stopHelper() {
        System.exit(0);
    }

}
