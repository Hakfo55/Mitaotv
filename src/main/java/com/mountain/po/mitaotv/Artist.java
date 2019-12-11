package com.mountain.po.mitaotv;

import com.mountain.po.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/11:42
 * @Description:
 */
@Entity
@Table(name = "mitaotv_manage_artists")
@Data
@ApiModel
public class Artist extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //姓名
    @NotEmpty(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名")
    private String name;
    //别称
    @ApiModelProperty(value = "昵称/别名")
    private String nickname;
    //性别 1表示男生 0表示女生
    @ApiModelProperty(value = "性别，1为男生，0表示女生")
    private Integer sex;
    //抖音id
    @NotEmpty(message = "抖音id不能为空")
    @ApiModelProperty(value = "抖音id号")
    private String tiktokId;
    //简介
    @ApiModelProperty(value = "简介")
    private String synopsis;
    //大封面图
    @ApiModelProperty(value = "大封面图链接")
    private String bigImageUrl;
    //小封面图
    @ApiModelProperty(value = "小封面图链接")
    private String smallImageUrl;
    //排序字段，权重
    @ApiModelProperty(value = "排序字段，正序")
    private Integer priority;

    //演艺经历
    @ApiModelProperty(value = "演艺经历")
    private String perform;
    //广告经历
    @ApiModelProperty(value = "广告经历")
    private String advert;
}
