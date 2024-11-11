package naero.naeroserver.question.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class QuestionController {

    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

//    private final QuestionService questionService;
//
//    @Autowired
//    public QuestionController(QuestionService questionService) {
//        this.questionService = questionService;
//    }
}
