package ca.utoronto.csc207.incorrectstaticversion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * This is an intentionally poorly written text based game.
 *
 * It is poorly written in that it is hard to extend, and it forces you to copy-paste a lot of code
 * to add new functionality.
 *
 * What changes would you make to this class in order to promote code reuse and
 * allow the game to be used in other uses [e.g. GameExampleReadFile?]
 *
 */
public class GameExample {
    private static final Map<String, Set<String>> answers = new LinkedHashMap<>();
    /**
     * Score goes up as you correctly answer questions
     */
    static int score = 0;

    static void addAnswer(String question, String answer) {
        if(!answers.containsKey(question)) {
            answers.put(question, new HashSet<>());
        }
        answers.get(question).add(answer);
    }

    /**
     * A static block executes the code within it whenever the class is loaded.
     * It is generally a sign of bad practice if you ever have to do this, so consider wisely when you do so
     * [what happens if addAnswer threw an unchecked exception here?]
     */
    static {
        addAnswer("What is 2+2?", "4");
        addAnswer("What is 2+2?", "a number");
        addAnswer("What is Colin's favorite color?", "blue");
        //PROBLEM 1: What happens if you have 10 answers and then realize you have a typo in the question?
        //how many lines do you have to edit?
        addAnswer("What is the air speed velocity of an unladen swallow?", "african or european?");
        addAnswer("What is the air speed velocity of an unladen swallow?", "african or european");
        addAnswer("What is the air speed velocity of an unladen swallow?", "what do you mean? is it an african or european swallow?");
        addAnswer("What gets wetter as it dries?", "towel");
        addAnswer("What gets wetter as it dries?", "towels");
        addAnswer("What gets wetter as it dries?", "a towel");
    }

    public static void promptForInput() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //NOTE 1 Map.Entry<String, Set<String>> is pretty gross.
            for (Map.Entry<String, Set<String>> questionAnswerPairs : answers.entrySet()) {
                boolean correctAnswer = false;
                while(!correctAnswer) {
                    System.out.print(questionAnswerPairs.getKey());
                    System.out.print(" ");
                    String answer = br.readLine();
                    //PROBLEM 2: If you look at this line with no context, what does it do?
                    //it isn't very obvious
                    if (!questionAnswerPairs.getValue().contains(answer.toLowerCase())) {
                        System.out.println("Sorry! That answer is incorrect.");
                        continue;
                    }
                    System.out.println("That is correct!");
                    correctAnswer = true;
                    score += 1;
                }
            }
            System.out.println("You know colin!");
            System.out.println("Score: " + score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void run() {
        promptForInput();
    }

    /**
     * Consider the scenario where you'd want to have "levels" of this game,
     * where each level had progressively harder and harder questions loaded
     * from text files.
     *
     * How would you modify this class and method to allow that to happen?
     */
    public static void main(String[] args) {
        //PROBLEM 3: What happens when you want to add a GUI to both games
        //and then run both?
        //Because the variables are shared across all games [static],
        //the total correct answer score could be more than the number of questions!
        GameExample.run();
    }
}
