package com.example.proyecto.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.jni.File;
import org.springframework.web.multipart.MultipartFile;

import com.example.proyecto.Model.Imagen;

public interface ImagenService {
	
	public Map<String, String> valuesMap= new HashMap<>();
	
	
	List<Imagen>listar();
	Imagen listarId(int id);
	void add(Imagen i);
	Imagen edit(Imagen i);
	Imagen delete(int id);

}
