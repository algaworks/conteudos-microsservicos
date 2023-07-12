package com.algaworks.example.secure;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final List<Product> products = new ArrayList<>();

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public List<Product> list() {
        return products;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Product create(@RequestBody @Valid Product product,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
//        UserDetails userDetails = (UserDetails)
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        product.setId(UUID.randomUUID());
        product.setCreatedByUser(userDetails.getUsername());
        products.add(product);

        return product;
    }

}
