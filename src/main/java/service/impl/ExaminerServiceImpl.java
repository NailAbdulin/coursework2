package service.impl;

import exception.NotEnoughQuestionsException;
import model.Question;
import org.springframework.stereotype.Service;
import service.ExaminerService;
import service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(JavaQuestionService questionService) {
        this.questionService = questionService;}

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> questions = questionService.getAll();
        if (amount > questions.size() || amount < 1) {
            throw new NotEnoughQuestionsException();
        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount){
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }

}