package com.ssafy.perfumee.repository.perfume;

import com.ssafy.perfumee.model.entity.perfume.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    Optional<Note> findByNo(Integer noteNo);
    Optional<Note> findByName(String name);
    @Query(value = "SELECT n FROM Note n Where n.name LIKE :name")
    Optional<Note> findByNameLike(String name);
}
