package com.project.peps.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.peps.tag.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
