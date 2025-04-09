package es.blastic.soap.empleado.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import es.blastic.soap.empleado.model.Empleado;
import es.blastic.soap.empleado.repository.EmpleadoRepository;

public class EmpleadoService {

    private final EmpleadoRepository repository;

    @Autowired
    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public Empleado buscarPorId(Integer id) throws SoapFault {
        try {
            Optional<Empleado> empleado = repository.buscarPorId(id);
            return empleado.orElse(null);
        } catch (Exception e) {
            throw crearSoapFault("buscarPorId", 1001, "Error al buscar empleado por id: " + e.getLocalizedMessage());
        }
    }

    public List<Empleado> buscarPorNombre(String busqueda) throws SoapFault {
        try {
            return empleadoRepository.buscarPorNombre(busqueda);
        } catch (Exception e) {
            throw crearSoapFault("buscarPorNombre", 1002, "Error al buscar empleados: " + e.getMessage());
        }

    }

}
