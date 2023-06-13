package com.example.travel.daos;

import com.example.travel.base.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location extends AbstractAuditingEntity {
    @NotBlank
    @Nationalized
    private String name;

    @Nationalized
    private String address;

    @Nationalized
    @Length(max = 100000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "location")
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "location")
    @JsonIgnore
    private List<Image> images;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "location")
    @JsonIgnore
    private List<Hotel> hotels;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "location")
    @JsonIgnore
    private List<Video> videos;

}
