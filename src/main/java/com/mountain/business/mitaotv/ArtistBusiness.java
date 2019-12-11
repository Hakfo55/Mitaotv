package com.mountain.business.mitaotv;

import com.mountain.business.CrudBusiness;
import com.mountain.common.exception.ArtistException;
import com.mountain.common.exception.ServiceException;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.Artist;
import com.mountain.vo.ArtistVo;
import org.springframework.data.domain.Page;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/18:17
 * @Description:
 */
public interface ArtistBusiness extends CrudBusiness<Artist> {

    /**
     * 通过id查找艺人
     * @return
     */
    ArtistVo findById(Long id);

    /**
     * 根据状态分页查询列表
     *
     * @return
     */
    PageResult findListByStatus(Integer page, Integer size, String status);

    /**
     * 新增
     * @param artist
     * @param portraitUrl
     * @return
     */
    ArtistVo insert(Artist artist, String portraitUrl) throws ArtistException;


    /**
     * 更新
     *
     * @param artist
     * @return
     */
    ArtistVo update(Artist artist, String portraitUrl) throws ArtistException;

    /**
     * 逻辑删除艺人以及艺人写真图片
     * @param id
     */
    void deleteArtist(Long id) throws ServiceException;

}
