package fighting.spirit.springstudy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class SearchForm{  //체크박스 값 받아올 폼
    private String keyword;
    private List<String> areaList;
    private List<String> careerList;
    private List<String> academicList;
}
