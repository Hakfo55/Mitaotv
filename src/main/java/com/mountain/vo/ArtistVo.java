package com.mountain.vo;

import com.mountain.po.BaseEntity;
import com.mountain.po.mitaotv.ArtistPortrait;
import lombok.Data;

import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/06/18:10
 * @Description:
 */
@Data
public class ArtistVo extends BaseEntity {
    private Long id;

    //姓名
    private String name;
    //别称
    private String nickname;
    //性别 1表示男生 0表示女生
    private Integer sex;
    //抖音id
    private String tiktokId;
    //简介
    private String synopsis;
    //大封面图
    private String bigImageUrl;
    //小封面图
    private String smallImageUrl;
    //排序字段，权重
    private Integer priority;
    //演艺经历
    private String perform;
    //广告经历
    private String advert;

    List<ArtistPortrait> artistPortraitList;

}
