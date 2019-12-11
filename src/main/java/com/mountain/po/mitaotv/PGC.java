package com.mountain.po.mitaotv;

import com.mountain.po.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/11:54
 * @Description:
 */
@Entity
@Table(name = "mitaotv_manage_pgc")
@Data
@ApiModel
public class PGC extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //标题
    @ApiModelProperty(value = "主标题")
    private String title;

    //副标题
    @ApiModelProperty(value = "副标题")
    private String subtitle;

    //图片链接
    @NotEmpty(message = "图片链接不能为空")
    @ApiModelProperty(value = "图片链接")
    private String imageUrl;

    //排序字段
    @ApiModelProperty(value = "排序字段，正序")
    private Integer priority;
}
