package com.mountain.vo;

import com.mountain.po.BaseEntity;
import com.mountain.po.mitaotv.NewsImageList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/11:54
 * @Description:
 */
@Data
public class NewsVO extends BaseEntity {
    private Long id;

    //标题
    private String title;
    //简介
    private String synopsis;
    //内容
    private String content;
    //封面图
    private String coverImageUrl;
    //大图
    private String bigImageUrl;
    //排序字段，权重
    private Integer priority;

    List<NewsImageList> imageLists;
}
