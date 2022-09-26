package fighting.spirit.springstudy.controller;

import fighting.spirit.springstudy.entity.AnswerEntity;
import fighting.spirit.springstudy.entity.SiteUserEntity;
import fighting.spirit.springstudy.form.AnswerForm;
import fighting.spirit.springstudy.entity.QuestionEntity;
import fighting.spirit.springstudy.service.AnswerService;
import fighting.spirit.springstudy.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import fighting.spirit.springstudy.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/answerEntity")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal){
        QuestionEntity questionEntity = this.questionService.getQuestionEntity(id);
        SiteUserEntity siteUserEntity = this.userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("questionEntity", questionEntity);
            return "questionEntity_detail";
        }
        AnswerEntity answerEntity = this.answerService.create(questionEntity,
                answerForm.getContent(), siteUserEntity);
        return String.format("redirect:/questionEntity/detail/%s#answerEntity_%s",
                answerEntity.getQuestionEntity().getId(), answerEntity.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String answerVote(Principal principal, @PathVariable("id") Integer id) {
        AnswerEntity answerEntity = this.answerService.getAnswerEntity(id);
        SiteUserEntity siteUserEntity = this.userService.getUser(principal.getName());
        this.answerService.vote(answerEntity, siteUserEntity);
        return String.format("redirect:/questionEntity/detail/%s#answerEntity_%s", answerEntity.getQuestionEntity().getId(), answerEntity.getId());
    }
}
