package com.kefelan.messaging.kefelanmicroservicesnote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kefelan.messaging.kefelanmicroservicesnote.model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

	public List<Note> findByUserId(Integer userId);
	
}
