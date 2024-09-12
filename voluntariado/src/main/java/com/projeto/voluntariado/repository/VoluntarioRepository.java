package com.projeto.voluntariado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.voluntariado.entity.Voluntario;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {

	Optional<Voluntario> findByEmail(String email);
	
}

