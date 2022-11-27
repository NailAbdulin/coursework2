package com.courework2.coursework;

import exception.QuestionAlreadyExistException;
import exception.QuestionNotFoundException;
import model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import service.impl.JavaQuestionService;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    private final JavaQuestionService javaQuestionService = new JavaQuestionService();

    @Test
    public void add1Test() {
        javaQuestionService.add(new Question("test","test"));

        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> javaQuestionService.add(new Question("test","test")));
        assertThat(javaQuestionService.getAll()).isEqualToComparingOnlyGivenFields(new Question("test", "test"));
    }

    @Test
    public void add2Test() {
        String question = "test";
        String answer = "test";
        Question q = new Question(question, answer);
        javaQuestionService.add(question, answer);

        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> javaQuestionService.add(question, answer));
        assertThat(javaQuestionService.getAll()).isEqualToComparingOnlyGivenFields(q);
    }

    @Test
    public void removeTest() {
        javaQuestionService.add(new Question("test", "test"));
        javaQuestionService.remove(new Question("test", "test"));

        assertThatExceptionOfType(QuestionNotFoundException.class).isThrownBy(() -> javaQuestionService.remove(new Question("test", "test")));
    }

    @ParameterizedTest
    @MethodSource("questions")
    public void getRandomQuestionTest(Set<Question> questions) {
        questions.forEach(javaQuestionService ::add);

        assertThat(javaQuestionService.getRandomQuestion()).isIn(javaQuestionService.getAll());
    }

    public static Stream<Arguments> questions() {
        return Stream.of(
                Arguments.of(
                        Set.of(
                                new Question ("Question1", "Answer1"),
                                new Question ("Question2", "Answer2"),
                                new Question ("Question3", "Answer3")
                        )
                )
        );
    }
}
