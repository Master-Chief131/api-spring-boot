# 📖 GUÍA COMPLETA: LECTURA AUTOMÁTICA DEL WEB.XML DEL ERP

## 🎯 **¿Qué es y para qué sirve?**

La funcionalidad de lectura automática del `web.xml` permite que tu API Spring Boot lea automáticamente los servlet mappings del ERP sin necesidad de configurar manualmente cada URL en `application.properties`.

## ✅ **Ventajas:**

1. **Configuración Automática**: No necesitas configurar URLs manualmente
2. **Sincronización**: Si cambias un mapping en el ERP, se refleja automáticamente
3. **Descubrimiento**: Encuentra servlets que no conocías
4. **Menos Errores**: Evita errores de tipeo en URLs
5. **Mantenimiento**: Menos archivos de configuración que mantener

## ⚠️ **Limitaciones:**

1. **Mismo Tomcat**: Solo funciona cuando ambas apps están en el mismo Tomcat
2. **Permisos**: Requiere acceso al sistema de archivos o contexto del ERP
3. **Parsing**: Depende de la estructura estándar del web.xml
4. **Fallback**: Necesita configuración manual como respaldo

## 🔧 **Cómo Funciona:**

### **1. Métodos de Lectura (en orden de prioridad):**

```java
// 1. Desde contexto del ERP (más eficiente)
ServletContext erpContext = context.getContext("/cptsoft-erp-prueba");
InputStream webXml = erpContext.getResourceAsStream("/WEB-INF/web.xml");

// 2. Desde sistema de archivos (respaldo)
String[] paths = {
    "../cptsoft-erp-prueba/WEB-INF/web.xml",
    "../../webapps/cptsoft-erp-prueba/WEB-INF/web.xml"
};

// 3. Configuración por defecto (última opción)
servletMappings.put("ValidarUsuario", "/cptsoft-erp-prueba/ValidarUsuario");
```

### **2. Estructura del Web.xml que se Lee:**

```xml
<servlet>
    <servlet-name>ValidarUsuario</servlet-name>
    <servlet-class>com.cptsoft.erp.servlets.ValidarUsuarioServlet</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>ValidarUsuario</servlet-name>
    <url-pattern>/ValidarUsuario</url-pattern>
</servlet-mapping>
```

### **3. Mapeo Resultante:**

```java
// El servicio crea estos mappings automáticamente:
"ValidarUsuario" -> "/cptsoft-erp-prueba/ValidarUsuario"
"ValidarUsuarioServlet" -> "/cptsoft-erp-prueba/ValidarUsuario"
```

## 🚀 **Configuración:**

### **application.properties:**
```properties
# Habilitar lectura automática del web.xml
erp.use.webxml.mappings=true

# URLs de respaldo (si no se puede leer web.xml)
erp.servlet.validar-usuario=/cptsoft-erp-prueba/ValidarUsuario
erp.servlet.cambiar-clave=/cptsoft-erp-prueba/CambiarClave
```

### **Variables de Entorno:**
```bash
# PowerShell
$env:ERP_USE_WEBXML_MAPPINGS="true"

# CMD
set ERP_USE_WEBXML_MAPPINGS=true
```

## 📋 **Endpoints para Gestión:**

### **Ver Mappings Cargados:**
```bash
GET http://localhost:8088/Api-Demo/api/erp/webxml-info
```

**Respuesta:**
```json
{
  "mappingsLoaded": true,
  "totalMappings": 6,
  "loadedFromWebXml": true,
  "mappings": {
    "ValidarUsuario": "/cptsoft-erp-prueba/ValidarUsuario",
    "CambiarClave": "/cptsoft-erp-prueba/CambiarClave",
    "BuscarCliente": "/cptsoft-erp-prueba/BuscarCliente"
  }
}
```

### **Refrescar Mappings:**
```bash
POST http://localhost:8088/Api-Demo/api/erp/refresh-webxml
```

## 🧪 **Casos de Prueba:**

### **Caso 1: Web.xml Accesible**
```bash
# El sistema lee automáticamente todos los servlets
GET /api/erp/webxml-info
# Resultado: mappings cargados desde web.xml
```

### **Caso 2: Web.xml No Accesible**
```bash
# El sistema usa configuración por defecto
GET /api/erp/webxml-info
# Resultado: "loadedFromWebXml": false, usa defaults
```

### **Caso 3: Nuevo Servlet Agregado al ERP**
```bash
# Refrescar para detectar nuevos servlets
POST /api/erp/refresh-webxml
# Resultado: nuevos mappings detectados
```

## 🔍 **Debugging:**

### **Ver Logs:**
```bash
# Los logs muestran qué método de carga se usó
=== ERP Client Configuration ===
Use WebXML Mappings: true
WebXML Mappings:
  ValidarUsuario -> /cptsoft-erp-prueba/ValidarUsuario
  CambiarClave -> /cptsoft-erp-prueba/CambiarClave
```

### **Probar en Página Web:**
```
http://localhost:8088/Api-Demo/test-integracion-erp.html
```

## 🎯 **Recomendaciones:**

### **Para Desarrollo:**
- ✅ Habilitar `erp.use.webxml.mappings=true`
- ✅ Usar endpoint de refresh para cambios rápidos
- ✅ Verificar logs para confirmar carga exitosa

### **Para Producción:**
- ✅ Probar ambos métodos (web.xml y manual)
- ✅ Configurar URLs de respaldo en application.properties
- ✅ Implementar monitoreo de errores de carga

### **Estructura de Archivos Recomendada:**
```
tomcat/
├── webapps/
│   ├── Api-Demo/
│   │   ├── WEB-INF/
│   │   └── ...
│   └── cptsoft-erp-prueba/
│       ├── WEB-INF/
│       │   ├── web.xml          ← Este archivo se lee automáticamente
│       │   └── classes/
│       └── ...
```

## 🚫 **Cuándo NO Usar:**

- ❌ **Aplicaciones en servidores diferentes**
- ❌ **URLs de ERP cambian frecuentemente**
- ❌ **Problemas de permisos de archivo**
- ❌ **Web.xml no estándar o muy personalizado**

## 🔄 **Alternativas:**

1. **Configuración Manual**: Más control, menos automatización
2. **API de Descubrimiento**: El ERP expone sus endpoints via API
3. **Configuración Centralizada**: Base de datos o config server
4. **Convención sobre Configuración**: URLs predecibles

## 📊 **Comparación Final:**

| Método | Ventajas | Desventajas | Mejor Para |
|--------|----------|-------------|------------|
| **WebXML Auto** | Sin configuración manual | Solo mismo Tomcat | Desarrollo rápido |
| **Manual** | Control total | Mantenimiento manual | Producción estable |
| **Híbrido** | Lo mejor de ambos | Más complejo | Entornos mixtos |

¡El sistema está diseñado para funcionar de forma híbrida: automático cuando es posible, manual cuando es necesario!
