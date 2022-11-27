package service;

import model.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question Question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
