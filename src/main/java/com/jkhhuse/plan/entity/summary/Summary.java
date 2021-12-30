package com.jkhhuse.plan.entity.summary;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "summary")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Summary {

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    private String uuid;

    @Column(name="measure_time", nullable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotBlank
    private Date measureTime;

    @Column(name="tolerance", nullable = false)
    @NotNull
    private Float tolerance;

    @Column(name="phe_need", nullable = false)
    @NotNull
    private Float pheNeed;


    @Column(name="protein_need", nullable = false)
    @NotNull
    private Float proteinNeed;
}
