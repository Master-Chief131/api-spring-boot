package com.example.demo.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {
    
    /**
     * Envía credenciales por correo usando configuración del ERP original
     * Replicando exactamente el comportamiento de EnvioClave.java
     */
    public void enviarCredenciales(String usuario, String claveGenerada, 
                                 String nombreCompleto, String emailDestino) {
        enviarCredenciales(usuario, claveGenerada, nombreCompleto, emailDestino, 1);
    }
      /**
     * Envía credenciales por correo usando configuración específica de compañía
     */    public void enviarCredenciales(String usuario, String claveGenerada, 
                                 String nombreCompleto, String emailDestino, Integer noCia) {
        System.out.println("=== ENVIANDO CORREO VIA GMAIL ===");
        System.out.println("Destinatario: " + emailDestino);
        System.out.println("Usuario: " + usuario);
        System.out.println("Nombre completo: " + nombreCompleto);
        System.out.println("Compañía: " + noCia);
        System.out.println("Nueva cuenta Gmail: opinto@cpt-soft.com");
        
        // Crear mensaje
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("opinto@cpt-soft.com");
        message.setTo(emailDestino);
        message.setSubject("Credenciales de Acceso - CPT-SOFT ERP");
        
        // Mensaje idéntico al ERP original
        String cuerpoMensaje = String.format(
            "Bienvenido al sistema de Planificación de Recursos Empresariales (CPT-SOFT ERP), " +
            "nos complace serle útil en sus labores diarias.\n\n" +
            "Su usuario es: %s y contraseña: %s, recuerde que puede cambiar su clave para mayor comodidad.\n\n" +
            "Atentamente:\n" +
            "Proceso automático de cambio de contraseña",
            usuario.toUpperCase(), claveGenerada
        );
        
        message.setText(cuerpoMensaje);
        
        // Intentar envío con múltiples configuraciones
        boolean enviado = false;
        
        // 1. Intentar con STARTTLS (puerto 587) - más compatible
        System.out.println("\n1️⃣ Intentando envío con STARTTLS puerto 587...");
        try {
            enviarConSTARTTLS(message);
            System.out.println("✅ CORREO ENVIADO EXITOSAMENTE VIA STARTTLS");
            enviado = true;
        } catch (Exception e) {
            System.err.println("❌ STARTTLS falló: " + e.getMessage());
        }
          // 2. Si STARTTLS falla, intentar con SSL (puerto 465)
        if (!enviado) {
            System.out.println("\n2️⃣ Intentando envío con SSL puerto 465...");
            try {
                enviarConSSL(message);
                System.out.println("✅ CORREO ENVIADO EXITOSAMENTE VIA SSL");
                enviado = true;
            } catch (Exception e) {
                System.err.println("❌ SSL falló: " + e.getMessage());
            }
        }
          if (!enviado) {
            System.err.println("=== ❌ TODOS LOS MÉTODOS DE ENVÍO FALLARON ===");
            mostrarInstruccionesGmail();
            throw new RuntimeException("No se pudo enviar el correo con ninguna configuración de Gmail");
        }
    }    /**
     * Envía correo usando STARTTLS (puerto 587) - Configuración para Office 365
     * PUERTO 587 CONFIRMADO FUNCIONANDO desde telnet
     * Nueva cuenta: opinto@cpt-soft.com
     */
    private void enviarConSTARTTLS(SimpleMailMessage message) throws Exception {        System.out.println("=== USANDO GMAIL - PUERTO 587 CON STARTTLS ===");
        
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");  // Gmail
        mailSender.setPort(587);  // Puerto STARTTLS CONFIRMADO
        mailSender.setUsername("opinto@cpt-soft.com");
        mailSender.setPassword("@OPINTO22");
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        
        // Configuración STARTTLS para Gmail
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");  // Gmail requiere TLS
        
        // Usar TLS 1.2 que es más estable
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        // Configuraciones SSL para Gmail
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        // Timeouts estándar
        props.put("mail.smtp.connectiontimeout", "30000");
        props.put("mail.smtp.timeout", "30000");
        props.put("mail.smtp.writetimeout", "30000");
        
        // Debug para diagnóstico
        props.put("mail.debug", "true");
        
        System.out.println("Servidor: smtp.gmail.com:587");
        System.out.println("Cuenta: opinto@cpt-soft.com");
        System.out.println("Configuración: STARTTLS + TLS 1.2");
        
        mailSender.send(message);
    }    /**
     * Envía correo usando SSL directo (puerto 465) - Configuración para Gmail
     * Nueva cuenta Gmail: opinto@cpt-soft.com con SSL
     */
    private void enviarConSSL(SimpleMailMessage message) throws Exception {
        System.out.println("=== USANDO GMAIL - PUERTO 465 CON SSL ===");
        
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");  // Gmail
        mailSender.setPort(465);  // Puerto SSL
        mailSender.setUsername("opinto@cpt-soft.com");
        mailSender.setPassword("@OPINTO22");
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        
        // Configuración SSL directa para Gmail
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        // Socket factory para SSL
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.fallback", "false");
          // Timeouts
        props.put("mail.smtp.connectiontimeout", "60000");
        props.put("mail.smtp.timeout", "60000");
        
        // No usar STARTTLS con SSL directo
        props.put("mail.smtp.starttls.enable", "false");
        
        props.put("mail.debug", "true");
          System.out.println("Cuenta: opinto@cpt-soft.com");
        System.out.println("Configuración SSL: smtp.gmail.com:465 con SSL directo");
        mailSender.send(message);
    }
    
    /**
     * Muestra instrucciones para configurar Office 365 correctamente
     */    
    private void mostrarInstruccionesGmail() {        System.err.println("\n🚨 DIAGNÓSTICO: PROBANDO NUEVA CUENTA GMAIL");
        System.err.println("════════════════════════════════════════════════════════");
        System.err.println("🔄 CUENTA NUEVA: opinto@cpt-soft.com");
        System.err.println("🏢 PROVEEDOR: Gmail (smtp.gmail.com)");
        System.err.println("🔐 CONTRASEÑA: @OPINTO22");
        System.err.println("");
        System.err.println("🔑 VERIFICACIONES GMAIL:");
        System.err.println("");
        System.err.println("📧 PASO 1: Verificar configuración de cuenta");
        System.err.println("• Cuenta: opinto@cpt-soft.com debe existir en Gmail");
        System.err.println("• Contraseña: @OPINTO22 debe ser correcta");
        System.err.println("• ¿Es el dominio personalizado de Gmail?");
        System.err.println("");
        System.err.println("🔒 PASO 2: Verificar políticas de seguridad Gmail");
        System.err.println("• Si tiene 2FA: Generar contraseña de aplicación");
        System.err.println("• Si NO tiene 2FA: Habilitar 'Aplicaciones menos seguras'");
        System.err.println("• Verificar que la cuenta no esté bloqueada");
        System.err.println("");
        System.err.println("🌐 PASO 3: Configuración de red/firewall");
        System.err.println("• Puerto 587 (STARTTLS) debe estar abierto");
        System.err.println("• Puerto 465 (SSL) debe estar abierto");
        System.err.println("• smtp.gmail.com debe ser accesible");
        System.err.println("");
        System.err.println("🧪 PASO 4: Links importantes:");
        System.err.println("• 2FA: https://myaccount.google.com/security");
        System.err.println("• Apps menos seguras: https://myaccount.google.com/lesssecureapps");
        System.err.println("• Desbloqueo: https://accounts.google.com/DisplayUnlockCaptcha");
    }    /**
     * Prueba la conectividad con Gmail sin enviar correo
     */
    public boolean probarConectividad() {
        System.out.println("=== DIAGNÓSTICO COMPLETO DE CONECTIVIDAD GMAIL ===");
        System.out.println("CUENTA: opinto@cpt-soft.com");
        System.out.println("SERVIDOR: smtp.gmail.com");
        
        boolean conectividad = false;
          // 1. Intentar con STARTTLS (puerto 587) - más compatible
        System.out.println("\n1️⃣ Probando STARTTLS puerto 587...");
        try {
            if (probarConfiguracionSTARTTLS()) {
                System.out.println("✅ STARTTLS 587 - CONECTIVIDAD OK");
                conectividad = true;
            }
        } catch (Exception e) {
            System.err.println("❌ STARTTLS 587 falló: " + e.getMessage());
        }
        
        // 2. Si STARTTLS falla, probar configuración SMTP básica (puerto 587 sin TLS)
        if (!conectividad) {
            System.out.println("\n2️⃣ Probando SMTP básico puerto 587 (sin STARTTLS)...");
            try {
                if (probarConfiguracionSMTPBasico()) {
                    System.out.println("✅ SMTP básico 587 - CONECTIVIDAD OK");
                    conectividad = true;
                }
            } catch (Exception e) {
                System.err.println("❌ SMTP básico falló: " + e.getMessage());
            }
        }
        
        // 3. Probar SSL como última alternativa
        if (!conectividad) {
            System.out.println("\n3️⃣ Probando SSL puerto 465...");
            try {
                if (probarConfiguracionSSL()) {
                    System.out.println("✅ SSL 465 - CONECTIVIDAD OK");
                    conectividad = true;
                }
            } catch (Exception e) {
                System.err.println("❌ SSL 465 falló: " + e.getMessage());
            }
        }
        
        if (!conectividad) {
            System.err.println("\n❌ NINGUNA CONFIGURACIÓN FUNCIONÓ");
            mostrarInstruccionesGmail();
        } else {
            System.out.println("\n✅ AL MENOS UNA CONFIGURACIÓN FUNCIONA");        }
        
        return conectividad;
    }    private boolean probarConfiguracionSSL() {
        System.out.println("Probando SSL (smtp.gmail.com:465)...");
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(465);
            mailSender.setUsername("opinto@cpt-soft.com");
            mailSender.setPassword("@OPINTO22");
            
            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.starttls.enable", "false");
            props.put("mail.smtp.connectiontimeout", "30000");
            props.put("mail.smtp.timeout", "30000");
            
            mailSender.testConnection();
            return true;} catch (Exception e) {
            System.err.println("SSL 465 falló: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            return false;
        }
    }    /**
     * Prueba configuración SMTP básica puerto 587 SIN STARTTLS
     * Para Gmail (normalmente no necesario, pero para diagnóstico)
     */
    private boolean probarConfiguracionSMTPBasico() {
        System.out.println("Probando SMTP BÁSICO (smtp.gmail.com:587) - SIN STARTTLS");
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);
            mailSender.setUsername("opinto@cpt-soft.com");
            mailSender.setPassword("@OPINTO22");
            
            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            
            // CONFIGURACIÓN SMTP BÁSICA - SIN TLS/SSL
            props.put("mail.smtp.starttls.enable", "false");  // NO usar STARTTLS
            props.put("mail.smtp.ssl.enable", "false");       // NO usar SSL
            
            // Timeouts básicos
            props.put("mail.smtp.connectiontimeout", "30000");
            props.put("mail.smtp.timeout", "30000");
            
            System.out.println("Configuración: SMTP plano sin encriptación (solo para diagnóstico)");
            
            mailSender.testConnection();
            return true;
        } catch (Exception e) {
            System.err.println("SMTP básico 587 falló: " + e.getClass().getSimpleName() + " - " + e.getMessage());            if (e.getMessage() != null && e.getMessage().contains("Username and Password not accepted")) {
                System.err.println("✅ CONECTIVIDAD OK: El problema SÍ es de autenticación");
                System.err.println("   Gmail acepta conexión pero rechaza credenciales");
            }
            
            return false;
        }
    }    private boolean probarConfiguracionSTARTTLS() {
        System.out.println("Probando STARTTLS (smtp.gmail.com:587) - GMAIL");
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);  // Puerto STARTTLS estándar
            mailSender.setUsername("opinto@cpt-soft.com");
            mailSender.setPassword("@OPINTO22");
            
            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            
            // Configuración STARTTLS para Gmail
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.starttls.required", "true");  // Gmail requiere TLS
            
            // TLS 1.2 para Gmail
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            
            // Trust específico de Gmail
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            
            // Timeouts estándar
            props.put("mail.smtp.connectiontimeout", "30000");
            props.put("mail.smtp.timeout", "30000");
            
            System.out.println("Configuración TLS: TLSv1.2, STARTTLS requerido para Gmail");
            
            mailSender.testConnection();
            return true;
        } catch (Exception e) {
            System.err.println("STARTTLS 587 falló: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            
            // Diagnóstico específico para TLS
            if (e.getMessage() != null) {
                String mensaje = e.getMessage().toLowerCase();
                if (mensaje.contains("could not convert socket to tls")) {
                    System.err.println("🚨 PROBLEMA TLS DETECTADO:");
                    System.err.println("   Gmail rechaza la negociación TLS");
                    System.err.println("   Intentando configuración aún más básica...");
                } else if (mensaje.contains("username and password not accepted")) {
                    System.err.println("🚨 PROBLEMA DE AUTENTICACIÓN:");
                    System.err.println("   TLS OK, pero credenciales rechazadas");
                }
            }
            
            return false;
        }
    }
    
    /**
     * Prueba credenciales de Gmail personalizadas
     */
    public boolean probarCredencialesPersonalizadas(String email, String password) {
        System.out.println("=== PROBANDO CREDENCIALES PERSONALIZADAS ===");
        System.out.println("Email: " + email);
        System.out.println("Password: " + (password != null ? "***" : "null"));
        
        boolean funciona = false;
        
        // 1. Probar STARTTLS
        System.out.println("\n1️⃣ Probando STARTTLS 587 con credenciales personalizadas...");
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);
            mailSender.setUsername(email);
            mailSender.setPassword(password);
            
            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2 TLSv1.3");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.connectiontimeout", "30000");
            props.put("mail.smtp.timeout", "30000");
            props.put("mail.debug", "true");
            
            mailSender.testConnection();
            System.out.println("✅ STARTTLS 587 funciona con credenciales personalizadas");
            funciona = true;
        } catch (Exception e) {
            System.err.println("❌ STARTTLS falló: " + e.getMessage());
        }
        
        // 2. Probar SSL si STARTTLS falla
        if (!funciona) {
            System.out.println("\n2️⃣ Probando SSL 465 con credenciales personalizadas...");
            try {
                JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
                mailSender.setHost("smtp.gmail.com");
                mailSender.setPort(465);
                mailSender.setUsername(email);
                mailSender.setPassword(password);
                
                Properties props = mailSender.getJavaMailProperties();
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.ssl.enable", "true");
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                props.put("mail.smtp.ssl.protocols", "TLSv1.2 TLSv1.3");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.starttls.enable", "false");
                props.put("mail.smtp.connectiontimeout", "30000");
                props.put("mail.smtp.timeout", "30000");
                props.put("mail.debug", "true");
                
                mailSender.testConnection();
                System.out.println("✅ SSL 465 funciona con credenciales personalizadas");
                funciona = true;
            } catch (Exception e) {
                System.err.println("❌ SSL falló: " + e.getMessage());
            }
        }
        
        if (!funciona) {
            System.err.println("\n❌ CREDENCIALES PERSONALIZADAS NO FUNCIONAN");
            System.err.println("Verifica:");
            System.err.println("• Email correcto: " + email);
            System.err.println("• Contraseña correcta o contraseña de aplicación");
            System.err.println("• Configuración de seguridad de Gmail");
        }
        
        return funciona;
    }
}
