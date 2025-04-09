package es.blastic.soap.empleado.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import es.blastic.soap.empleado.model.Empleado;

public class EmpleadoRowMapper implements RowMapper<Empleado> {

    @Override
    public Empleado mapRow(ResultSet rs, int rowNum) throws SQLException {
        Empleado e = new Empleado();
        try {
            e.setId(rs.getInt("id"));
            e.setNombre(rs.getString("nombre"));
            e.setApellidos(rs.getString("apellidos"));
            e.setEmail(rs.getString("email"));
            String cadfecha = rs.getString("fcontrato");
            if (cadfecha != null)
                e.setFcontrato(LocalDateTime.parse(cadfecha));
        } catch (Exception e) {
            throw crearSoapFault("Empleado mapRow", 1002, "Error de conversión: " + e.getLocalizedMessage());
        }

        return e;
    }

}
