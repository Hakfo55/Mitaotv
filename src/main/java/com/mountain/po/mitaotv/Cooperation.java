package com.mountain.po.mitaotv;

import com.mountain.po.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/05/16:46
 * @Description:
 */
@Entity
@Data
@Table(name = "mitaotv_manage_cooperation")
@ApiModel
public class Cooperation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //图片链接
    @NotEmpty(message = "图片链接不能为空")
    @ApiModelProperty(value = "图片链接")
    private String imageUrl;

    //排序字段
    @ApiModelProperty(value = "排序字段，正序")
    private Integer priority;

    //设备信息
    @NotNull(message = "设备信息不能为空")
    @ApiModelProperty(value = "设备，1表示pc端，2表示手机端")
    private Integer device;
}
