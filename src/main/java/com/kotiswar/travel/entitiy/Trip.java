package com.kotiswar.travel.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "trips")
@Getter
@Setter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @OneToMany(mappedBy = "trip")
    private Set<Feedback> feedbacks;

}

