package com.happytails.controller;

import com.happytails.model.*;
import com.happytails.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PlatformApiController {
    private final EntityManager em;
    private final UserRepository users;

    public PlatformApiController(EntityManager em, UserRepository users) {
        this.em = em;
        this.users = users;
    }

    @GetMapping("/sitters")
    public List<Map<String, Object>> sitters() {
        return em.createQuery("select s from Sitter s where s.verificationStatus = 'approved'", Sitter.class)
                .getResultList().stream().map(s -> Map.<String, Object>of(
                        "id", s.getId(), "name", s.getName(), "services", s.getServiceTypes() == null ? "" : s.getServiceTypes(),
                        "status", s.getVerificationStatus())).toList();
    }

    @GetMapping("/products")
    public List<Map<String, Object>> products() {
        return em.createQuery("select p from Product p order by p.createdAt desc", Product.class)
                .getResultList().stream().map(p -> Map.<String, Object>of(
                        "id", p.getId(), "name", p.getName(), "price", p.getPrice(), "stock", p.getStock(),
                        "seller", p.getSeller().getName())).toList();
    }

    @PostMapping("/products")
    @Transactional
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request, Authentication auth) {
        User seller = currentUser(auth);
        Product product = new Product();
        product.setName(request.name()); product.setDescription(request.description());
        product.setPrice(request.price()); product.setStock(request.stock()); product.setSeller(seller);
        em.persist(product);
        return ResponseEntity.ok(Map.of("id", product.getId(), "message", "Product created"));
    }

    @PostMapping("/orders")
    @Transactional
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request, Authentication auth) {
        User buyer = currentUser(auth);
        Product product = em.find(Product.class, request.productId());
        if (product == null) return ResponseEntity.notFound().build();
        if (request.quantity() < 1 || product.getStock() < request.quantity())
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid quantity or insufficient stock"));
        Order order = new Order();
        order.setProduct(product); order.setBuyer(buyer); order.setQuantity(request.quantity());
        order.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(request.quantity())));
        order.setStatus("pending"); order.setPaymentMethod(request.paymentMethod());
        product.setStock(product.getStock() - request.quantity());
        product.setSalesCount(product.getSalesCount() + request.quantity());
        em.persist(order);
        return ResponseEntity.ok(Map.of("id", order.getId(), "total", order.getTotalPrice()));
    }

    @GetMapping("/lost-pets")
    public List<Map<String, Object>> lostPets() {
        return em.createQuery("select l from LostPet l where l.status = 'missing' order by l.createdAt desc", LostPet.class)
                .getResultList().stream().map(l -> Map.<String, Object>of(
                        "id", l.getId(), "name", l.getName(), "species", l.getSpecies() == null ? "" : l.getSpecies(),
                        "lastSeenLocation", l.getLastSeenLocation() == null ? "" : l.getLastSeenLocation(), "status", l.getStatus())).toList();
    }

    @PostMapping("/lost-pets")
    @Transactional
    public ResponseEntity<?> reportLostPet(@RequestBody LostPetRequest request, Authentication auth) {
        LostPet lost = new LostPet();
        lost.setName(request.name()); lost.setSpecies(request.species()); lost.setBreed(request.breed());
        lost.setColor(request.color()); lost.setLastSeenLocation(request.lastSeenLocation());
        lost.setLastSeenAt(request.lastSeenAt()); lost.setDescription(request.description()); lost.setOwner(currentUser(auth));
        em.persist(lost);
        return ResponseEntity.ok(Map.of("id", lost.getId(), "message", "Lost pet report created"));
    }

    @PostMapping("/lost-pets/{id}/sightings")
    @Transactional
    public ResponseEntity<?> reportSighting(@PathVariable Long id, @RequestBody SightingRequest request, Authentication auth) {
        LostPet lost = em.find(LostPet.class, id);
        if (lost == null) return ResponseEntity.notFound().build();
        Sighting sighting = new Sighting();
        sighting.setLostPet(lost); sighting.setReporter(currentUser(auth)); sighting.setLocation(request.location());
        sighting.setSeenAt(request.seenAt()); sighting.setNotes(request.notes());
        em.persist(sighting);
        return ResponseEntity.ok(Map.of("id", sighting.getId(), "message", "Sighting submitted"));
    }

    @PostMapping("/bookings")
    @Transactional
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request, Authentication auth) {
        User owner = currentUser(auth);
        Pet pet = em.find(Pet.class, request.petId());
        Sitter sitter = em.find(Sitter.class, request.sitterId());
        if (pet == null || sitter == null) return ResponseEntity.notFound().build();
        if (!pet.getOwner().getId().equals(owner.getId()))
            return ResponseEntity.status(403).body(Map.of("error", "Pet does not belong to current user"));
        Booking booking = new Booking();
        booking.setPet(pet); booking.setSitter(sitter); booking.setStartDate(request.startDate()); booking.setEndDate(request.endDate());
        em.persist(booking);
        return ResponseEntity.ok(Map.of("id", booking.getId(), "status", booking.getStatus()));
    }

    private User currentUser(Authentication auth) {
        return users.findByEmail(auth.getName()).orElseThrow();
    }

    public record ProductRequest(String name, String description, BigDecimal price, Integer stock) {}
    public record OrderRequest(Long productId, Integer quantity, String paymentMethod) {}
    public record LostPetRequest(String name, String species, String breed, String color, String lastSeenLocation, LocalDateTime lastSeenAt, String description) {}
    public record SightingRequest(String location, LocalDateTime seenAt, String notes) {}
    public record BookingRequest(Long petId, Long sitterId, LocalDateTime startDate, LocalDateTime endDate) {}
}
