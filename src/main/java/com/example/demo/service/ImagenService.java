package com.example.demo.service;

import com.example.demo.entity.SegwebModulo;
import com.example.demo.repository.SegwebModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ImagenService {
    
    @Autowired
    private SegwebModuloRepository segwebModuloRepository;
    
    /**
     * Obtiene la ruta completa de una imagen basada en el módulo de inventario
     * @param archivoImagen El nombre del archivo de imagen desde la tabla invweb_familia_img o invweb_subfamilia_img
     * @return La ruta completa del archivo o null si no se encuentra el módulo
     */
    public String obtenerRutaImagen(String archivoImagen) {
        if (archivoImagen == null || archivoImagen.trim().isEmpty()) {
            return null;
        }
        
        // Buscar el módulo de inventario
        Optional<SegwebModulo> moduloInventario = segwebModuloRepository.findModuloInventario();
        
        if (moduloInventario.isPresent() && moduloInventario.get().getRuta() != null) {
            String rutaBase = moduloInventario.get().getRuta();
            
            // Combinar la ruta base con el archivo de imagen
            // Ejemplo: C:\cptsoft\inventario + familia_1001\Familia-1-1001-producto_prueba.jpg
            return Paths.get(rutaBase, archivoImagen).toString();
        }
        
        return null;
    }
    
    /**
     * Obtiene la URL web de una imagen para ser servida por el controlador
     * @param archivoImagen El nombre del archivo de imagen
     * @return La URL relativa para acceder a la imagen vía web
     */
    public String obtenerUrlImagen(String archivoImagen) {
        if (archivoImagen == null || archivoImagen.trim().isEmpty()) {
            return null;
        }
        
        // Retorna una URL que será manejada por un controlador específico
        // El controlador se encargará de servir la imagen desde la ruta física
        return "/api/imagenes/" + archivoImagen.replace("\\", "/");
    }
    
    /**
     * Obtiene la ruta base del módulo de inventario
     * @return La ruta base del módulo de inventario
     */
    public String obtenerRutaBaseInventario() {
        Optional<SegwebModulo> moduloInventario = segwebModuloRepository.findModuloInventario();
        return moduloInventario.map(SegwebModulo::getRuta).orElse(null);
    }
}
