# Documentación de Endpoints de Facturas

Se han implementado endpoints optimizados para consultar las facturas facturadas (status = 'F') de los clientes, con manejo de grandes volúmenes de datos mediante paginación y límites.

## ⚠️ OPTIMIZACIONES IMPLEMENTADAS

Para manejar eficientemente grandes volúmenes de datos, se han implementado las siguientes optimizaciones:

### 🔄 PAGINACIÓN OBLIGATORIA
- **Búsquedas generales**: Siempre paginadas (máximo 100 registros por página)
- **Búsquedas por rango de fechas**: Siempre paginadas cuando no se especifica cliente

### 📊 LÍMITES EN CONSULTAS ESPECÍFICAS
- **Facturas por cliente**: Límite de 1,000 registros
- **Facturas por cliente + fechas**: Límite de 500 registros  
- **Facturas recientes generales**: Límite de 100 registros (con paginación)
- **Facturas recientes por cliente**: Límite de 50 registros

### ⚡ OPTIMIZACIONES DE RENDIMIENTO
- Detalles cargados en batch para evitar consultas N+1
- Consultas SQL nativas para mejor rendimiento
- Índices implícitos en campos clave (no_cia, no_cliente, status, fecha_registro)

## Endpoints de Facturas Disponibles

### GET /api/facturas
Obtiene facturas facturadas con filtros opcionales - **CON PAGINACIÓN OBLIGATORIA**

**Parámetros:**
- `noCia` (obligatorio): Número de compañía
- `noCliente` (opcional): Número de cliente específico
- `fechaDesde` (opcional): Fecha desde (formato ISO: yyyy-MM-ddTHH:mm:ss)
- `fechaHasta` (opcional): Fecha hasta (formato ISO: yyyy-MM-ddTHH:mm:ss)
- `pagina` (opcional): Número de página (empezando en 0, default: 0)
- `tamano` (opcional): Tamaño de página (default: 20, máximo: 100)

**Comportamiento de paginación:**
- ✅ **Búsqueda general**: SIEMPRE paginada
- ✅ **Búsqueda por fechas**: SIEMPRE paginada  
- ❌ **Búsqueda por cliente**: SIN paginación (limitada a 1,000 registros)
- ❌ **Búsqueda por cliente + fechas**: SIN paginación (limitada a 500 registros)

**Respuesta paginada:**
```json
{
  "facturas": [...],
  "paginaActual": 0,
  "totalPaginas": 5,
  "totalElementos": 97,
  "tamano": 20,
  "esUltimaPagina": false,
  "esPrimeraPagina": true
}
```

**Ejemplos:**
```
GET /api/facturas?noCia=1&pagina=0&tamano=20                # Primera página (20 registros)
GET /api/facturas?noCia=1&noCliente=123                     # Cliente específico (sin paginación)
GET /api/facturas?noCia=1&fechaDesde=2024-01-01T00:00:00&pagina=0&tamano=50
```

### GET /api/facturas/{noFactura}
Obtiene una factura específica con sus detalles

**Parámetros:**
- `noCia` (obligatorio): Número de compañía
- `noFactura` (path): Número de factura

**Ejemplo:**
```
GET /api/facturas/12345?noCia=1                             # Factura específica #12345
```

### GET /api/facturas/cliente/{noCliente}
Obtiene todas las facturas de un cliente específico - **SIN PAGINACIÓN** (limitado a 1,000 registros)

**Parámetros:**
- `noCia` (obligatorio): Número de compañía
- `noCliente` (path): Número de cliente

**Ejemplo:**
```
GET /api/facturas/cliente/123?noCia=1                       # Facturas del cliente 123 (máximo 1,000)
```

### GET /api/facturas/recientes
Obtiene facturas de los últimos 30 días - **LIMITADO**

**Límites aplicados:**
- Sin cliente: 100 registros (con paginación)
- Con cliente específico: 50 registros (sin paginación)

**Parámetros:**
- `noCia` (obligatorio): Número de compañía
- `noCliente` (opcional): Número de cliente específico

**Ejemplos:**
```
GET /api/facturas/recientes?noCia=1                         # Facturas recientes de toda la compañía
GET /api/facturas/recientes?noCia=1&noCliente=123          # Facturas recientes del cliente 123
```

## DTO de Respuesta de Facturas

