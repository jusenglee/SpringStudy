package fighting.spirit.springstudy.entity;

import javax.persistence.*;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    private LocalDateTime createDate;

    @ManyToOne
    private QuestionEntity questionEntity;

    @ManyToOne
    private SiteUserEntity author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<SiteUserEntity> voter;
}
