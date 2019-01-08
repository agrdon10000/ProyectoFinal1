/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author JAVA
 */
public enum TipoFactura {
    FACTURA("Para declarar sin activos"),
    NOTA_VENTA("Con fines tributarios");
    
    private String descripcion;
    
    private TipoFactura (String s) {
        descripcion = s;
    }    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