### FacturaCompletaDTO
- `noCia`: Número de compañía
- `noFactura`: Número de factura
- `noSucursal`: Número de sucursal
- `noBodega`: Número de bodega
- `noPrefactura`: Número de prefactura asociada
- `fechaRegistro`: Fecha de registro de la factura
- `noVendedor`: Número del vendedor
- `noCliente`: Número del cliente
- `nombreCliente`: Nombre del cliente
- `rucCedula`: RUC o cédula del cliente
- `tipoFactura`: Tipo de factura
- `subtotalNominal`: Subtotal en moneda local
- `impuestoNominal`: Impuesto en moneda local
- `totalNominal`: Total en moneda local
- `descuentoNominal`: Descuento en moneda local
- `status`: Estado de la factura (siempre 'F' - Facturada)
- `usuarioFacturo`: Usuario que facturó
- `fechaFacturo`: Fecha de facturación
- `observacion`: Observaciones de la factura
- `noOrdenCompra`: Número de orden de compra
- `noSolicitud`: Número de solicitud asociada
- `consecutivoFiscal`: Consecutivo fiscal
- `email`: Email del cliente
- `clienteTelefono`: Teléfono del cliente
- `clienteDireccion`: Dirección del cliente
- `grupo`: Grupo del cliente
- `detalles`: Lista de artículos de la factura

### FacturaDetalleDTO
- `noCia`: Número de compañía
- `noFactura`: Número de factura
- `noLinea`: Número de línea
- `noArticulo`: Código del artículo
- `cantidad`: Cantidad facturada
- `descripcion`: Descripción del artículo
- `precioNominal`: Precio unitario en moneda local
- `subtotalNominal`: Subtotal de la línea
- `descuentoNominal`: Descuento de la línea
- `impuestoNominal`: Impuesto de la línea
- `totalNominal`: Total de la línea
- `porDescuento`: Porcentaje de descuento
- `porImpuesto`: Porcentaje de impuesto
- `codBarra`: Código de barras del artículo

## Página de Ejemplo

Se ha creado una página de ejemplo en:
- **URL**: `http://localhost:8080/consultar-facturas.html`
- **Funcionalidades**:
  - Filtros por compañía, cliente y rango de fechas
  - Búsqueda de facturas recientes (30 días)
  - Estadísticas de facturas y montos
  - Vista expandible de detalles de artículos
  - Interfaz moderna y responsiva

## Scripts de Prueba

- **Archivo**: `test-facturas.ps1`
- **Pruebas incluidas**:
  - Listado general de facturas
  - Facturas por cliente
  - Facturas recientes
  - Factura específica
  - Facturas por rango de fechas
  - Endpoint específico de cliente

---

# Documentación de Endpoints de Servicios y Lista de Precios

## Resumen de Implementación

Se han implementado dos endpoints claramente diferenciados para diferentes propósitos:

1. **`/api/servicios`**: Catálogo básico de servicios de familias habilitadas para portal
2. **`/api/lista-precios`**: Lista completa de servicios con precios de portal (búsqueda automática del grupo)

## Nueva Lógica de Funcionamiento

### Endpoint /api/lista-precios:
1. **Buscar lista de portal**: Busca en `facweb_grupo_mercado` el grupo con `ind_portal = 'S'` para la compañía especificada
2. **Obtener no_grupo**: Usa el `no_grupo` del grupo de portal encontrado automáticamente
3. **Consulta compleja**: Ejecuta una consulta SQL que une varias tablas para obtener servicios con precios únicos
4. **Validación**: Si no existe grupo de portal para la compañía, retorna error 400

### Endpoint /api/servicios:
1. **Filtro simple**: Busca directamente en `invweb_servicio` 
2. **Filtro de familias**: Solo incluye servicios de familias con `ver_portal = 'S'`
3. **Resultado básico**: Retorna entidades `InvwebServicio` sin información de precios

## Endpoints Disponibles

### GET /api/servicios
Obtiene servicios con familias habilitadas para portal (solo familias con `ver_portal = 'S'`)

**Parámetros:**
- `activo` (opcional): Estado activo de los servicios (default: "S")

**Ejemplos:**
```
GET /api/servicios                    # Servicios activos de familias portal
GET /api/servicios?activo=S           # Servicios activos de familias portal
```

### GET /api/lista-precios
Obtiene todos los servicios con precios de lista de portal

**Lógica:**
1. Busca automáticamente en `facweb_grupo_mercado` el grupo con `ind_portal = 'S'` para la compañía especificada
2. Usa el `no_grupo` encontrado para obtener los precios de esa lista de portal
3. Ejecuta la consulta compleja para obtener todos los servicios con sus precios

**Parámetros:**
- `noCia` (opcional): Número de compañía (default: 1)

**Ejemplos:**
```
GET /api/lista-precios                   # Compañía 1, busca su grupo de portal automáticamente
GET /api/lista-precios?noCia=2           # Compañía 2, busca su grupo de portal automáticamente  
```

## Diferencias entre Endpoints

### /api/servicios
- **Función**: Obtiene solo servicios de familias habilitadas para portal (`ver_portal = 'S'`)
- **Resultado**: ~34 registros (servicios básicos de `invweb_servicio`)
- **Uso**: Para mostrar catálogo básico de servicios en el portal

### /api/lista-precios  
- **Función**: Obtiene todos los servicios con información detallada de precios de portal
- **Resultado**: ~186+ registros (todos los servicios con precios de lista)
- **Uso**: Para gestión completa de precios y información detallada

