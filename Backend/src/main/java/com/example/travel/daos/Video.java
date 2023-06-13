package com.example.travel.daos;

import com.example.travel.base.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "videos")
public class Video extends AbstractAuditingEntity {
    @NotBlank
    private String linkVideo;

    @Column(name = "public_id")
    private String publicId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;
}
