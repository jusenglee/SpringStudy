package fighting.spirit.springstudy.controller;

import fighting.spirit.springstudy.entity.QuestionEntity;
import fighting.spirit.springstudy.form.SearchForm;
import fighting.spirit.springstudy.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fighting.spirit.springstudy.service.UserService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/** @PreAuthorize("isAuthenticated()") **/
@RequiredArgsConstructor
@Controller
public class SearchController {
    private final QuestionService questionService;
    private final UserService userService;
    @RequestMapping(value = "/search")
    public String searchAjax(SearchForm form) {
        System.out.println("academic : " + form.getAcademic());
        System.out.println("career : " + form.getCareer());
        System.out.println("area : " + form.getArea());
        // 이후 DB연결 로직 추가
        return "search_box";

    }
    @RequestMapping("/user")
    public String GetUser(){

        return "user";
    }
    @RequestMapping("/searchAjax")
    public String SearchAjax(){

        return "searchAjax";
    }
    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
        Page<QuestionEntity> paging = this.questionService.getList(page);
        model.addAttribute("paging",paging);
        return "tables";
    }


}
