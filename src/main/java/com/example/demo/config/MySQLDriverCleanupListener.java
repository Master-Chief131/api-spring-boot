package com.example.demo.config;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Listener para limpiar correctamente el driver JDBC de MySQL cuando la
 * aplicación se detiene.
 * Esto previene memory leaks cuando se reinicia la aplicación en Tomcat sin
 * reiniciar el servidor.
 */
@Component
public class MySQLDriverCleanupListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        try {
            // Detener el thread de limpieza de conexiones abandonadas de MySQL
            AbandonedConnectionCleanupThread.checkedShutdown();

            // Desregistrar todos los drivers JDBC
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                try {
                    DriverManager.deregisterDriver(driver);
                    System.out.println("Driver JDBC desregistrado: " + driver);
                } catch (SQLException e) {
                    System.err.println("Error al desregistrar driver JDBC: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error durante la limpieza del driver MySQL: " + e.getMessage());
        }
    }
}
