//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2025.04.11 a las 01:11:03 PM CEST 
//


package es.blastic.soap.empleado.gen;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SoapFault complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SoapFault"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="maquina" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="operacion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="mensajeError" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoapFault", propOrder = {
    "maquina",
    "operacion",
    "codigoError",
    "mensajeError"
})
public class SoapFault {

    @XmlElement(required = true)
    protected String maquina;
    @XmlElement(required = true)
    protected String operacion;
    protected int codigoError;
    @XmlElement(required = true)
    protected String mensajeError;

    /**
     * Obtiene el valor de la propiedad maquina.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaquina() {
        return maquina;
    }

    /**
     * Define el valor de la propiedad maquina.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaquina(String value) {
        this.maquina = value;
    }

    /**
     * Obtiene el valor de la propiedad operacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * Define el valor de la propiedad operacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperacion(String value) {
        this.operacion = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoError.
     * 
     */
    public int getCodigoError() {
        return codigoError;
    }

    /**
     * Define el valor de la propiedad codigoError.
     * 
     */
    public void setCodigoError(int value) {
        this.codigoError = value;
    }

    /**
     * Obtiene el valor de la propiedad mensajeError.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeError() {
        return mensajeError;
    }

    /**
     * Define el valor de la propiedad mensajeError.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeError(String value) {
        this.mensajeError = value;
    }

}
