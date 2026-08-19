package com.happytails.web;

import com.happytails.model.Pet;
import com.happytails.model.User;
import com.happytails.repository.PetRepository;
import com.happytails.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {
    private final PetRepository pets;
    private final UserRepository users;

    @GetMapping
    String list(Authentication auth, Model model) {
        model.addAttribute("pets", pets.findByOwnerEmailOrderByCreatedAtDesc(auth.getName()));
        return "pets/list";
    }

    @GetMapping("/new")
    String createForm(Model model) {
        model.addAttribute("pet", new Pet());
        model.addAttribute("species", Pet.Species.values());
        return "pets/form";
    }

    @PostMapping
    String create(@Valid @ModelAttribute("pet") Pet pet, BindingResult result,
                  Authentication auth, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("species", Pet.Species.values());
            return "pets/form";
        }
        User owner = users.findByEmail(auth.getName()).orElseThrow();
        pet.setId(null);
        pet.setOwner(owner);
        pets.save(pet);
        return "redirect:/pets";
    }

    @PostMapping("/{id}/delete")
    String delete(@PathVariable Long id, Authentication auth) {
        Pet pet = pets.findById(id).orElseThrow();
        if (!pet.getOwner().getEmail().equalsIgnoreCase(auth.getName())) {
            throw new org.springframework.security.access.AccessDeniedException("Not your pet");
        }
        pets.delete(pet);
        return "redirect:/pets";
    }
}
