package com.jkhhuse.plan.entity.specialmilk;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "special_milk")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SpecialMilkDO implements Serializable {

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    private String uuid;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="type", nullable = false)
    private Integer type;

    @Column(name="protein", nullable = false)
    private Float protein;

    @Column(name="content", nullable = true)
    private String content;

}