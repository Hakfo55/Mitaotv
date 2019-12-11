package com.mountain.business.mitaotv;

import com.mountain.business.CrudBusiness;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.Video;
import org.springframework.data.domain.Page;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/16:10
 * @Description:
 */
public interface VideoBusiness extends CrudBusiness<Video> {

    /**
     * 根据状态分页查询视频列表
     * @return
     */
    PageResult findListByStatus(Integer page, Integer size, String status);


    /**
     * 更新
     * @param video
     * @return
     */
    Video update(Video video);


    /**
     * 增加播放量
     * @param id
     */
    void increasePlayNum(Long id);

}
