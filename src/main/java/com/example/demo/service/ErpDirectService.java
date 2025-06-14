package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Servicio alternativo para comunicación directa con servlets del ERP
 * cuando están en el mismo contenedor Tomcat.
 * 
 * Esta opción es más eficiente que HTTP REST calls cuando ambas aplicaciones
 * están en el mismo Tomcat.
 */
@Service
public class ErpDirectService {

    /**
     * Llamada directa a servlet del ERP usando RequestDispatcher
     * Solo funciona cuando ambas webapps están en el mismo Tomcat
     */
    public String llamadaDirectaServlet(String servletPath, Map<String, String> parametros) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            HttpServletResponse response = attributes.getResponse();

            // Agregar parámetros al request
            if (parametros != null) {
                parametros.forEach(request::setAttribute);
            }

            // Crear un wrapper para capturar la respuesta
            ResponseWrapper responseWrapper = new ResponseWrapper(response);

            // Obtener dispatcher para el servlet del ERP
            RequestDispatcher dispatcher = request.getRequestDispatcher(servletPath);
            
            if (dispatcher != null) {
                // Hacer forward al servlet del ERP
                dispatcher.forward(request, responseWrapper);
                
                // Retornar la respuesta capturada
                return responseWrapper.getResponseContent();
            } else {
                return "Error: No se pudo obtener dispatcher para " + servletPath;
            }

        } catch (Exception e) {
            return "Error en llamada directa: " + e.getMessage();
        }
    }

    /**
     * Wrapper para capturar la respuesta del servlet
     */
    private static class ResponseWrapper extends HttpServletResponseWrapper {
        private CharArrayWriter writer;
        private PrintWriter printWriter;

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
            writer = new CharArrayWriter();
            printWriter = new PrintWriter(writer);
        }

        @Override
        public PrintWriter getWriter() {
            return printWriter;
        }

        public String getResponseContent() {
            printWriter.flush();
            return writer.toString();
        }
    }    /**
     * Método helper para validar si estamos en el mismo Tomcat
     */
    public boolean isInSameTomcat() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            
            // Intentar obtener dispatcher para contexto ERP específico
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cptsoft-erp-prueba/");
            return dispatcher != null;
            
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Información sobre el contexto actual
     */
    public String getContextInfo() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            
            StringBuilder info = new StringBuilder();
            info.append("Context Path: ").append(request.getContextPath()).append("\n");
            info.append("Server Name: ").append(request.getServerName()).append("\n");
            info.append("Server Port: ").append(request.getServerPort()).append("\n");
            info.append("Request URI: ").append(request.getRequestURI()).append("\n");
            info.append("Same Tomcat: ").append(isInSameTomcat()).append("\n");
            
            return info.toString();
            
        } catch (Exception e) {
            return "Error obteniendo información del contexto: " + e.getMessage();
        }
    }
}
