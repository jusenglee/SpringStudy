package fighting.spirit.springstudy.controller;

import java.security.Principal;

import fighting.spirit.springstudy.entity.SiteUserEntity;
import fighting.spirit.springstudy.form.AnswerForm;
import fighting.spirit.springstudy.form.QuestionForm;
import fighting.spirit.springstudy.entity.QuestionEntity;
import fighting.spirit.springstudy.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import fighting.spirit.springstudy.service.UserService;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequestMapping("/questionEntity")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
        Page<QuestionEntity> paging = this.questionService.getList(page);
        model.addAttribute("paging",paging);
        return "questionEntity_list";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        QuestionEntity questionEntity = this.questionService.getQuestionEntity(id);
        model.addAttribute("questionEntity", questionEntity);
        return "questionEntity_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "questionEntity_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()){
            return "questionEntity_form";
        }
        SiteUserEntity siteUserEntity = this.userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUserEntity);
        return "redirect:/questionEntity/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal){
        QuestionEntity questionEntity = this.questionService.getQuestionEntity(id);
        if(!questionEntity.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setSubject(questionEntity.getSubject());
        questionForm.setContent(questionEntity.getContent());
        return "questionEntity_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id){
        if (bindingResult.hasErrors()){
            return "questionEntity_form";
        }
        QuestionEntity questionEntity = this.questionService.getQuestionEntity(id);
        if (!questionEntity.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.questionService.modify(questionEntity, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/questionEntity/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String questionVote(Principal principal, @PathVariable("id") Integer id) {
        QuestionEntity questionEntity = this.questionService.getQuestionEntity(id);
        SiteUserEntity siteUserEntity = this.userService.getUser(principal.getName());
        this.questionService.vote(questionEntity, siteUserEntity);
        return String.format("redirect:/questionEntity/detail/%s", id);
    }
}

