package com.example.projektpp.models;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import jakarta.persistence.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @Size(min = 2, max = 50)
    private String manufacturer;


    @NumberFormat(pattern = "#.00")
    @Min(0) @Max(1000)
    @NotNull
    private Float price;

    private boolean onPrescription;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;
}
