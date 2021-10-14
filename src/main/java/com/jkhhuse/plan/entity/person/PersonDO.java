package com.jkhhuse.plan.entity.person;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "person")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonDO implements Serializable {

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    private String uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;

    @Column(nullable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date born_time;

    @Column(nullable = false)
    private Float origin;

    @Column(nullable = false)
    private String addr;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String paaswd;
}