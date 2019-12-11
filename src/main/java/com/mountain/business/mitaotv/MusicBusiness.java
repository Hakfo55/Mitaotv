package com.mountain.business.mitaotv;

import com.mountain.business.CrudBusiness;
import com.mountain.common.util.response.PageResult;
import com.mountain.po.mitaotv.Music;
import org.springframework.data.domain.Page;


/**
 * @Author: 陈宇健
 * @Date: 2019/12/04/15:06
 * @Description:
 */
public interface MusicBusiness extends CrudBusiness<Music> {


    /**
     * 根据状态分页查询艺人列表
     * @return
     */
    PageResult findListByStatus(Integer page, Integer size, String status);

    /**
     * 更新
     * @param music
     * @return
     */
    Music update(Music music);

}
