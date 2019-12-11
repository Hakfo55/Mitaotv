package com.mountain.po.mitaotv;

import com.mountain.po.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/13:58
 * @Description:
 */
@Entity
@Table(name = "mitaotv_manage_partner")
@Data
@ApiModel
public class Partner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //名字
    @NotEmpty(message = "合作伙伴名字不能为空")
    @ApiModelProperty(value = "合作伙伴名称")
    private String name;
    //图片链接
    @NotEmpty(message = "图片链接不能为空")
    @ApiModelProperty(value = "图片链接")
    private String imageUrl;
    //排序字段，权重
    @ApiModelProperty(value = "排序字段，正序")
    private Integer priority;

}
