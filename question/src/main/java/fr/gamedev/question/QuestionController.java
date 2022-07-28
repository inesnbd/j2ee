package fr.gamedev.question;

import fr.gamedev.question.data.Question;
import fr.gamedev.question.data.Tag;
import fr.gamedev.question.data.User;
import fr.gamedev.question.data.UserAnswer;
import fr.gamedev.question.repository.QuestionRepository;
import fr.gamedev.question.repository.UserAnswerRepository;
import fr.gamedev.question.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author baha
 *
 */
@RestController
public class QuestionController {

    /**.
     * userRepository
     */
    @Autowired
    private UserRepository userRepository;
    /**.
     * questionRepository
     */
    @Autowired
    private QuestionRepository questionRepository;
    /**.
     * UserAnswerRepository
     */
    @Autowired
    private UserAnswerRepository userAnswerRepository;



    /**
     * @param userId
     * @return Response
     */
    @GetMapping("/question/next")
    public ResponseEntity answer(
            @RequestParam final long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User does not exist");
        }
        Optional<UserAnswer> waitingForAnswer = this.userAnswerRepository.findFirstUserAnswerByUserAndCorrectIsNull(user.get());
        AtomicReference<Optional<Question>> question = new AtomicReference<Optional<Question>>();
        waitingForAnswer.ifPresentOrElse(
                userAnswer -> question.set(Optional.ofNullable(userAnswer.getQuestion())),
                () -> question.set(getRandomQuestion(user)));
        if (question.get().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There are no questions matching the corresponding tags");
        }

        return ResponseEntity
                .accepted()
                .body(question);
    }

    private Optional<Question> getRandomQuestion(final Optional<User> user) {
        Set<Tag> tags = user.get().getTags();
        if (tags.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User has no tags");
        }
        Optional<Question> question = questionRepository.getRandomQuestion(tags);
        UserAnswer userAnswer = new UserAnswer();
        question.ifPresent(userAnswer::setQuestion);
        user.ifPresent(userAnswer::setUser);
        userAnswerRepository.save(userAnswer);
        return question;
    }

}
