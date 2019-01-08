/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JAVA
 */
public class FacturaDetalle implements Serializable {

    private String descripcion;
    private Float valorUnitario = 0.0f;
    private Float cantidad = 0.0f;
    private Boolean iva = false;

    public FacturaDetalle() {
    }

    public FacturaDetalle(String descripcion, Float cantidad,Float valorUnitario,  Boolean iva) {
        this.descripcion = descripcion;
        this.valorUnitario = valorUnitario;
        this.cantidad = cantidad;
        this.iva = iva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getIva() {
        return iva;
    }

    public void setIva(Boolean iva) {
        this.iva = iva;
    }
    
    public Map getValores() {
        Map<String, Float> resultado = new HashMap();
        float iva_valor;
        
        float valor = (cantidad * valorUnitario);
        iva_valor = (iva) ?  valor + (valor * 0.12f) : 0.0f;
    
        resultado.put("iva", iva_valor);
        resultado.put("total", valor);
        
      return resultado;
    }
    
}
