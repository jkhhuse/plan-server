package com.jkhhuse.plan.entity.corporeality;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "corporeality")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CorporealityDO {
    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    private String uuid;

    @Column(name="person_uuid", nullable = false)
    private String personUuid;

    @Column(name="record_time", nullable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date recordTime;

    @Column(name="weight")
    private Float weight;

    @Column(name="height")
    private Float height;

}
