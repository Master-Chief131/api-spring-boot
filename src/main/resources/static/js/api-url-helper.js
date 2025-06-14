/**
 * Utilidad para manejar URLs de API que funcionen tanto en desarrollo como en producción (Tomcat)
 * 
 * En desarrollo: http://localhost:8080/api/...
 * En Tomcat:     http://localhost:8088/Api-Demo/api/...
 */

class ApiUrlHelper {
    constructor() {
        this.baseUrl = this.detectBaseUrl();
    }

    /**
     * Detecta automáticamente la URL base según el entorno
     */
    detectBaseUrl() {
        const currentPath = window.location.pathname;
        const currentHost = window.location.origin;
        
        // Si la URL contiene /Api-Demo/, estamos en Tomcat
        if (currentPath.startsWith('/Api-Demo/')) {
            return `${currentHost}/Api-Demo`;
        }
        
        // Si estamos en puerto 8088 pero sin /Api-Demo/, asumir Tomcat
        if (window.location.port === '8088' && !currentPath.startsWith('/Api-Demo/')) {
            return `${currentHost}/Api-Demo`;
        }
        
        // Desarrollo local o servidor embebido
        return currentHost;
    }

    /**
     * Construye una URL de API completa
     * @param {string} endpoint - El endpoint de la API (ej: '/api/usuarios/validar-email')
     * @returns {string} URL completa
     */
    buildApiUrl(endpoint) {
        // Asegurar que el endpoint empiece con /
        if (!endpoint.startsWith('/')) {
            endpoint = '/' + endpoint;
        }
        
        return `${this.baseUrl}${endpoint}`;
    }

    /**
     * Construye una URL con parámetros de consulta
     * @param {string} endpoint - El endpoint base
     * @param {Object} params - Parámetros de consulta
     * @returns {string} URL completa con parámetros
     */
    buildApiUrlWithParams(endpoint, params) {
        const baseUrl = this.buildApiUrl(endpoint);
        
        if (!params || Object.keys(params).length === 0) {
            return baseUrl;
        }
        
        const searchParams = new URLSearchParams();
        Object.entries(params).forEach(([key, value]) => {
            if (value !== null && value !== undefined) {
                searchParams.append(key, value);
            }
        });
        
        return `${baseUrl}?${searchParams.toString()}`;
    }

    /**
     * Información del entorno actual
     */
    getEnvironmentInfo() {
        return {
            baseUrl: this.baseUrl,
            currentUrl: window.location.href,
            contextPath: window.location.pathname,
            isProduction: this.baseUrl.includes('/Api-Demo'),
            isDevelopment: !this.baseUrl.includes('/Api-Demo'),
            port: window.location.port,
            host: window.location.hostname
        };
    }

    /**
     * Logs información de debugging
     */
    logEnvironmentInfo() {
        const info = this.getEnvironmentInfo();
        console.log('=== API URL Helper - Environment Info ===');
        console.log('Base URL:', info.baseUrl);
        console.log('Current URL:', info.currentUrl);
        console.log('Context Path:', info.contextPath);
        console.log('Is Production (Tomcat):', info.isProduction);
        console.log('Is Development:', info.isDevelopment);
        console.log('Port:', info.port);
        console.log('Host:', info.host);
        console.log('==========================================');
    }
}

// Crear instancia global
const apiUrlHelper = new ApiUrlHelper();

// Función de conveniencia global
function buildApiUrl(endpoint, params = null) {
    if (params) {
        return apiUrlHelper.buildApiUrlWithParams(endpoint, params);
    }
    return apiUrlHelper.buildApiUrl(endpoint);
}

// Log información al cargar (solo en desarrollo)
if (apiUrlHelper.getEnvironmentInfo().isDevelopment) {
    apiUrlHelper.logEnvironmentInfo();
}

// Exportar para uso en módulos (si es necesario)
if (typeof module !== 'undefined' && module.exports) {
    module.exports = { ApiUrlHelper, apiUrlHelper, buildApiUrl };
}
