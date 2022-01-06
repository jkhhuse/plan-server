package com.jkhhuse.plan.entity.statistics;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "statistics")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDO {

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    private String uuid;

    @Column(name="person_uuid", nullable = false)
    @NotNull
    private String personUuid;

    @Column(name="measure_time", nullable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull
    private Date measureTime;

    @Column(name="days", nullable = false)
    @NotNull
    private Integer days;

    @Column(name="special_protein", nullable = false)
    @NotNull
    private Float specialProtein;

    @Column(name="special_protein_ml", nullable = false)
    @NotNull
    private Float specialProteinMl;

    @Column(name="nature_protein", nullable = false)
    @NotNull
    private Float natureProtein;

    @Column(name="total_protein", nullable = false)
    @NotNull
    private Float totalProtein;

    @Column(name="phe_value", nullable = false)
    @NotNull
    private Float pheValue;

}
