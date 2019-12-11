package com.mountain.business.mitaotv;

import com.mountain.business.CrudBusiness;
import com.mountain.common.exception.ArtistException;
import com.mountain.po.mitaotv.ArtistPortrait;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/11:38
 * @Description:
 */
public interface ArtistPortraitBusiness extends CrudBusiness<ArtistPortrait> {

    /**
     * 根据状态分页查询艺人写真列表
     * @return
     */
    Page<ArtistPortrait> findByArtistIdAndStatus(Integer page, Integer size, Long artistId, String status);


    /**
     * 保存
     * @param artistId
     * @param portraitUrl
     * @return
     */
    List<ArtistPortrait> save(Long artistId, String portraitUrl,String operation) throws ArtistException;

    /**
     * 通过艺人id和状态查找艺人写真图片列表
     * @param artistId
     * @param status
     * @return
     */
    List<ArtistPortrait> findByArtistIdAndStatus(Long artistId, String status);

    /**
     * 通过艺人id逻辑删除艺人写真图片
     * @param artistId
     */
    void deleteByArtistId(Long artistId);

}
