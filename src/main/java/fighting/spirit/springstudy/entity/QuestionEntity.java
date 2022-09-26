package fighting.spirit.springstudy.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class QuestionEntity {
    @Id //고유 번호 id 속성에 적용한 @Id 애너테이션은 id 속성을 기본 키로 지정한다.
    // 기본 키로 지정하면 이제 id 속성의 값은 데이터베이스에
    // 저장할 때 동일한 값으로 저장할 수 없다. 고유 번호를
    // 기본 키로 한 이유는 고유 번호는 엔티티에서
    // 각 데이터를 구분하는 유효한 값으로 중복되면 안 되기 때문이다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "questionEntity", cascade = CascadeType.REMOVE)
    private List<AnswerEntity> answerEntityList;

    @ManyToOne
    private SiteUserEntity author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<SiteUserEntity> voter;
}
