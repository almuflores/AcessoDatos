package com.example.proyecto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.proyecto.Model.Imagen;


public interface ImagenRepositorio extends JpaRepository<Imagen, Integer>{
	
	List<Imagen>findByOrderById();
	Imagen findById(int id);
	Imagen save(Imagen i);
	void delete(Imagen i);

}
