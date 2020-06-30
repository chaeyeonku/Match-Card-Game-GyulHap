import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Game {
    List<Picture> pictureList = new ArrayList<>();
    private List<String> colorList = new ArrayList<>();
    private List<String> shapeList = new ArrayList<>();
    private List<String> bColorList = new ArrayList<>();
    private List<String> clChosen = new ArrayList<>();
    private List<String> slChosen = new ArrayList<>();
    private List<String> bclChosen = new ArrayList<>();

    List<List<Integer>> solutionList = new ArrayList<>();
    private List<List<Integer>> pictureListForCalc = new ArrayList<>();
    List<List<Integer>> userSolutionList = new ArrayList<>();
    Integer currentScore;

    Game(boolean isBeginner) {
        Random rand = new Random();
        addToLists();
        if (isBeginner) {
            clChosen.add("red");
            clChosen.add("blue");
            clChosen.add("green");
            slChosen.add("square");
            slChosen.add("triangle");
            slChosen.add("circle");

        } else {
            while (clChosen.size() < 3) {
                int color = rand.nextInt(6);
                if (!clChosen.contains(colorList.get(color))) {
                    clChosen.add(colorList.get(color));
                }
            }

            while (slChosen.size() < 3) {
                int shape = rand.nextInt(6);
                if (!slChosen.contains(shapeList.get(shape))) {
                    slChosen.add(shapeList.get(shape));
                }
            }
        }

        while (pictureList.size() <= 9) {
            int x = rand.nextInt(3);
            int y = rand.nextInt(3);
            int z = rand.nextInt(3);
            Picture p = new Picture(slChosen.get(x), clChosen.get(y), bColorList.get(z));
            if (!pictureList.contains(p)) {
                pictureList.add(p);
                List<Integer> picInNum = new ArrayList<>();
                picInNum.add(x);
                picInNum.add(y);
                picInNum.add(z);
                pictureListForCalc.add(picInNum);
            }
        }
        currentScore = 0;
        generateSolutionList();

    }

    private void addToLists() {
        colorList.add("red");
        colorList.add("blue");
        colorList.add("green");
        colorList.add("orange");
        colorList.add("purple");
        colorList.add("yellow");

        shapeList.add("Square");
        shapeList.add("Triangle");
        shapeList.add("Circle");
        shapeList.add("Moon");
        shapeList.add("Star");
        shapeList.add("Sun");

        bColorList.add("grey");
        bColorList.add("black");
        bColorList.add("white");
    }

    private void generateSolutionList() {

        for (int i = 1; i < 8; i ++) {
            for (int j = i+1; j < 9; j++) {
                for (int t = j+1; t < 10; t++) {
                    if (isMatch(i,j,t)) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(i);
                        temp.add(j);
                        temp.add(t);
                        solutionList.add(temp);
                    }
                }
            }
        }

        System.out.println("Solution List Created");
        for (List l: solutionList) {
            System.out.println(l);
        }
    }

    private boolean isMatch(int i, int j, int t) {
        List<Integer> one = pictureListForCalc.get(i-1);
        List<Integer> two = pictureListForCalc.get(j-1);
        List<Integer> three = pictureListForCalc.get(t-1);

        return isMatchHelper(one.get(0),two.get(0),three.get(0))
                && isMatchHelper(one.get(1),two.get(1),three.get(1))
                && isMatchHelper(one.get(2),two.get(2),three.get(2));
    }

    private boolean isMatchHelper(int i, int j, int t) {
        return (i == j && j == t) | (i != j && j != t && i != t);
    }


    void printPictures() {
        for (Picture p: pictureList) {
            System.out.println("Shape: " + p.shape + "     ShapeColor: " + p.shapeColor
                    + "     BackGround Color: " + p.bColor);
        }
    }
}
