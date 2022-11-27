package service.impl;

import exception.QuestionAlreadyExistException;
import exception.QuestionNotFoundException;
import model.Question;
import org.springframework.stereotype.Service;
import service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class JavaQuestionService implements QuestionService {
    private final Random random;
    private final Set<Question> questions;

    public JavaQuestionService() {
        this.random = new Random();
        this.questions = new HashSet<>();
    }
    @Override
    public Question add(String question,
                        String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question){
        if (!questions.add(question)) {
            throw new QuestionAlreadyExistException();
        }
        return question;
    }
    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {

        return new HashSet<>(questions);
    }
    @Override
    public Question getRandomQuestion() {
        if (questions.size() == 0) {
            return null;
        }
        return questions.stream().skip(random.nextInt(questions.size()))
                .findAny()
                .orElse(null);
    }

}
