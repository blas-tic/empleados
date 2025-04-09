package es.blastic.soap.empleado.repository;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.blastic.soap.empleado.Utils.EmpleadoRowMapper;
import es.blastic.soap.empleado.model.Empleado;

@Repository
public class EmpleadoRepository {

    private final JdbcTemplate jdbcTemplate;
    private final EmpleadoRowMapper empleadoRowMapper;

    public EmpleadoRepository(JdbcTemplate jdbcTemplate, EmpleadoRowMapper er) {
        this.jdbcTemplate = jdbcTemplate;
        this.empleadoRowMapper = er;
    }

    List<Empleado> buscarPorNombre(String busqueda) {
        String sql = "SELECT e.id, e.nombre, e.apellidos, e.email, e.fcontrato from empleados where CONCAT(e.nombre, ' ', e.apellidos) LIKE %:busqueda%";
        return jdbcTemplate.query(sql, empleadoRowMapper, busqueda);
    }

    Empleado buscarPorId(int id) {
        String sql = "SELECT e.id, e.nombre, e.apellidos, e.email, e.fcontrato from empleados where e.id = ?";
        return jdbcTemplate.queryForObject(sql, empleadoRowMapper, id);
    }

    Empleado buscarPorEmail(String mail) {
        String sql = "SELECT e.id, e.nombre, e.apellidos, e.email, e.fcontrato from empleados where e.email = ?";
        return jdbcTemplate.queryForObject(sql, empleadoRowMapper, mail);
    }

    Empleado crearEmpleado(Empleado emp) {
        String sql = "INSERT INTO empleado (id, nombre, apellidos, email, fcontrato) VALUES (?, ?, ?, ?, ?)";
        int insertados = 0;
        try {
            insertados = jdbcTemplate.update(sql,
            empleadoRowMapper, 
            emp.getId(),
            emp.getNombre(),
            emp.getApellidos(),
            emp.getEmail(),
            emp.getFcontrato()
            );
        } catch (DuplicateKeyException e) {
            String errorMessage = e.getMostSpecificCause().getMessage();
            if (errorMessage.contains("PRIMARY KEY") || errorMessage.contains("unique constraint")) {
                throw crearSoapFault("crearEmpleado", e.getMostSpecificCause()., "Error al buscar empleado por id: " + e.getLocalizedMessage());
            }
        }
    }

}
