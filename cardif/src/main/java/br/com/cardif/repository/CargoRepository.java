package br.com.cardif.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cardif.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer>{

}
