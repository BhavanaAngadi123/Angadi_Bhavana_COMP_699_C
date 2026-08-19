package com.happytails.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "lost_pets")
@Getter @Setter @NoArgsConstructor
public class LostPet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String name;
    private String species;
    private String breed;
    private String color;
    @Column(name = "last_seen_location") private String lastSeenLocation;
    @Column(name = "last_seen_at") private LocalDateTime lastSeenAt;
    @Column(columnDefinition = "TEXT") private String description;
    private String image;
    private String status = "missing";
    @ManyToOne(optional = false) @JoinColumn(name = "owner_id") private User owner;
    @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();
}
