package com.example.demo.service;

import com.example.demo.entity.EstadoEmpleado;
import com.example.demo.repository.EstadoEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio para obtener descripciones de estados de empleados
 */
@Service
public class EstadoEmpleadoService {
    
    @Autowired
    private EstadoEmpleadoRepository estadoEmpleadoRepository;
    
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
            List<EstadoEmpleado> estados = estadoEmpleadoRepository.findAll();
            cacheDescripciones.clear();
            
            for (EstadoEmpleado estado : estados) {
                cacheDescripciones.put(estado.getCodigo(), estado.getDescripcion());
            }
            
            System.out.println("Cache de estados de empleados cargado: " + cacheDescripciones.size() + " estados");
        } catch (Exception e) {
            System.err.println("Error al cargar cache de estados de empleados: " + e.getMessage());
            // Si hay error, usar valores por defecto
            cargarValoresPorDefecto();
        }
    }
    
    /**
     * Cargar valores por defecto en caso de error con la base de datos
     */
    private void cargarValoresPorDefecto() {
        cacheDescripciones.clear();
        cacheDescripciones.put("A", "Activo");
        cacheDescripciones.put("I", "Inactivo");
        cacheDescripciones.put("L", "Liquidación");
        cacheDescripciones.put("LG", "Licencia por Gravidez");
        cacheDescripciones.put("LN", "Licencia sin Sueldo");
        cacheDescripciones.put("LR", "Licencia Remunerada");
        cacheDescripciones.put("V", "Vacaciones");
    }
    
    /**
     * Obtener descripción de un estado
     */
    public String getDescripcionEstado(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return "No especificado";
        }
        
        String descripcion = cacheDescripciones.get(codigo.trim());
        return descripcion != null ? descripcion : "Estado " + codigo;
    }
    
    /**
     * Obtener todos los estados disponibles
     */
    public Map<String, String> getTodosLosEstados() {
        return new HashMap<>(cacheDescripciones);
    }
    
    /**
     * Recargar el cache manualmente (útil si se actualizan los datos)
     */
    public void recargarCache() {
        cargarDescripciones();
    }
}
