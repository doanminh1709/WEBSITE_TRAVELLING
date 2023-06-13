package com.example.travel.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "active_flag")
    private Boolean activeFlag = Boolean.TRUE;

    @Column(name = "delete_flag")
    private Boolean deleteFlag = Boolean.FALSE;

    @CreationTimestamp
    @Column(name = "created_date")
    private Timestamp createdDate; //thời gian tạo

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate; //thời gian sửa cuối cùng

}