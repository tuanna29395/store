package com.anhtuan.store.model;

import com.anhtuan.store.commons.constants.Commons;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "discount")
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer percent;
    @DateTimeFormat(pattern = Commons.DATE_PATTERN)
    private Date startAt;
    @DateTimeFormat(pattern = Commons.DATE_PATTERN)
    private Date endAt;

    private String description;

    @UpdateTimestamp
    private Date updatedAt;

    @CreationTimestamp
    private Date createdAt;

    private Integer deleteFlag;
}
