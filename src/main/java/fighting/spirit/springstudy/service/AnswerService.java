package fighting.spirit.springstudy.service;

import fighting.spirit.springstudy.DataNotFoundException;
import fighting.spirit.springstudy.entity.AnswerEntity;
import fighting.spirit.springstudy.entity.QuestionEntity;
import fighting.spirit.springstudy.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fighting.spirit.springstudy.entity.SiteUserEntity;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;


    public AnswerEntity create(QuestionEntity questionEntity, String content, SiteUserEntity author){
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setContent(content);
        answerEntity.setCreateDate(LocalDateTime.now());
        answerEntity.setQuestionEntity(questionEntity);
        answerEntity.setAuthor(author);
        this.answerRepository.save(answerEntity);
        return answerEntity;
    }

    public AnswerEntity getAnswerEntity(Integer id) {
        Optional<AnswerEntity> answerEntity = this.answerRepository.findById(id);
        if (answerEntity.isPresent()) {
            return answerEntity.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void vote(AnswerEntity answerEntity, SiteUserEntity siteUserEntity) {
        answerEntity.getVoter().add(siteUserEntity);
        this.answerRepository.save(answerEntity);
    }
}
