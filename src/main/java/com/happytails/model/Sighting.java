package com.happytails.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "sightings")
@Getter @Setter @NoArgsConstructor
public class Sighting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "lost_pet_id") private LostPet lostPet;
    @ManyToOne @JoinColumn(name = "reporter_id") private User reporter;
    @Column(nullable = false) private String location;
    @Column(name = "seen_at") private LocalDateTime seenAt;
    @Column(columnDefinition = "TEXT") private String notes;
    private String image;
    @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();
}
