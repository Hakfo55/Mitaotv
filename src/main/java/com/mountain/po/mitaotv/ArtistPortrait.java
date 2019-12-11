package com.mountain.po.mitaotv;

import com.mountain.po.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/11:53
 * @Description:
 */
@Entity
@Table(name = "mitaotv_manage_artists_portrait")
@Data
public class ArtistPortrait extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //艺人id
    private Long artistId;
    //艺人写真url
    private String portraitUrl;
}
