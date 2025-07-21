/**
 * Utilidad para manejar URLs de API que funcionen tanto en desarrollo como en producción (Tomcat)
 * 
 * En desarrollo: http://localhost:8080/api/...
 * En Tomcat:     http://localhost:8088/Api-[Nombre]/api/...
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
        
        // Buscar cualquier contexto que empiece con /Api-
        const apiContextMatch = currentPath.match(/^\/Api-[^\/]+/);
        if (apiContextMatch) {
            const contextPath = apiContextMatch[0];
            console.log('Contexto detectado automáticamente:', contextPath);
            return `${currentHost}${contextPath}`;
        }
        
        // Si estamos en puerto 8088 pero no detectamos contexto Api-, buscar en el DOM
        if (window.location.port === '8088') {
            // Intentar detectar desde otras URLs en la página
            const detectedContext = this.detectContextFromDOM();
            if (detectedContext) {
                console.log('Contexto detectado desde DOM:', detectedContext);
                return `${currentHost}${detectedContext}`;
            }
            
            // Fallback: Si no se puede detectar, usar Api-cptsoft por defecto para producción
            console.warn('No se pudo detectar el contexto automáticamente. Usando Api-cptsoft por defecto.');
            return `${currentHost}/Api-cptsoft`;
        }
        
        // Desarrollo local o servidor embebido
        console.log('Modo desarrollo detectado');
        return currentHost;
    }

    /**
     * Intenta detectar el contexto desde elementos del DOM o metadatos
     */
    detectContextFromDOM() {
        // Buscar en links, scripts o imágenes que contengan contextos Api-
        const elements = document.querySelectorAll('link[href*="/Api-"], script[src*="/Api-"], img[src*="/Api-"]');
        
        for (const element of elements) {
            const url = element.href || element.src;
            if (url) {
                const match = url.match(/\/Api-[^\/]+/);
                if (match) {
                    return match[0];
                }
            }
        }
        
        return null;
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
        const isApiContext = /\/Api-[^\/]+/.test(this.baseUrl);
        const contextMatch = this.baseUrl.match(/\/Api-[^\/]+/);
        
        return {
            baseUrl: this.baseUrl,
            currentUrl: window.location.href,
            contextPath: window.location.pathname,
            detectedContext: contextMatch ? contextMatch[0] : null,
            isProduction: isApiContext,
            isDevelopment: !isApiContext,
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
