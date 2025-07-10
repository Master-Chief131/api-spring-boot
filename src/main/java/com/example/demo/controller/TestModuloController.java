// package com.example.demo.controller;

// import com.example.demo.entity.SegwebModulo;
// import com.example.demo.repository.SegwebModuloRepository;
// import com.example.demo.service.ImagenService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/test")
// public class TestModuloController {
    
//     @Autowired
//     private SegwebModuloRepository segwebModuloRepository;
    
//     @Autowired
//     private ImagenService imagenService;
    
//     /**
//      * Endpoint para listar todos los módulos disponibles
//      */
//     @GetMapping("/modulos")
//     public List<SegwebModulo> listarTodosLosModulos() {
//         return segwebModuloRepository.findAll();
//     }
    
//     /**
//      * Endpoint para obtener el módulo de inventario detectado automáticamente
//      */
//     @GetMapping("/modulo-inventario")
//     public Optional<SegwebModulo> obtenerModuloInventario() {
//         return segwebModuloRepository.findModuloInventario();
//     }
    
//     /**
//      * Endpoint para obtener la ruta base de inventario
//      */
//     @GetMapping("/ruta-inventario")
//     public String obtenerRutaInventario() {
//         return imagenService.obtenerRutaBaseInventario();
//     }
    
//     /**
//      * Endpoint para probar la generación de URL de imagen
//      */
//     @GetMapping("/test-imagen-url")
//     public String testImagenUrl(@RequestParam(defaultValue = "familia_1001\\Familia-1-1001-producto_prueba.jpg") String archivo) {
//         return imagenService.obtenerUrlImagen(archivo);
//     }
    
//     /**
//      * Endpoint para probar la generación de ruta física de imagen
//      */
//     @GetMapping("/test-imagen-ruta")
//     public String testImagenRuta(@RequestParam(defaultValue = "familia_1001\\Familia-1-1001-producto_prueba.jpg") String archivo) {
//         return imagenService.obtenerRutaImagen(archivo);
//     }
// }
