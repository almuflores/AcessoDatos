package com.example.proyecto.Model;
import javax.persistence.*;

@Entity
@Table(name = "imagenes")

public class Imagen {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String nombre;
	
	@Column
	private String imagenID;
	
	@Column 
	private String imagen_url;
	
	public Imagen(String nombre, String imagenID, String imagen_url) {
		
		this.nombre = nombre;
		this.imagenID = imagenID;
		this.imagen_url = imagen_url;
	}
	
	public Imagen() {
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImagenID() {
		return imagenID;
	}
	public void setImagenID(String imagenID) {
		this.imagenID = imagenID;
	}
	public String  getImagen_url() {
		return imagen_url;
	}
	public void setImagen_url(String imagenUrl) {
		this.imagen_url = imagenUrl;
	}
	
	
}
