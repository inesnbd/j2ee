package fr.gamedev.question.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * UserAnswer representation with easy access to data.
 * More info on projections : https://www.baeldung.com/spring-data-rest-projections-excerpts.
 * Documentation of projections :
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections.interfaces.
 * @author djer13
 */
@Projection(name = "askedQuestion", types = { UserAnswer.class })
public interface UserAnswerAsAskedQuestion {
    @Value("#{target.id}")
    long getId();

    Integer getPoints();

    @Value("#{target.answer.question}")
    Question getQuestion();

}
