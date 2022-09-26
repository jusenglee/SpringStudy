package fighting.spirit.springstudy.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "crawl_master")
@javax.persistence.Entity(name = "crawl_master")
public class Entity {
    @Id
    @GeneratedValue//데이터베이스 매칭시키는 엔터티
    @Column(name = "id")
    private  Long ID; //식별 id
    @Column(name = "homepage")
    private  String homepage; //사이트 종류
    @Column(name = "company")
    private  String company; //회사
    @Column(name = "title")
    private  String title; // 게시글 제목
    @Column(name = "career")
    private  String career; // 경력
    @Column(name = "academic")
    private  String academic; //학력
    @Column(name = "employment")
    private  String employment; //고용형태
    @Column(name = "area")
    private  String area; //지역
    @Column(name = "period")
    private  String period; // 기간
    @Column(name = "etc")
    private  String etc; //기타
    @Column(name = "url")
    private  String url;//링크


    @Builder
    public Entity(Long ID, String homepage, String company,
                  String title, String career,
                  String academic, String employment, String area,
                  String period, String etc, String url) {
        this.ID = ID;//식별 id

        this.homepage = homepage; //사이트 종류

        this.company = company; //회사

        this.title = title; // 게시글 제목

        this.career = career; // 경력

        this.academic = academic; //학력

        this.employment = employment; //고용형태

        this.area = area; //지역

        this.period = period; // 기간

        this.etc = etc;

        this.url = url;

    }
}

