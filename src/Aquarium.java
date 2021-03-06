import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

@SuppressWarnings("UnusedAssignment")
class Aquarium {
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd(HH-mm)";

    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    static void makeFolder() {
        File dir = new File("output");
        dir.mkdir();
    }

    public static void main(String[] args) {
        makeFolder();
        int numberOfFish = 50, randomAge, randomType, randomColor;
        String fishType;
        String fileName;
        char fishColor;
        boolean fishAlive;
        PrintWriter print = null;
        fileName = now() + "output.txt";
        Fish[] tank = new Fish[numberOfFish];
        int[] ages = new int[numberOfFish];
        String[] types = new String[numberOfFish];

        for (int i = 0; i < numberOfFish; i++) {
            randomType = (int) (Math.random() * 5) + 1;
            if (randomType == 1) {
                fishType = "minnow";
            } else if (randomType == 2) {
                fishType = "bass";
            } else if (randomType == 3) {
                fishType = "gold";
            } else if (randomType == 4) {
                fishType = "beta";
            } else {
                fishType = "pike";
            }
            randomAge = (int) (Math.random() * 10);
            fishAlive = (Math.random() < 0.5);
            randomColor = (int) (Math.random() * 6) + 1;
            if (randomColor == 1) {
                fishColor = 'r';
            } else if (randomColor == 2) {
                fishColor = 'b';
            } else if (randomColor == 3) {
                fishColor = 'p';
            } else if (randomColor == 4) {
                fishColor = 'y';
            } else if (randomColor == 5) {
                fishColor = 'o';
            } else {
                fishColor = 'g';
            }
            tank[i] = new Fish(fishType, randomAge, fishAlive, fishColor);
            ages[i] = randomAge;
            types[i] = fishType;
        }

        try {
            print = new PrintWriter(new BufferedWriter(new FileWriter("output/" + fileName, true)));
        } catch (IOException iox) {
            System.out.println("Problem writing " + fileName);
        }

        print.println("#Fish Population in Order of Generation#");
        print.println("Fish Type" + "\t\t" + "Fish Age" + "\t\t" + "Fish Alive?" + "\t\t" + "Fish Color");
        print.println("=========" + "\t\t" + "========" + "\t\t" + "===========" + "\t\t" + "==========");
        for (int i = 0; i < numberOfFish; i++)
            print.println(tank[i].getType() + "\t\t\t" + tank[i].getAge() + "\t\t\t\t" + tank[i].isAlive() + "\t\t\t" + tank[i].getColor());

        print.println("==========================================================");
        Arrays.sort(tank);

        print.println("#Fish Population in Order of Type#");
        print.println("Fish Type" + "\t\t" + "Fish Age" + "\t\t" + "Fish Alive?" + "\t\t" + "Fish Color");
        print.println("=========" + "\t\t" + "========" + "\t\t" + "===========" + "\t\t" + "==========");
        for (int i = 0; i < numberOfFish; i++)
            if (tank[i].getType().equals("minnow"))
                print.println(tank[i].getType() + "\t\t\t" + tank[i].getAge() + "\t\t\t\t" + tank[i].isAlive() + "\t\t\t" + tank[i].getColor());
        for (int i = 0; i < numberOfFish; i++)
            if (tank[i].getType().equals("bass"))
                print.println(tank[i].getType() + "\t\t\t" + tank[i].getAge() + "\t\t\t\t" + tank[i].isAlive() + "\t\t\t" + tank[i].getColor());
        for (int i = 0; i < numberOfFish; i++)
            if (tank[i].getType().equals("gold"))
                print.println(tank[i].getType() + "\t\t\t" + tank[i].getAge() + "\t\t\t\t" + tank[i].isAlive() + "\t\t\t" + tank[i].getColor());
        for (int i = 0; i < numberOfFish; i++)
            if (tank[i].getType().equals("beta"))
                print.println(tank[i].getType() + "\t\t\t" + tank[i].getAge() + "\t\t\t\t" + tank[i].isAlive() + "\t\t\t" + tank[i].getColor());
        for (int i = 0; i < numberOfFish; i++)
            if (tank[i].getType().equals("pike"))
                print.println(tank[i].getType() + "\t\t\t" + tank[i].getAge() + "\t\t\t\t" + tank[i].isAlive() + "\t\t\t" + tank[i].getColor());

        print.println("==========================================================");
        Arrays.sort(tank);

        print.println("#Fish Population in Order of Age#");
        print.println("Fish Type" + "\t\t" + "Fish Age" + "\t\t" + "Fish Alive?" + "\t\t" + "Fish Color");
        print.println("=========" + "\t\t" + "========" + "\t\t" + "===========" + "\t\t" + "==========");
        for (int i = 0; i < numberOfFish; i++) {
            print.println(tank[i].getType() + "\t\t\t" + tank[i].getAge() + "\t\t\t\t" + tank[i].isAlive() + "\t\t\t" + tank[i].getColor());
        }

        print.println("==========================================================");
        print.println("#Statistics#");
        int ageSum = 0;
        for (int d : ages) ageSum += d;
        double averageAge = 1.0d * ageSum / ages.length;
        print.println("Average Population Age: " + averageAge);
        int numOfMinnows = 0;
        int numOfBass = 0;
        int numOfGoldfish = 0;
        int numOfBetas = 0;
        int numOfPike = 0;
        for (String d : types) {
            if (d.equals("minnow")) numOfMinnows += 1;
            if (d.equals("bass")) numOfBass += 1;
            if (d.equals("gold")) numOfGoldfish += 1;
            if (d.equals("beta")) numOfBetas += 1;
            if (d.equals("pike")) numOfPike += 1;
        }
        print.println("Number of Minnows: " + numOfMinnows);
        print.println("Number of Bass: " + numOfBass);
        print.println("Number of Goldfish: " + numOfGoldfish);
        print.println("Number of Betas: " + numOfBetas);
        print.println("Number of Pike: " + numOfPike);
        print.close();
    }
}

class Fish implements Comparable<Fish> {
    private String type;
    private int age;
    private boolean alive;
    private char color;

    public Fish(String type, int age, boolean alive, char color) {
        setType(type);
        setAge(age);
        setAlive(alive);
        setColor(color);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public String getType() {
        return this.type;
    }

    public int getAge() {
        return this.age;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public char getColor() {
        return this.color;
    }

    public int compareTo(Fish compareFish) {

        int compareAge = ((Fish) compareFish).getAge();
        return this.age - compareAge;

    }
}