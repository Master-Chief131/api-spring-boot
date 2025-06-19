package com.example.demo.controller;

import com.example.demo.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenController {
    
    @Autowired
    private ImagenService imagenService;
    
    /**
     * Sirve imágenes desde la ruta configurada en segweb_modulo
     * @param rutaImagen La ruta relativa de la imagen (ej: familia_1001/Familia-1-1001-producto_prueba.jpg)
     * @return La imagen como recurso
     */
    @GetMapping("/**")
    public ResponseEntity<Resource> servirImagen(HttpServletRequest request) {
        // Obtener la ruta completa desde la URL
        String rutaCompleta = request.getRequestURI().substring("/api/imagenes/".length());
        
        // Obtener la ruta física completa
        String rutaFisica = imagenService.obtenerRutaImagen(rutaCompleta);
        
        if (rutaFisica == null) {
            return ResponseEntity.notFound().build();
        }
        
        File archivo = new File(rutaFisica);
        
        if (!archivo.exists() || !archivo.isFile()) {
            return ResponseEntity.notFound().build();
        }
        
        try {
            Resource resource = new FileSystemResource(archivo);
            
            // Determinar el tipo de contenido
            String contentType = determinarTipoContenido(archivo.getName());
            
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + archivo.getName() + "\"")
                    .body(resource);
                    
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Determina el tipo MIME basado en la extensión del archivo
     */
    private String determinarTipoContenido(String nombreArchivo) {
        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf('.') + 1).toLowerCase();
        
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "bmp":
                return "image/bmp";
            case "svg":
                return "image/svg+xml";
            case "webp":
                return "image/webp";
            default:
                return "application/octet-stream";
        }
    }
}
