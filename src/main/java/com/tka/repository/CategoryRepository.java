package com.tka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tka.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}