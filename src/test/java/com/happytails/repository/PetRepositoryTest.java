package com.happytails.repository;

import com.happytails.model.Pet;
import com.happytails.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PetRepositoryTest {

    @Autowired private UserRepository users;
    @Autowired private PetRepository pets;

    @Test
    void returnsPetsForOwnerNewestFirst() {
        User owner = new User();
        owner.setName("Owner");
        owner.setEmail("petowner@example.com");
        owner.setPasswordHash("encoded");
        owner.setRole("owner");
        owner = users.save(owner);

        Pet older = pet("Max", owner, LocalDateTime.now().minusDays(1));
        Pet newer = pet("Luna", owner, LocalDateTime.now());
        pets.save(older);
        pets.save(newer);

        var result = pets.findByOwnerEmailOrderByCreatedAtDesc(owner.getEmail());

        assertThat(result).extracting(Pet::getName).containsExactly("Luna", "Max");
    }

    private Pet pet(String name, User owner, LocalDateTime createdAt) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setSpecies(Pet.Species.Dog);
        pet.setAge(3);
        pet.setOwner(owner);
        pet.setCreatedAt(createdAt);
        return pet;
    }
}
