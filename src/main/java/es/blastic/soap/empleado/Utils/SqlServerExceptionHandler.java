package es.blastic.soap.empleado.Utils;

import org.springframework.dao.DuplicateKeyException;
import java.sql.SQLException;

public class SqlServerExceptionHandler {

    public static void handleDuplicateKey(DuplicateKeyException e, String entityName) {
        Throwable rootCause = e.getMostSpecificCause();

        if (rootCause instanceof SQLException) {
            SQLException sqlEx = (SQLException) rootCause;

            // Manejo para SQL Server nativo
            if (sqlEx.getErrorCode() == 2627) {
                handleSqlServerDuplicate(sqlEx, entityName);
            }
            // Manejo para H2 en modo SQL Server
            else if (sqlEx.getErrorCode() == 23505 || "23505".equals(sqlEx.getSQLState())) {
                handleH2SqlServerModeDuplicate(sqlEx, entityName);
            }
            // Fallback para otros casos
            else {
                throw crearSoapFault("SQLException", 1010, e.getLocalizedMessage());
            }
        } else {
            throw crearSoapFault("SQLException", 1010, entityName + "-Clave Duplicada-" + e.getLocalizedMessage());
        }
    }

    private static void handleSqlServerDuplicate(SQLException sqlEx, String entityName) {
        // Extraer el nombre del constraint del mensaje
        String constraintName = extractBetween(sqlEx.getMessage(), "constraint '", "'");

        if (constraintName != null) {
            if (constraintName.startsWith("PK_")) {
                throw crearSoapFault("SQLException", 1015,
                        entityName + " ya existe en el sistema (clave primaria duplicada)");
            } else if (constraintName.startsWith("UQ_")) {
                throw crearSoapFault("SQLException", 1020,
                        " Violación de campo único en " + entityName + ": " + constraintName);

            }
        }
        throw crearSoapFault("SQLException", 1020, "Registro duplicado en " + entityName);
    }

    private static void handleH2SqlServerModeDuplicate(SQLException sqlEx, String entityName) {
        // H2 en modo SQL Server puede no incluir el nombre del constraint
        String message = sqlEx.getMessage().toLowerCase();

        if (message.contains("primary key") || message.contains("unique index")) {
            throw crearSoapFault("SQLException", 1025, entityName + " ya existe en el sistema (clave duplicada)");
        }
        throw crearSoapFault("SQLException", 1030, "Registro duplicado en " + entityName);
    }

    private static String extractBetween(String text, String start, String end) {
        try {
            int beginIndex = text.indexOf(start) + start.length();
            int endIndex = text.indexOf(end, beginIndex);
            return text.substring(beginIndex, endIndex);
        } catch (Exception e) {
            return null;
        }
    }
}