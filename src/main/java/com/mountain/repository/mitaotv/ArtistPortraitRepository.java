package com.mountain.repository.mitaotv;

import com.mountain.po.mitaotv.ArtistPortrait;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/11:20
 * @Description:
 */
public interface ArtistPortraitRepository extends JpaRepository<ArtistPortrait,Long> {
    Page<ArtistPortrait> findByArtistIdAndStatus(Pageable pageable, Long artistId, String status);

    List<ArtistPortrait> findByArtistIdAndStatus(Long artistId, String status);

    List<ArtistPortrait> findByArtistId(Long artistId);

    void deleteByArtistId(Long artistId);
}
