package es.blastic.soap.empleado.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import es.blastic.soap.empleado.service.EmpleadoService;
import es.blastic.soap.empleado.service.IdGeneratorService;

@Endpoint
public class EmpleadoEndpoint {
    private static final String NAMESPACE_URI = "http://blastic.es/soap/empleado";

    @Autowired
    private EmpleadoService empleadoService;
    
    @Autowired
    private IdGeneratorService idGeneratorService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "EmpleadoIdRequest")
    @ResponsePayload
    public EmpleadoIdResponse processRequest(@RequestPayload EmpleadoIdRequest request) {
        // Genera ID único para la petición
        String requestId = idGeneratorService.generarIdPeticion();
        
        // Log de la petición
        log.info("Procesando petición con ID: {}", requestId);
        
        // Procesar la petición usando el servicio de negocio
        EmpleadoIdResponse response = empleadoService.processRequest(request, requestId);
        
        return response;
    }
}