package com.example.proyecto.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.proyecto.Model.Imagen;
import com.example.proyecto.Repository.ImagenRepositorio;

@Service
public class ImagenServiceImp{
	@Autowired
	private ImagenRepositorio repositorio;
	
	
	public List<Imagen> listar() {
		
		return repositorio.findByOrderById();
	}

	public Imagen listarId(int id) {
		return repositorio.findById(id);
	}

	public void add(Imagen i) {
		repositorio.save(i);
		
	}

	public Imagen edit(Imagen i) {
		return repositorio.save(i);
	}

	public void delete(int id) {
		repositorio.deleteById(id);
	}
	
	public Optional <Imagen> getOne(int id){
		return Optional.of(repositorio.findById(id));
	}
	
	public boolean exist(int id) {
		return repositorio.existsById(id);
	}
	
	

}
