package com.jkhhuse.plan.entity.density;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "density")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DensityDO implements Serializable {

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    private String uuid;

    @Column(name="person_uuid", nullable = false)
    private String personUuid;

    @Column(name="measure_uuid", nullable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date measureTime;

    @Column(name="measure_value", nullable = false)
    private Float measure_value;
}