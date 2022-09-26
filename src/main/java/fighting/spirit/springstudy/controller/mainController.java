package fighting.spirit.springstudy.controller;

import fighting.spirit.springstudy.entity.ResponseDTO;
import fighting.spirit.springstudy.entity.SearchForm;
import fighting.spirit.springstudy.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
public class mainController {

    @Autowired
    private SearchService SearchService;
    private SearchForm SearchForm = new SearchForm();

    @RequestMapping("/")
    public String redirect(@ModelAttribute("form") SearchForm Form, Model model) {

        return "redirect:/bs4_forum"; //리다이렉트를 통하여 mainHome 컨트롤러로 연결
    }
    @GetMapping("/bs4_forum")
    public String mainHome(SearchForm form, Model model) { // 뷰에서 데이터를 받아오기위해 빈 폼을 모델로 뷰에 던짐
        model.addAttribute("form", new SearchForm());
        return "search_box2";
    }



    @PostMapping("/bs4_forum")
    public String SearchMain(@ModelAttribute("form") SearchForm form, Model model) {// ModelAttribute를 통하여 데이터 받아오기
        Logger log = LoggerFactory.getLogger("com.example.demo.LogTest");
        log.info("Form.keyword = {}", form.getKeyword());// 받은 데이터 로그띄워서 체크
        log.info("Form.AreaList = {}", form.getAreaList());
        log.info("Form.CareerList = {}", form.getCareerList());
        List<ResponseDTO> All = SearchService.All(form);//All 서비스 실행 및 검색 자료를 ResponseDTO에 담음
        model.addAttribute("All", All);//만든 All을 모델에 담아 뷰로 전달
        SearchForm.setKeyword(form.getKeyword());//받았던 사용자 검색 체크 옵션을 다른 컨트롤러에 전달하기 위해 폼에 세팅
        SearchForm.setAreaList(form.getAreaList());
        SearchForm.setCareerList(form.getCareerList());
        SearchForm.setAcademicList(form.getAcademicList());
        return "bs4_forum_All";//호출시 띄울 html
   }


    @RequestMapping("/bs4_forum/jobkorea")
    public String jobkorea(Model model) {
        Logger log = LoggerFactory.getLogger("com.example.demo.LogTest");
        log.info("jobkorea.keyword = {}", SearchForm.getKeyword());//SearchMain에서 세팅해준 폼의 데이터 확인
        log.info("jobkorea.AreaList = {}", SearchForm.getAreaList());
        log.info("jobkorea.CareerList = {}", SearchForm.getCareerList());
        log.info("jobkorea.AcademicList = {}", SearchForm.getAcademicList());
        List<ResponseDTO> All = SearchService.jobkorea(SearchForm);//jobkorea 서비스 실행 및 검색 자료를 ResponseDTO에 담음
        model.addAttribute("All", All);//만든 All을 모델에 담아 뷰로 전달
        return "bs4_forum_jobkorea";
    }

    @GetMapping("/bs4_forum/saramin")
    public String saramin(Model model) {
        Logger log = LoggerFactory.getLogger("com.example.demo.LogTest");
        log.info("saramin.keyword = {}", SearchForm.getKeyword());
        log.info("saramin.AreaList = {}", SearchForm.getAreaList());
        log.info("saramin.CareerList = {}", SearchForm.getCareerList());
        List<ResponseDTO> All = SearchService.saramin(SearchForm);
        model.addAttribute("All", All);
        return "bs4_forum_saramin";
    }
    @RequestMapping("/bs4_forum/career")
    public String career(Model model) {
        Logger log = LoggerFactory.getLogger("com.example.demo.LogTest");
        log.info("career.keyword = {}", SearchForm.getKeyword());
        log.info("career.AreaList = {}", SearchForm.getAreaList());
        log.info("career.CareerList = {}", SearchForm.getCareerList());
        List<ResponseDTO> All = SearchService.career(SearchForm);
        model.addAttribute("All", All);
        return "jobkorea_career";
    }





    @ModelAttribute("area")//모델에 데이터를 맵에 매핑
    private Map<String, String> SearchArea() {//체크박스 출력용 맵
        Map<String, String> map = new LinkedHashMap<>();
        map.put("서울", "서울");
        map.put("경기", "경기");
        map.put("부산", "부산");
        map.put("대구", "대구");
        map.put("인천", "인천");
        map.put("대전", "대전");
        map.put("광주", "광주");
        map.put("충청", "충청");
        map.put("영남", "영남");
        map.put("제주", "제주");
        map.put("강원", "강원");
        map.put("호남", "호남");
        return map;
    }

    @ModelAttribute("career")//모델에 데이터를 맵에 매핑
    private Map<String, String> SearchCareer() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("신입", "신입");
        map.put("경력", "경력");
        map.put("신입·경력", "신입·경력");
        return map;
    }

    @ModelAttribute("academic")//모델에 데이터를 맵에 매핑
    private Map<String, String> Searchacademic() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("대졸", "대졸");
        map.put("초대졸", "초대졸");
        map.put("학력무관", "학력무관");
        return map;
    }
}