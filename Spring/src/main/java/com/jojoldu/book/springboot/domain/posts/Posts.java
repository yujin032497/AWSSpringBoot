package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // 클래스 내 모든 필드의 Getter 메소드 추가
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // 테이블과 링크될 클래스
        // Entity에는 절대 Setter 메서드를 만들지 않음( 차후 기능 변경 시 정말 복잡해짐)
public class Posts extends BaseTimeEntity {
    
    @Id // PK
        // PK는 Long타입의 Auto_increment 권장(Mysql: bigint)
        // 주민등록번호, 복하비 등은 유니크 키로 별도로 추가하는 것을 추천
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙
    private Long id;
    
    @Column(length = 500, nullable = false) // 컬럼(varchar를 255가 기본값인데 500으로 늘리고 싶을 경우 length 사용)
    private String title;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    
    private String author;
    
    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
            // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
