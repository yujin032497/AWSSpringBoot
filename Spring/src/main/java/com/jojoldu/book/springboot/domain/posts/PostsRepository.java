package com.jojoldu.book.springboot.domain.posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 보통 ibatis나 Mybatis 등에서 Dao라고 불리는 DB Layer 접근자
// 기본적인 CRUD 메소드가 자동으로 생성
// Entity 클래스와 기본 Entity Repository는 함께 위치해야하며 기본 Repository 없이는 제대로 역할 수행 불가
// 규모가 커질 경우 domain에서 함께 관리함
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
