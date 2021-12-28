package com.jkhhuse.plan.entity.food;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "food")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FoodDO implements Serializable {

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    private String uuid;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="protein", nullable = false)
    private String protein;

    @Column(name="phe", nullable = false)
    @NotNull
    private String phe;

    @Column(name="rule", nullable = false)
    @NotNull
    private String rule;

    @Column(name="type", nullable = false)
    @NotNull
    private String type;
}