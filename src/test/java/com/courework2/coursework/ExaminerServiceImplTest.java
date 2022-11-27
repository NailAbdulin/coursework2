package com.courework2.coursework;

import exception.NotEnoughQuestionsException;
import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import service.impl.JavaQuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class ExaminerServiceImplTest  {
    @Mock
    private JavaQuestionService javaQuestionService;
@InjectMocks
    private ExaminerServiceImplTest examinerService;

    private final List<Question> javaQuestions = new ArrayList<>();

    @BeforeEach
    public void beforeEach() {
        javaQuestions.clear();

        javaQuestions.addAll(
                Stream.of(
                        new Question("Вопрос по Java - 1", "Ответ по Java - 1"),
                        new Question("Вопрос по Java - 2", "Ответ по Java - 2"),
                        new Question("Вопрос по Java - 3", "Ответ по Java - 3")
                ).collect(Collectors.toSet())
        );
        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
    }

    @Test
        public void getQuestionsNegativeTest() {
            assertThatExceptionOfType(NotEnoughQuestionsException.class)
                    .isThrownBy(() -> examinerService.getQuestions(-1));
        }

    @Test
    public void getQuestionsPositiveTest() {
        when(javaQuestionService.getRandomQuestion()).thenReturn(
                new Question("Вопрос по Java - 1", "Ответ по Java - 1"),
                new Question("Вопрос по Java - 1", "Ответ по Java - 1"),
                new Question("Вопрос по Java - 1", "Ответ по Java - 1"),
                new Question("Вопрос по Java - 3", "Ответ по Java - 3")
        );

        assertThat(examinerService.getQuestions(3))
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Вопрос по Java - 1", "Ответ по Java - 1"),
                        new Question("Вопрос по Java - 3", "Ответ по Java - 3")
                );

    }
}
