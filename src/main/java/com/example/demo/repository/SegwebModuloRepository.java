package com.example.demo.repository;

import com.example.demo.entity.SegwebModulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SegwebModuloRepository extends JpaRepository<SegwebModulo, String> {
      // Buscar módulo por código
    Optional<SegwebModulo> findByModulo(String modulo);
    
    // Buscar módulo por nombre (útil para buscar "inventario" en el nombre)
    Optional<SegwebModulo> findByNomModuloContainingIgnoreCase(String nombreModulo);
    
    // Buscar módulo de inventario con múltiples opciones
    default Optional<SegwebModulo> findModuloInventario() {
        // Intentar primero con códigos comunes para inventario
        String[] codigosInventario = {"IN", "IV", "01", "02"};
        
        for (String codigo : codigosInventario) {
            Optional<SegwebModulo> modulo = findByModulo(codigo);
            if (modulo.isPresent()) {
                return modulo;
            }
        }
        
        // Si no encuentra por código, buscar por nombre que contenga "inventario"
        return findByNomModuloContainingIgnoreCase("inventario");
    }
}