La diferencia principal es que `/api/servicios` filtra por familias portal, mientras que `/api/lista-precios` incluye todos los servicios con información de precios.

## Consulta SQL Implementada

```sql
SELECT a.no_servicio as noArticulo, a.descripcion as descripcion,
       fa.nombre as familia,
       sf.nombre as subFamilia,
       um.nombre as unidad,
       IFNULL(a.costo,0) as costo,
       d.no_pais as noPais, pa.nombre as nombrePais,
       d.no_provincia as noProvincia, pr.nombre as nombreProvincia,
       d.no_distrito as noDistrito, ds.nombre as nombreDistrito,
       d.no_corregimiento as noCorregimiento, cg.nombre as nombreCorregimiento,
       d.no_grupo as noGrupo, IFNULL(d.tipo,'S') as tipo,
       CASE WHEN IFNULL(d.precio_base,a.precio_venta) = 0 THEN IFNULL(a.precio_venta,0) ELSE IFNULL(d.precio_base,a.precio_venta) END as precioBase,
       d.porcentaje as porcentaje,
       CASE WHEN IFNULL(d.precio_venta,a.precio_venta) = 0 THEN IFNULL(a.precio_venta,0) ELSE IFNULL(d.precio_venta,a.precio_venta) END as precioVenta,
       CASE WHEN d.usuario_mod IS NULL THEN d.usuario_crea ELSE d.usuario_mod END as usuarioMod,
       CASE WHEN d.fecha_mod IS NULL THEN d.fecha_crea ELSE d.fecha_mod END as fechaMod,
       d.estado_lista_precio as estadoListaPrecio,
       d.precio_base_lista as precioBaseLista,
       d.precio_venta_lista as precioVentaLista,
       d.usuario_lista_mod as usuarioListaMod,
       CASE WHEN d.no_grupo IS NULL THEN 'I' ELSE 'U' END as indicadorAct
FROM invweb_servicio a 
INNER JOIN invweb_familia fa ON fa.no_cia = a.no_cia AND fa.no_familia = a.no_familia [AND fa.ver_portal = 'S']
INNER JOIN invweb_subfamilia sf ON sf.no_cia = a.no_cia AND sf.no_familia = a.no_familia AND sf.no_subfamilia = a.no_sub_familia
INNER JOIN invweb_unidad_medida um ON um.no_cia = a.no_cia AND um.no_unidad = a.no_unidad
LEFT JOIN facweb_descuento d ON a.no_cia = d.no_cia AND a.no_servicio = d.No_articulo AND d.tipo = 'S' AND d.no_grupo = :noGrupo
LEFT JOIN genweb_pais pa ON pa.no_cia = a.no_cia AND pa.no_pais = d.no_pais
LEFT JOIN genweb_provincia pr ON pr.no_cia = a.no_cia AND pr.no_pais = d.no_pais AND pr.no_provincia = d.no_provincia
LEFT JOIN genweb_distrito ds ON ds.no_cia = a.no_cia AND ds.no_pais = d.no_pais AND ds.no_provincia = d.no_provincia AND ds.no_distrito = d.no_distrito
LEFT JOIN genweb_corregimiento cg ON cg.no_cia = a.no_cia AND cg.no_pais = d.no_pais AND cg.no_provincia = d.no_provincia AND cg.no_distrito = d.no_distrito AND cg.no_corregimiento = d.no_corregimiento
WHERE a.no_cia = :noCia AND a.activo = 'S'
ORDER BY a.no_servicio ASC
```

Nota: `[AND fa.ver_portal = 'S']` se incluye solo cuando `incluirTodos=false`

## DTO de Respuesta

El endpoint devuelve una lista de `ServicioConPrecioDTO` con los siguientes campos:

- `noArticulo`: Número del servicio
- `descripcion`: Descripción del servicio
- `familia`: Nombre de la familia
- `subFamilia`: Nombre de la subfamilia
- `unidad`: Nombre de la unidad de medida
- `costo`: Costo del servicio
- `noPais`, `nombrePais`: Información del país
- `noProvincia`, `nombreProvincia`: Información de la provincia
- `noDistrito`, `nombreDistrito`: Información del distrito
- `noCorregimiento`, `nombreCorregimiento`: Información del corregimiento
- `noGrupo`: Número del grupo de precios
- `tipo`: Tipo de descuento
- `precioBase`: Precio base calculado
- `porcentaje`: Porcentaje de descuento
- `precioVenta`: Precio de venta calculado
- `usuarioMod`: Usuario que modificó
- `fechaMod`: Fecha de modificación
- `estadoListaPrecio`: Estado de la lista de precios
- `precioBaseLista`, `precioVentaLista`: Precios de lista
- `usuarioListaMod`: Usuario que modificó la lista
- `indicadorAct`: Indicador de actualización ('I' = Insert, 'U' = Update)
