package com.project.peps.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.peps.menu.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByUserId(Long userId);
}
