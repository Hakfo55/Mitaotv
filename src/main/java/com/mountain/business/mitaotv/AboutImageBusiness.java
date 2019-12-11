package com.mountain.business.mitaotv;

import com.mountain.business.CrudBusiness;
import com.mountain.common.exception.AboutException;
import com.mountain.common.exception.ArtistException;
import com.mountain.po.mitaotv.AboutImagelist;
import com.mountain.po.mitaotv.ArtistPortrait;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/11:38
 * @Description:
 */
public interface AboutImageBusiness extends CrudBusiness<AboutImagelist> {

    /**
     * 根据状态分页查询艺人写真列表
     * @return
     */
    Page<AboutImagelist> findByAboutIdAndStatus(Integer page, Integer size, Long aboutId, String status);


    /**
     * 新增或修改
     * @param aboutId
     * @param imageUrl
     * @param operation
     * @return
     * @throws AboutException
     */
    List<AboutImagelist> save(Long aboutId, String imageUrl ,String operation) throws AboutException;

    /**
     * 通过id和状态查找图片列表
     * @param aboutId
     * @param status
     * @return
     */
    List<AboutImagelist> findByAboutIdAndStatus(Long aboutId, String status);

    /**
     * 通过id逻辑删除关联的图片
     * @param aboutId
     */
    void deleteByAboutId(Long aboutId);

}
