package com.example.crawlingex.Repository;

import com.example.crawlingex.Entity.Product;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public interface productRepository extends JpaRepository<Product, Integer> {
}
