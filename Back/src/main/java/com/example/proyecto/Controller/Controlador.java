package com.example.proyecto.Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.Deflater;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.proyecto.Model.Imagen;
import com.example.proyecto.Repository.ImagenRepositorio;
import com.example.proyecto.Service.CloudinaryService;
import com.example.proyecto.Service.ImagenService;
import com.example.proyecto.Service.ImagenServiceImp;
import com.example.proyecto.dto.Mensaje;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/imagenes"})

public class Controlador {
	
	@Autowired
	ImagenServiceImp service;
	
	@Autowired
	CloudinaryService cloudinaryService;
	
	@Autowired
	ImagenRepositorio imgrepo;
	
	
	@GetMapping("/lista")
	public ResponseEntity<List<Imagen>> listar(){
		List<Imagen> list = service.listar();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(path = {"/add"})
	public ResponseEntity<?> add(@RequestParam MultipartFile multipartFile) throws IOException{
		BufferedImage bi=ImageIO.read(multipartFile.getInputStream());
		if(bi==null) {
			return new ResponseEntity(new Mensaje("imagen no valida"), HttpStatus.BAD_REQUEST);
		}
		Map result = cloudinaryService.add(multipartFile);
		
		Imagen imagen = 
				new Imagen((String)result.get("original_filename"), 
						(String)result.get("url"), 
						(String)result.get("public_id"));
		
		service.add(imagen);
		return new ResponseEntity(new Mensaje("imagen subida"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) throws IOException{
		if(!service.exist(id)) {
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		}
		Imagen imagen = service.getOne(id).get();
		
		Map result = cloudinaryService.delete(imagen.getImagenID());
		service.delete(id);
		return new ResponseEntity(new Mensaje("imagen eliminada"), HttpStatus.OK);
	}

	
	
	@GetMapping(path = {"/{id}"})
	public Imagen listarId(@PathVariable("id") int id) {
		return service.listarId(id);
	}
	
	
	@PutMapping(path= {"/update/{id}"})
	public Imagen editar(@RequestBody Imagen i, @PathVariable("id") int id) {
		i.setId(id);
		return service.edit(i);
	}
	
	
	
}
