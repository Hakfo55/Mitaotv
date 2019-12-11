package com.mountain.vo;

import com.mountain.po.BaseEntity;
import com.mountain.po.mitaotv.AboutImagelist;
import com.mountain.po.mitaotv.NewsImageList;
import lombok.Data;

import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/11:54
 * @Description:
 */
@Data
public class AboutVO extends BaseEntity {
    private Long id;

    //大图图片链接
    private String bigImageUrl;

    //排序字段
    private Integer priority;

    //设备信息
    private Integer device;


    List<AboutImagelist> imagelists;
}
