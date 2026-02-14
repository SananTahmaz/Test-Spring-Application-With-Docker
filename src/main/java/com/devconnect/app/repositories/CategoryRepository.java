package com.devconnect.app.repositories;

import com.devconnect.app.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    Optional<Category> findByName(String name);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Product p SET p.category = NULL WHERE p.category.id = :id")
    void clearCategoryFromProducts(@Param("id") Long id);
}
