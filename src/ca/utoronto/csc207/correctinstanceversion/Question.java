package ca.utoronto.csc207.correctinstanceversion;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by colin on 2016-11-10.
 */
public class Question {
    public final String question;

    public final Set<String> answers = new HashSet<>();

    public Question(String question) {
        this.question = question;
    }

    public boolean isAnswer(String answer) {
        System.out.println("Answers: " + this.answers + ", " + answers.contains(answer));
        //Ignore case
        return this.answers.contains(answer.toLowerCase());
    }

    public void addAnswer(String answer) {
        //Ignore case
        this.answers.add(answer.toLowerCase());
    }
}
