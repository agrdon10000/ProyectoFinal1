/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JAVA
 */
public class Factura implements Serializable {
   private String numero;
   private Date fecha;
   private String cliente;
   private List<FacturaDetalle> listaDetalles = new ArrayList<FacturaDetalle>();
   private TipoFactura tipo;

    public Factura() {
    }

    public Factura(String numero, Date fecha, String cliente) {
        this.numero = numero;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public List<FacturaDetalle> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<FacturaDetalle> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public TipoFactura getTipo() {
        return tipo;
    }

    public void setTipo(TipoFactura tipo) {
        this.tipo = tipo;
    }
   
    
    
    public Map<String, Float> getCalculos() {
        float total = 0.0f;
        float sub_total_0 = 0.0f;
        float sub_total_iva = 0.0f;
        float iva = 0.0f;
        float valor = 0.0f;
        
        Map<String, Float> resultado = new HashMap<String, Float>();
        
        resultado.put("total", 0.0f);
        resultado.put("sub_total_0", 0.0f);
        resultado.put("sub_total_iva", 0.0f);
        resultado.put("iva", 0.0f);
        
        
        for (FacturaDetalle fd: listaDetalles) {
           valor = fd.getCantidad() * fd.getValorUnitario();
           if (fd.getIva()) {
               iva +=  (valor + (valor * 0.12f));
               sub_total_iva += valor;
           }  
           else sub_total_0 += valor;
        }
        resultado.put("total", sub_total_0 + sub_total_iva + iva );
        resultado.put("sub_total_0", sub_total_0);
        resultado.put("sub_total_iva", sub_total_iva);
        resultado.put("iva", iva);
        
        return resultado;
    }
   
   
}
