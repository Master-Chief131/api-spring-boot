package com.example.demo.service;

import com.example.demo.entity.EstadoCivil;
import com.example.demo.repository.EstadoCivilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio para obtener descripciones de estados civiles
 */
@Service
public class EstadoCivilService {
    
    @Autowired
    private EstadoCivilRepository estadoCivilRepository;
    
    // Cache en memoria para las descripciones
    private Map<String, String> cacheDescripciones = new HashMap<>();
    
    /**
     * Inicializar el cache al arrancar la aplicación
     */
    @PostConstruct
    public void inicializarCache() {
        cargarDescripciones();
    }
    
    /**
     * Cargar todas las descripciones desde la base de datos
     */
    public void cargarDescripciones() {
        try {
            List<EstadoCivil> estadosCiviles = estadoCivilRepository.findAll();
            cacheDescripciones.clear();
            
            for (EstadoCivil estadoCivil : estadosCiviles) {
                cacheDescripciones.put(estadoCivil.getCodigo(), estadoCivil.getDescripcion());
            }
            
            System.out.println("Cache de estados civiles cargado: " + cacheDescripciones.size() + " estados");
        } catch (Exception e) {
            System.err.println("Error al cargar cache de estados civiles: " + e.getMessage());
            // Si hay error, usar valores por defecto
            cargarValoresPorDefecto();
        }
    }
    
    /**
     * Cargar valores por defecto en caso de error de base de datos
     */
    private void cargarValoresPorDefecto() {
        cacheDescripciones.clear();
        cacheDescripciones.put("C", "Casado");
        cacheDescripciones.put("D", "Divorciado/a");
        cacheDescripciones.put("S", "Soltero/a");
        cacheDescripciones.put("U", "Unido");
        cacheDescripciones.put("V", "Viudo/a");
        cacheDescripciones.put("O", "Otro");
        
        System.out.println("Cache de estados civiles cargado con valores por defecto");
    }
    
    /**
     * Obtener la descripción de un estado civil por su código
     */
    public String getDescripcionEstadoCivil(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return "No especificado";
        }
        
        String descripcion = cacheDescripciones.get(codigo.trim().toUpperCase());
        return descripcion != null ? descripcion : "Estado Civil " + codigo;
    }
    
    /**
     * Obtener todos los estados civiles como un mapa código -> descripción
     */
    public Map<String, String> getTodosLosEstadosCiviles() {
        return new HashMap<>(cacheDescripciones);
    }
    
    /**
     * Recargar el cache desde la base de datos
     */
    public void recargarCache() {
        cargarDescripciones();
    }
    
    /**
     * Verificar si existe un código de estado civil
     */
    public boolean existeCodigo(String codigo) {
        return codigo != null && cacheDescripciones.containsKey(codigo.trim().toUpperCase());
    }
    
    /**
     * Obtener la cantidad de estados civiles cargados
     */
    public int getCantidadEstados() {
        return cacheDescripciones.size();
    }
}
