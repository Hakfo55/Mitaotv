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
     * 分页查询歌曲列表
     * @return
     */
    PageResult findList(Integer page, Integer size, String status,String singer);

    /**
     * 更新
     * @param music
     * @return
     */
    Music update(Music music);


    /**
     * 查找歌手列表
     * @return
     */
    PageResult singerList(Integer page, Integer size,String status);

}
