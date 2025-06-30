package com.example.demo.dto;

import com.example.demo.entity.Empleado;
import com.example.demo.service.EstadoEmpleadoService;
import com.example.demo.service.EstadoCivilService;
// import java.math.BigDecimal; // Comentado por si se necesita más adelante

/**
 * DTO para mostrar información simplificada de los empleados
 */
public class EmpleadoDTO {
    
    private String codPla;
    private String noEmple;
    private String nombre;
    private String cedula;
    private String email;
    private String estadoCivil;
    private String sexo;
    private String depto;
    private String puesto;
    private String estado;
    // private BigDecimal salario; // Comentado por si se necesita más adelante
    
    // Campos calculados para mejor presentación
    private String estadoDescripcion;
    private String estadoCivilDescripcion;
    private String sexoDescripcion;
    
    // Constructor por defecto
    public EmpleadoDTO() {}
    
    // Constructor que recibe una entidad Empleado y el servicio de estados
    public EmpleadoDTO(Empleado empleado, EstadoEmpleadoService estadoEmpleadoService) {
        this.codPla = empleado.getCodPla();
        this.noEmple = empleado.getNoEmple();
        this.nombre = empleado.getNombre();
        this.cedula = empleado.getCedula();
        this.email = empleado.getEmail();
        this.estadoCivil = empleado.getECivil();
        this.sexo = empleado.getSexo();
        this.puesto = empleado.getPuesto();
        this.depto = empleado.getDepto();
        this.estado = empleado.getEstado();
        // this.salario = empleado.getSalario(); // Comentado por si se necesita más adelante
        
        // Descripciones amigables usando el servicio
        if (estadoEmpleadoService != null) {
            this.estadoDescripcion = estadoEmpleadoService.getDescripcionEstado(empleado.getEstado());
        } else {
            // this.estadoDescripcion = getEstadoDescripcionFromCodigo(empleado.getEstado());
        }
        
        this.sexoDescripcion = "H".equals(empleado.getSexo()) ? "Masculino" : 
                              "F".equals(empleado.getSexo()) ? "Femenino" : "No especificado";
    }
    
    // Constructor que recibe una entidad Empleado y ambos servicios
    public EmpleadoDTO(Empleado empleado, EstadoEmpleadoService estadoEmpleadoService, EstadoCivilService estadoCivilService) {
        this.codPla = empleado.getCodPla();
        this.noEmple = empleado.getNoEmple();
        this.nombre = empleado.getNombre();
        this.cedula = empleado.getCedula();
        this.email = empleado.getEmail();
        this.estadoCivil = empleado.getECivil();
        this.sexo = empleado.getSexo();
        this.puesto = empleado.getPuesto();
        this.depto = empleado.getDepto();
        this.estado = empleado.getEstado();
        // this.salario = empleado.getSalario(); // Comentado por si se necesita más adelante
        
        // Descripciones amigables usando los servicios
        if (estadoEmpleadoService != null) {
            this.estadoDescripcion = estadoEmpleadoService.getDescripcionEstado(empleado.getEstado());
        } else {
            this.estadoDescripcion = "Estado " + (empleado.getEstado() != null ? empleado.getEstado() : "No especificado");
        }
        
        if (estadoCivilService != null) {
            this.estadoCivilDescripcion = estadoCivilService.getDescripcionEstadoCivil(empleado.getECivil());
        } else {
            this.estadoCivilDescripcion = empleado.getECivil() != null ? empleado.getECivil() : "No especificado";
        }
        
        this.sexoDescripcion = "H".equals(empleado.getSexo()) ? "Masculino" : 
                              "F".equals(empleado.getSexo()) ? "Femenino" : "No especificado";
    }
    
    // Constructor que recibe una entidad Empleado (mantenido para compatibilidad)
    public EmpleadoDTO(Empleado empleado) {
        this(empleado, null, null);
    }
    
    // Método fallback para cuando no se puede usar el servicio
    // private String getEstadoDescripcionFromCodigo(String estado) {
    //     if (estado == null) return "No especificado";
    //     switch (estado) {
    //         case "L": return "Liquidacion";
    //         case "I": return "Inactivo";
    //         case "A": return "Activo";
    //         case "LG": return "Licencia por Gravidez";
    //         case "LN": return "Licencia sin Sueldo";
    //         case "LR": return "Licencia Remunerada";
    //         case "V": return "Vacaciones";
    //         default: return "Estado " + estado;
    //     }
    // }

    // Getters y Setters
    public String getCodPla() {
        return codPla;
    }

    public void setCodPla(String codPla) {
        this.codPla = codPla;
    }

    public String getNoEmple() {
        return noEmple;
    }

    public void setNoEmple(String noEmple) {
        this.noEmple = noEmple;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Comentado por si se necesita más adelante
    // public BigDecimal getSalario() {
    //     return salario;
    // }

    // public void setSalario(BigDecimal salario) {
    //     this.salario = salario;
    // }

    public String getEstadoDescripcion() {
        return estadoDescripcion;
    }

    public void setEstadoDescripcion(String estadoDescripcion) {
        this.estadoDescripcion = estadoDescripcion;
    }

    public String getEstadoCivilDescripcion() {
        return estadoCivilDescripcion;
    }

    public void setEstadoCivilDescripcion(String estadoCivilDescripcion) {
        this.estadoCivilDescripcion = estadoCivilDescripcion;
    }

    public String getSexoDescripcion() {
        return sexoDescripcion;
    }

    public void setSexoDescripcion(String sexoDescripcion) {
        this.sexoDescripcion = sexoDescripcion;
    }

    @Override
    public String toString() {
        return "EmpleadoDTO{" +
                "codPla='" + codPla + '\'' +
                ", noEmple='" + noEmple + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
