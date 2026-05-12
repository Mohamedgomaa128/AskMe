package fm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public Question askQuestion(String askerName, String askedToName, String questionText) {
        Optional<User> asker = userRepository.findByUserName(askerName);
        Optional<User> askedTo = userRepository.findByUserName(askedToName);
        
        if (asker.isEmpty() || askedTo.isEmpty()) return null;

        Question q = new Question(questionText, asker.get(), askedTo.get());
        return questionRepository.save(q);
    }

    public void answerQuestion(int questionId, String answer) {
        Optional<Question> q = questionRepository.findById(questionId);
        if (q.isPresent()) {
            q.get().setAnswer(answer);
            questionRepository.save(q.get());
        }
    }

    public List<Question> getFeed() {
        return questionRepository.findByAnswerIsNotNull();
    }
}
