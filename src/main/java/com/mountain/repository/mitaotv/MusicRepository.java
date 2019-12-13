package com.mountain.repository.mitaotv;

import com.mountain.po.mitaotv.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/15:06
 * @Description:
 */
public interface MusicRepository extends JpaRepository<Music,Long> {
    Page<Music> findByStatus(Pageable pageable, String status);

    @Query(value = "select singer from mitaotv_manage_musics where status = :status group by singer order by count(singer) desc ",nativeQuery = true)
    Page<String> singerList(Pageable pageable,String status);

    Page<Music> findBySingerAndStatus(Pageable pageable, String singer, String status);
}
