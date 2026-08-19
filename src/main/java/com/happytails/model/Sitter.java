package com.happytails.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "sitters")
@Getter @Setter @NoArgsConstructor
public class Sitter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100) private String name;
    @Column(nullable = false, unique = true, length = 100) private String email;
    private String phone = "Not Provided";
    @Column(name = "password_hash") private String passwordHash;
    @Column(name = "service_types", columnDefinition = "TEXT") private String serviceTypes;
    @Column(name = "verification_status") private String verificationStatus = "pending";
    @Column(name = "profile_image") private String profileImage = "default-avatar.png";
    @Column(name = "id_document") private String idDocument;
    @Column(name = "selfie_with_id") private String selfieWithId;
    @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updated_at") private LocalDateTime updatedAt = LocalDateTime.now();
}
