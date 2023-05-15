package com.example.crawlingex.Entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "storeTitle")
    private String storeTitle;

    @Column(name = "storeName")
    private String storeName;

    @Column(name = "price")
    private String price;

    @Column(name = "imgUrl")
    private String imgUrl;

    @Column(name = "storeLink")
    private String storeLink;

    @Column(name = "searchName")
    private String searchName;
}