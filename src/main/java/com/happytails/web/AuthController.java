package com.happytails.web;

import com.happytails.model.User;
import com.happytails.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    String login() { return "login"; }

    @GetMapping("/register")
    String registerForm(Model model) {
        model.addAttribute("registration", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    String register(@Valid @ModelAttribute("registration") RegistrationForm form,
                    BindingResult result) {
        if (users.existsByEmail(form.getEmail())) {
            result.rejectValue("email", "duplicate", "An account already exists with this email.");
        }
        if (result.hasErrors()) return "register";

        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail().trim().toLowerCase());
        user.setPasswordHash(passwordEncoder.encode(form.getPassword()));
        user.setRole("owner");
        users.save(user);
        return "redirect:/login?registered";
    }

    @lombok.Getter @lombok.Setter
    public static class RegistrationForm {
        @jakarta.validation.constraints.NotBlank private String name;
        @jakarta.validation.constraints.Email @jakarta.validation.constraints.NotBlank private String email;
        @jakarta.validation.constraints.Size(min = 8, message = "Password must be at least 8 characters") private String password;
    }
}
