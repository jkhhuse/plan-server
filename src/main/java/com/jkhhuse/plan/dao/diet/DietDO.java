package com.jkhhuse.plan.dao.diet;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dietTime;

    @Column(name="phe_value", nullable = false)
    private String pheValue;

    @Column(name="diet_type", nullable = false)
    private String dietType;

    @Column(name="diet_content", nullable = false)
    private String dietContent;
}