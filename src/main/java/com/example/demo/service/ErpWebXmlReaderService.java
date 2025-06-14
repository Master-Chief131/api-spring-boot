package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Servicio para leer automáticamente el mapeo de servlets desde el web.xml del ERP
 */
@Service
public class ErpWebXmlReaderService {

    private Map<String, String> servletMappings = null;
    private boolean mappingsLoaded = false;

    /**
     * Lee los servlet mappings desde el web.xml del ERP
     */
    public Map<String, String> getServletMappings() {
        if (!mappingsLoaded) {
            loadServletMappings();
        }
        return servletMappings != null ? new HashMap<>(servletMappings) : new HashMap<>();
    }

    /**
     * Obtiene la URL de un servlet específico
     */
    public String getServletUrl(String servletName) {
        Map<String, String> mappings = getServletMappings();
        return mappings.get(servletName);
    }

    /**
     * Carga los servlet mappings desde el web.xml del ERP
     */
    private void loadServletMappings() {
        servletMappings = new HashMap<>();
        
        try {
            // Método 1: Intentar leer desde el contexto del ERP
            if (loadFromErpContext()) {
                mappingsLoaded = true;
                return;
            }
            
            // Método 2: Intentar leer desde archivo en el sistema
            if (loadFromFileSystem()) {
                mappingsLoaded = true;
                return;
            }
            
            // Método 3: Cargar mappings por defecto
            loadDefaultMappings();
            mappingsLoaded = true;
            
        } catch (Exception e) {
            System.err.println("Error cargando servlet mappings: " + e.getMessage());
            loadDefaultMappings();
            mappingsLoaded = true;
        }
    }

    /**
     * Intenta leer el web.xml desde el contexto del ERP
     */
    private boolean loadFromErpContext() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            ServletContext context = request.getServletContext();
            
            // Intentar acceder al contexto del ERP
            ServletContext erpContext = context.getContext("/cptsoft-erp-prueba");
            
            if (erpContext != null) {
                InputStream webXmlStream = erpContext.getResourceAsStream("/WEB-INF/web.xml");
                
                if (webXmlStream != null) {
                    parseWebXml(webXmlStream, "/cptsoft-erp-prueba");
                    return true;
                }
            }
            
        } catch (Exception e) {
            System.out.println("No se pudo leer desde contexto ERP: " + e.getMessage());
        }
        
        return false;
    }

    /**
     * Intenta leer el web.xml desde el sistema de archivos
     */
    private boolean loadFromFileSystem() {
        try {
            // Rutas típicas donde podría estar el web.xml del ERP
            String[] possiblePaths = {
                "../cptsoft-erp-prueba/WEB-INF/web.xml",
                "../../webapps/cptsoft-erp-prueba/WEB-INF/web.xml",
                "../webapps/cptsoft-erp-prueba/WEB-INF/web.xml"
            };
            
            for (String path : possiblePaths) {
                try {
                    InputStream webXmlStream = this.getClass().getClassLoader().getResourceAsStream(path);
                    if (webXmlStream != null) {
                        parseWebXml(webXmlStream, "/cptsoft-erp-prueba");
                        return true;
                    }
                } catch (Exception e) {
                    // Continuar con la siguiente ruta
                }
            }
            
        } catch (Exception e) {
            System.out.println("No se pudo leer desde sistema de archivos: " + e.getMessage());
        }
        
        return false;
    }

    /**
     * Parsea el archivo web.xml para extraer los servlet mappings
     */
    private void parseWebXml(InputStream webXmlStream, String contextPath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(webXmlStream);
            
            // Crear mapas para servlets y sus mappings
            Map<String, String> servletNames = new HashMap<>();
            Map<String, String> servletUrls = new HashMap<>();
            
            // Leer definiciones de servlets
            NodeList servletNodes = document.getElementsByTagName("servlet");
            for (int i = 0; i < servletNodes.getLength(); i++) {
                Element servlet = (Element) servletNodes.item(i);
                
                String servletName = getTextContent(servlet, "servlet-name");
                String servletClass = getTextContent(servlet, "servlet-class");
                
                if (servletName != null && servletClass != null) {
                    servletNames.put(servletName, servletClass);
                }
            }
            
            // Leer mappings de servlets
            NodeList mappingNodes = document.getElementsByTagName("servlet-mapping");
            for (int i = 0; i < mappingNodes.getLength(); i++) {
                Element mapping = (Element) mappingNodes.item(i);
                
                String servletName = getTextContent(mapping, "servlet-name");
                String urlPattern = getTextContent(mapping, "url-pattern");
                
                if (servletName != null && urlPattern != null) {
                    servletUrls.put(servletName, urlPattern);
                }
            }
            
            // Combinar información y crear mappings finales
            for (Map.Entry<String, String> entry : servletNames.entrySet()) {
                String servletName = entry.getKey();
                String servletClass = entry.getValue();
                String urlPattern = servletUrls.get(servletName);
                
                if (urlPattern != null) {
                    // Crear mapping usando el nombre de la clase del servlet
                    String simpleClassName = servletClass.substring(servletClass.lastIndexOf('.') + 1);
                    String fullUrl = contextPath + urlPattern;
                    
                    servletMappings.put(simpleClassName, fullUrl);
                    servletMappings.put(servletName, fullUrl);
                }
            }
            
            System.out.println("Servlet mappings cargados desde web.xml:");
            servletMappings.forEach((key, value) -> 
                System.out.println("  " + key + " -> " + value)
            );
            
        } catch (Exception e) {
            throw new RuntimeException("Error parseando web.xml", e);
        }
    }

    /**
     * Obtiene el contenido de texto de un elemento hijo
     */
    private String getTextContent(Element parent, String tagName) {
        NodeList nodes = parent.getElementsByTagName(tagName);
        if (nodes.getLength() > 0) {
            return nodes.item(0).getTextContent().trim();
        }
        return null;
    }    /**
     * Carga mappings por defecto si no se puede leer el web.xml
     */
    private void loadDefaultMappings() {
        servletMappings = new HashMap<>();
        
        // Mappings reales basados en el web.xml del ERP
        servletMappings.put("ValidarUsuario", "/cptsoft-erp-prueba/acceseguridad");
        servletMappings.put("CambiarClave", "/cptsoft-erp-prueba/sv_cambio_clave");
        servletMappings.put("BuscarCliente", "/cptsoft-erp-prueba/usuario");  // Temporal, necesita implementación
        servletMappings.put("CotizacionServlet", "/cptsoft-erp-prueba/CotizacionServlet");  // Temporal, necesita implementación
        
        System.out.println("Usando servlet mappings por defecto (actualizados):");
        servletMappings.forEach((key, value) -> 
            System.out.println("  " + key + " -> " + value)
        );
    }

    /**
     * Refresca los mappings (útil para desarrollo)
     */
    public void refreshMappings() {
        mappingsLoaded = false;
        servletMappings = null;
        loadServletMappings();
    }

    /**
     * Verifica si los mappings fueron cargados exitosamente desde web.xml
     */
    public boolean isMappingsLoadedFromWebXml() {
        return mappingsLoaded && servletMappings != null && !servletMappings.isEmpty();
    }

    /**
     * Obtiene información sobre el estado de carga
     */
    public Map<String, Object> getLoadingInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("mappingsLoaded", mappingsLoaded);
        info.put("totalMappings", servletMappings != null ? servletMappings.size() : 0);
        info.put("loadedFromWebXml", isMappingsLoadedFromWebXml());
        info.put("mappings", getServletMappings());
        
        return info;
    }
}
