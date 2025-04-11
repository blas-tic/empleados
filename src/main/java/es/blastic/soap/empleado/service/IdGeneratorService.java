package es.blastic.soap.empleado.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class IdGeneratorService {
    
    private static final AtomicLong counter = new AtomicLong();
    private final LogContextService logContextService;
    
    public IdGeneratorService(LogContextService logContextService) {
        this.logContextService = logContextService;
    }

    public String generarIdPeticion() {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String idPeticion = null;
        String hostName = null;
        try {
            hostName = InetAddress.getLocalHost().getHostName().toUpperCase();
            idPeticion = hostName  + "_" + fecha + "_" + UUID.randomUUID().toString().replace("-", "").toUpperCase();
        } catch (UnknownHostException e) {
            // el nombre del host no se muestra
            idPeticion = fecha + "_" + UUID.randomUUID().toString().replace("-", "").toUpperCase();
        }
        return idPeticion;
    }
    
    public String generateUniqueRequestId() {
        long timestamp = System.currentTimeMillis();
        long count = counter.incrementAndGet();
        String requestId = timestamp + "-" + count + "-" + UUID.randomUUID().toString().substring(0, 8);
        
        // Almacenar el ID en el contexto
        logContextService.setRequestId(requestId);
        
        return requestId;
    }
}
