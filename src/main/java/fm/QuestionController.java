package fm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/ask")
    public Question askQuestion(@RequestParam String asker, @RequestParam String askedTo, @RequestBody String text) {
        return questionService.askQuestion(asker, askedTo, text);
    }

    @PostMapping("/answer")
    public void answerQuestion(@RequestParam int questionId, @RequestBody String answer) {
        questionService.answerQuestion(questionId, answer);
    }
}
