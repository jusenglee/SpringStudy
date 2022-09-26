package fighting.spirit.springstudy.service;

import fighting.spirit.springstudy.entity.Entity;
import fighting.spirit.springstudy.entity.ResponseDTO;
import fighting.spirit.springstudy.entity.SearchForm;
import fighting.spirit.springstudy.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {
    @Autowired
    private SearchRepository SearchRepository;

    /*private  testRepository testRepository;    //@Autowired 가 작동 안 할 경우
    /@Autowired
    private ResponseDTO responseDTO;
    public DbService(testRepository testRepository,
                     DbRepository DbRepository)
        this.DbRepository = DbRepository;
        this.testRepository = testRepository;
    }*/

    @Transactional
    public List<ResponseDTO> jobkorea(SearchForm SearchForm) {//잡코리아 게시글 검색 서비스단
        Logger log = LoggerFactory.getLogger("com.example.demo.LogTest");
        log.info("Service_Form.keyword = {}", SearchForm.getKeyword()); // 컨트롤러에서 체크박스 폼 값 전달받았는지 체크
        log.info("Service_Form.AreaList = {}", SearchForm.getAreaList());
        log.info("Service_Form.CareerList = {}", SearchForm.getCareerList());
        log.info("Service_Form.AcademicList = {}", SearchForm.getAcademicList());

        List<Entity> boards = SearchRepository.jobkorea(SearchForm);//엔티티 boards 선언 후 레포지토리 쿼리문 입력
        List<ResponseDTO> boardDtoList = new ArrayList<>();

        for (Entity Entity : boards) { //엔티티에서 DTO에 데이터 전달
            ResponseDTO dto = ResponseDTO.builder()
                    .ID(Entity.getID())
                    .homepage(Entity.getHomepage())
                    .title(Entity.getTitle())
                    .area(Entity.getArea())
                    .etc((Entity.getEtc()))
                    .url(Entity.getUrl())
                    .company(Entity.getCompany())
                    .build();

            boardDtoList.add(dto);
        }
        return boardDtoList;
    }
    @Transactional
    public List<ResponseDTO> saramin(SearchForm Form) { //사람인 게시글 검색 서비스단
        Logger log = LoggerFactory.getLogger("com.example.demo.LogTest");
        log.info("Service_Form.keyword = {}", Form.getKeyword());
        log.info("Service_Form.AreaList = {}", Form.getAreaList());
        log.info("Service_Form.CareerList = {}", Form.getCareerList());
        log.info("Service_Form.AcademicList = {}", Form.getAcademicList());

        List<Entity> boards = SearchRepository.saramin(Form);
        List<ResponseDTO> boardDtoList = new ArrayList<>();

        for (Entity Entity : boards) {
            ResponseDTO dto = ResponseDTO.builder()
                    .ID(Entity.getID())
                    .homepage(Entity.getHomepage())
                    .title(Entity.getTitle())
                    .area(Entity.getArea())
                    .etc((Entity.getEtc()))
                    .url(Entity.getUrl())
                    .company(Entity.getCompany())
                    .build();

            boardDtoList.add(dto);
        }
        return boardDtoList;
    }

    @Transactional
    public List<ResponseDTO> career(SearchForm Form) {//커리어 게시글 검색 서비스단
        Logger log = LoggerFactory.getLogger("com.example.demo.LogTest");
        log.info("Service_Form.keyword = {}", Form.getKeyword());
        log.info("Service_Form.AreaList = {}", Form.getAreaList());
        log.info("Service_Form.CareerList = {}", Form.getCareerList());
        log.info("Service_Form.AcademicList = {}", Form.getAcademicList());

        List<Entity> boards = SearchRepository.career(Form);
        List<ResponseDTO> boardDtoList = new ArrayList<>();

        for (Entity Entity : boards) {
            ResponseDTO dto = ResponseDTO.builder()
                    .ID(Entity.getID())
                    .homepage(Entity.getHomepage())
                    .title(Entity.getTitle())
                    .area(Entity.getArea())
                    .etc((Entity.getEtc()))
                    .url(Entity.getUrl())
                    .company(Entity.getCompany())
                    .build();

            boardDtoList.add(dto);
        }
        return boardDtoList;
    }
    @Transactional
    public List<ResponseDTO> All(SearchForm Form) {
        Logger log = LoggerFactory.getLogger("com.example.demo.LogTest");
        log.info("Service_Form.keyword = {}", Form.getKeyword());
        log.info("Service_Form.AreaList = {}", Form.getAreaList());
        log.info("Service_Form.CareerList = {}", Form.getCareerList());
        log.info("Service_Form.AcademicList = {}", Form.getAcademicList());

        List<Entity> boards = SearchRepository.All(Form);
        List<ResponseDTO> boardDtoList = new ArrayList<>();

        for (Entity Entity : boards) {
            ResponseDTO dto = ResponseDTO.builder()
                    .ID(Entity.getID())
                    .homepage(Entity.getHomepage())
                    .title(Entity.getTitle())
                    .area(Entity.getArea())
                    .etc((Entity.getEtc()))
                    .company(Entity.getCompany())
                    .url(Entity.getUrl())
                    .build();

            boardDtoList.add(dto);
        }
        return boardDtoList;
    }
}



