package ca.utoronto.csc207.correctinstanceversion;

import java.util.HashSet;
import java.util.Set;

public class Question {
    public final String question;

    public final Set<String> answers;

    /**
     * You must use the builder instead of the constructor.
     */
    private Question(String question, Set<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    public boolean isAnswer(String answer) {
        //Ignore case
        return this.answers.contains(answer.toLowerCase());
    }

    public void addAnswer(String answer) {
        //Ignore case
        this.answers.add(answer.toLowerCase());
    }

    public static class Builder {
        private final String question;
        private final Set<String> answers = new HashSet<>();
        public Builder(String question) {
            this.question = question;
        }

        public Builder addAnswer(String answer) {
            this.answers.add(answer);
            return this;
        }

        public Question build() {
            if(this.answers.isEmpty()) {
                throw new IllegalStateException("You can't build a question with no answers!");
            }
            return new Question(question, answers);
        }
    }
}
