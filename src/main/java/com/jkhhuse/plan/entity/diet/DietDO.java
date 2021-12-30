package com.jkhhuse.plan.entity.diet;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "diet")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DietDO implements Serializable {

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    private String uuid;

    @Column(name="person_uuid", nullable = false)
    private String personUuid;

    @Column(name="diet_time", nullable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dietTime;

    @Column(name="phe_value", nullable = false)
    private Float pheValue;

    @Column(name="diet_type", nullable = false)
    @NotNull
    private Integer dietType;

    @Column(name="diet_content", nullable = false)
    private String dietContent;

    @Column(name="special_milk", nullable = false)
    private Integer specialMilk;

    @Column(name="smilk_type", nullable = false)
    private Integer smilkType;

    @Column(name="breast_milk", nullable = false)
    private Integer breastMilk;

    @Column(name="food_amount", nullable = false)
    private Float foodAmount;

    @Column(name="food_uuid", nullable = false)
    private String foodUuid;
}