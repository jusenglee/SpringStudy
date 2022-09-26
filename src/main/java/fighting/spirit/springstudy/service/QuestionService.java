package fighting.spirit.springstudy.service;

import fighting.spirit.springstudy.DataNotFoundException;
import fighting.spirit.springstudy.entity.QuestionEntity;
import fighting.spirit.springstudy.entity.SiteUserEntity;
import fighting.spirit.springstudy.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

        private final QuestionRepository questionRepository;

    public Page<QuestionEntity> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10,Sort.by((sorts)));
        return this.questionRepository.findAll(pageable);
    }

    public QuestionEntity getQuestionEntity(Integer id){
        Optional<QuestionEntity> questionEntity = this.questionRepository.findById(id);
        if(questionEntity.isPresent()){
            return questionEntity.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
    public void create(String subject, String content, SiteUserEntity userEntity){
        QuestionEntity q = new QuestionEntity();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(userEntity);
        this.questionRepository.save(q);
    }

    public void modify(QuestionEntity questionEntity, String subject, String content){
        questionEntity.setSubject(subject);
        questionEntity.setContent(content);
        questionEntity.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(questionEntity);
    }

    public void vote(QuestionEntity questionEntity, SiteUserEntity siteUserEntity) {
        questionEntity.getVoter().add(siteUserEntity);
        this.questionRepository.save(questionEntity);
    }
}
