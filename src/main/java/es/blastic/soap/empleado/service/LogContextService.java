package es.blastic.soap.empleado.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class LogContextService {
    private static final Logger logger = LoggerFactory.getLogger(LogContextService.class);
    private static final ThreadLocal<String> REQUEST_ID = new ThreadLocal<>();
    
    @Value("${spring.application.name}")
    private String applicationName;
    
    @Value("${spring.profiles.active:default}")
    private String environment;
    
    private String hostName;
    
    public LogContextService() {
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            hostName = "unknown-host";
            logger.warn("No se pudo determinar el hostname", e);
        }
    }
    
    public void setRequestId(String requestId) {
        REQUEST_ID.set(requestId);
        
        // Configurar MDC para SLF4J con información útil para ELK
        MDC.put("requestId", requestId);
        MDC.put("application", applicationName);
        MDC.put("environment", environment);
        MDC.put("hostName", hostName);
        MDC.put("traceId", requestId); // Para compatibilidad con sistemas de rastreo
    }
    
    public void setMethodContext(String className, String methodName) {
        MDC.put("className", className);
        MDC.put("methodName", methodName);
    }
    
    public void addCustomField(String key, String value) {
        if (value != null) {
            MDC.put(key, value);
        }
    }
    
    public String getRequestId() {
        return REQUEST_ID.get();
    }
    
    public void clear() {
        REQUEST_ID.remove();
        MDC.clear();
    }
}
