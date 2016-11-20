package ca.utoronto.csc207.correctinstanceversion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * This is the "correct" version of the static example.
 */
public class GameExample {
    //private static final Map<String, Set<String>> answers = new LinkedHashMap<>();
    private final List<Question> questions = new ArrayList<>();
    private int score = 0;

    private void loadDefaultQuestions() {
        //to make this less verbose, you can use the "Builder Design Pattern"
        //if you bother Colin enough, he will show you an implementation of that.
        //SOLUTION TO PROBLEM 1:  Now you only have to change the question on this one line
        //instead of each once for each answer before
        questions.add(new QuestionBuilder("What is 2+2?")
        .addAnswer("4")
        .addAnswer("four")
        .addAnswer("a number")
        .build());
        questions.add(new QuestionBuilder("What is Colin's favorite color?")
        .addAnswer("blue"));
        questions.add(new QuestionBuilder("What is the air speed velocity of an unladen swallow?")
        .addAnswer("african or european?");
        .addAnswer("african or european");
        .addAnswer("what do you mean? is it an african or european swallow?"));
        questions.add(new QuestionBuilder("What gets wetter as it dries?")
        .addAnswer("towel");
        .addAnswer("towels");
        .addAnswer("a towel"));
    }

    private void promptForInput() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //NOTE 1 "Map.Entry<String, Set<String>> is pretty gross."
            //Now we are clearly looping over questions, much less confusing.
            for (Question q : questions) {
                boolean correctAnswer = false;
                while(!correctAnswer) {
                    System.out.print(q.question);
                    System.out.print(" ");
                    String answer = br.readLine();
                    //SOLUTION TO PROBLEM 2: Now, this line can be understood
                    //without context.  You are checking if "answer" is an answer
                    //to your question.
                    if (!q.isAnswer(answer)) {
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

    public void run() {
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
        //SOLUTION TO PROBLEM 3: What happens when you want to add a GUI to both games
        //and then run both?
        //Because we are using instance variables, we'll be able to get the individual score
        //for each GameExample by simply doing
        //game1.score, game2.score, ...
        GameExample game1 = new GameExample();
        game1.loadDefaultQuestions();
        game1.run();
    }
}
