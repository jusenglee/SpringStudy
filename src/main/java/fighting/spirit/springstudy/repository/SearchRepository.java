package fighting.spirit.springstudy.repository;


import fighting.spirit.springstudy.entity.Entity;
import fighting.spirit.springstudy.entity.SearchForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Entity> findAll() {
        String jpql = "Select m from crawl_master m";
        TypedQuery<Entity> query = em.createQuery(jpql, Entity.class);
        return query.getResultList();
    }


    public List<Entity> jobkorea(SearchForm SearchForm) {
        String jobkorea = "jobkorea";
        String jpql1 = "SELECT m FROM crawl_master m WHERE m.homepage = :jobkorea";

        //회원 이름 검색
        if (StringUtils.hasText(SearchForm.getKeyword())) {//키워드 검색1
            jpql1 += " AND m.title like '%' || :Keyword || '%'  ";
        }
        if (SearchForm.getAreaList().size() > 0) {//지역검색 2
            jpql1 += " AND m.area in :AreaList";
        }
        if (SearchForm.getCareerList().size() > 0) { // 경력 검색 3
            jpql1 += " AND m.career in :CareerList";
        }
        if (SearchForm.getAcademicList().size() > 0) { // 학력검색 4
            jpql1 += " AND m.academic IN :AcademicList";
        }
        TypedQuery<Entity> query = em.createQuery(jpql1, Entity.class)
                .setMaxResults(1000);//최대 1000건
        query = query.setParameter("jobkorea", jobkorea);

        if (StringUtils.hasText(SearchForm.getKeyword())) {
            query = query.setParameter("Keyword", SearchForm.getKeyword());
        }
        if (SearchForm.getAreaList().size() > 0) {
            query = query.setParameter("AreaList", SearchForm.getAreaList());
        }
        if (SearchForm.getCareerList().size() > 0) {
            query = query.setParameter("CareerList", SearchForm.getCareerList());
        }
        if (SearchForm.getAcademicList().size() > 0) {
            query = query.setParameter("AcademicList", SearchForm.getAcademicList());
        }
        return query.getResultList();
    }


    public List<Entity> saramin(SearchForm Form) {
        String saramin = "saramin";
        String jpql2 = "SELECT m FROM crawl_master m WHERE m.homepage = :saramin";

        //회원 이름 검색
        if (StringUtils.hasText(Form.getKeyword())) {//키워드 검색1
            jpql2 += " AND m.title like '%' || :Keyword || '%'  ";
        }
        if (Form.getAreaList().size() > 0) {//지역검색 2
            jpql2 += " AND m.area in :AreaList";
        }
        if (Form.getCareerList().size() > 0) { // 경력 검색 3
            jpql2 += " AND m.career in :CareerList";
        }
        if (Form.getAcademicList().size() > 0) { // 학력검색 4
            jpql2 += " AND m.academic IN :AcademicList";
        }
        TypedQuery<Entity> query = em.createQuery(jpql2, Entity.class)
                .setMaxResults(1000);//최대 1000건
        query = query.setParameter("saramin", saramin);

        if (StringUtils.hasText(Form.getKeyword())) {
            query = query.setParameter("Keyword", Form.getKeyword());
        }
        if (Form.getAreaList().size() > 0) {
            query = query.setParameter("AreaList", Form.getAreaList());
        }
        if (Form.getCareerList().size() > 0) {
            query = query.setParameter("CareerList", Form.getCareerList());
        }
        if (Form.getAcademicList().size() > 0) {
            query = query.setParameter("AcademicList", Form.getAcademicList());
        }
        return query.getResultList();
    }


    public List<Entity> career(SearchForm Form) {
        String career = "career";
        String jpql3 = "SELECT m FROM crawl_master m where m.homepage = :career";

        //회원 이름 검색
        if (StringUtils.hasText(Form.getKeyword())) {//키워드 검색1
            jpql3 += " and";
            jpql3 += " m.title like '%' || :Keyword || '%'  ";
        }
        if (Form.getAreaList().size() > 0) {//지역검색 2
            jpql3 += " and";
            jpql3 += " m.area in :AreaList";
        }
        if (Form.getCareerList().size() > 0) { // 경력 검색 3
            jpql3 += " and";
            jpql3 += " m.career in :CareerList";
        }
        if (Form.getAcademicList().size() > 0) { // 학력검색 4
            jpql3 += " AND";

            jpql3 += " m.academic IN :AcademicList";
        }
        TypedQuery<Entity> query = em.createQuery(jpql3, Entity.class)
                .setMaxResults(1000);//최대 1000건
        query = query.setParameter("career", career);

        if (StringUtils.hasText(Form.getKeyword())) {
            query = query.setParameter("Keyword", Form.getKeyword());
        }
        if (Form.getAreaList().size() > 0) {
            query = query.setParameter("AreaList", Form.getAreaList());
        }
        if (Form.getCareerList().size() > 0) {
            query = query.setParameter("CareerList", Form.getCareerList());
        }
        if (Form.getAcademicList().size() > 0) {
            query = query.setParameter("AcademicList", Form.getAcademicList());
        }
        return query.getResultList();
    }


    public List<Entity> All(SearchForm Form) {
        String jpql = "SELECT m FROM crawl_master m ";
        boolean isFirstCondition = true;
        //회원 이름 검색
        if (StringUtils.hasText(Form.getKeyword())) {//키워드 검색1
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.title like '%' || :Keyword || '%' or m.etc like '%' || :Keyword || '%' ";
        }
        if (Form.getAreaList().size() > 0) {//지역검색 2
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.area in :AreaList";
        }
        if (Form.getCareerList().size() > 0) { // 경력 검색 3
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.career in :CareerList";
        }
        if (Form.getAcademicList().size() > 0) { // 학력검색 4
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.academic IN :AcademicList";
        }
        TypedQuery<Entity> query = em.createQuery(jpql, Entity.class)
                .setMaxResults(1000);//최대 1000건
        if (StringUtils.hasText(Form.getKeyword())) {
            query = query.setParameter("Keyword", Form.getKeyword());
        }
        if (Form.getAreaList().size() > 0) {
            query = query.setParameter("AreaList", Form.getAreaList());
        }
        if (Form.getCareerList().size() > 0) {
            query = query.setParameter("CareerList", Form.getCareerList());
        }
        if (Form.getAcademicList().size() > 0) {
            query = query.setParameter("AcademicList", Form.getAcademicList());
        }
        return query.getResultList();
    }
}



