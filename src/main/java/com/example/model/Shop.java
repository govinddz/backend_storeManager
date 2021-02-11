package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shop")
public class Shop extends BaseEntity {

    public enum ShopCategory {
        General_Store,
        Mall,
        Supermarket,
        Medical_Store
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "shopname")
    @NotNull
    private String shopName;
    @Column(name = "ownername")
    @NotNull
    private String ownerName;
    @Column(name = "category")
    @NotNull
    @Enumerated(EnumType.STRING)
    private ShopCategory category;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = EAGER, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

}
