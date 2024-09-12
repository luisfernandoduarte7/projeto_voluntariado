package com.projeto.voluntariado.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.voluntariado.entity.Organizador;

public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {
	
	Optional<Organizador> findByEmail(String email);
}
