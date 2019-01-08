/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal1;

import datos.Factura;
import datos.FacturaDetalle;
import datos.TipoFactura;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import proyectofecha1.FechasLib;



class ComparadorFactura implements Comparator {

    @Override
    public int compare(Object t, Object t1) {
       return ((Factura) t).getFecha().compareTo(((Factura) t1).getFecha());
    }
    
} 

public class ProyectoFinal1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Factura factura;
        List<FacturaDetalle> listaFacturaDetalles; 
        List<Factura> listaFacturas= new ArrayList<Factura>();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date hoy = new Date();
        
        factura = new Factura("000001", formatoFecha.parse("01/05/2014"),"Pepito" );
        listaFacturaDetalles =  new ArrayList<FacturaDetalle>();
        listaFacturaDetalles.add(new FacturaDetalle("producto 1",100.0f,0.5f,true));
        listaFacturaDetalles.add(new FacturaDetalle("producto 2",50.0f,1.5f,false));
        factura.setListaDetalles(listaFacturaDetalles);
        factura.setTipo(TipoFactura.FACTURA);
        listaFacturas.add(factura);
        
        factura = new Factura("000002", formatoFecha.parse("01/03/2014"),"Juanito" );
        listaFacturaDetalles =  new ArrayList<FacturaDetalle>();
        listaFacturaDetalles.add(new FacturaDetalle("producto 1.1",10.0f,45.5f,false));
        listaFacturaDetalles.add(new FacturaDetalle("producto 2.1",6.0f,100.5f,true));
        factura.setListaDetalles(listaFacturaDetalles);
        factura.setTipo(TipoFactura.NOTA_VENTA);
        listaFacturas.add(factura);
        
        Collections.sort(listaFacturas, new ComparadorFactura());
        
        // Recorrer la lista y escribir el archivo 
        FileOutputStream fOS = new FileOutputStream("archivoFactura.bin");
        ObjectOutputStream oW = new ObjectOutputStream(fOS);
        for (Factura f : listaFacturas) {
            oW.writeObject(f);
            oW.flush();
        }
        oW.close();
        
        listaFacturas.clear();
        
        FileInputStream iS = new FileInputStream("archivoFactura.bin");
        ObjectInputStream oR = new ObjectInputStream(iS);
        
        while (iS.available() > 0) {
                listaFacturas.add((Factura) oR.readObject());  
        }
        oR.close();
        
        for (Factura f: listaFacturas) {
            System.out.println(f.getNumero()+" "+formatoFecha.format(f.getFecha())+" "+f.getCliente()+" Dias:"+ FechasLib.getDiferencia(hoy, f.getFecha()).get("dias") );
            System.out.println("Descripcion; "+f.getTipo().getDescripcion());
            System.out.println("-------------------------------------");
            for (FacturaDetalle fd: f.getListaDetalles()) {
                System.out.print(String.format("%5.2f\t%-40s\t%8.2f\t%8.2f\t%8.2f\n", 
                            fd.getCantidad(),fd.getDescripcion(), fd.getValorUnitario(), fd.getValores().get("iva"), fd.getValores().get("total") ));
                

            }
            System.out.println("Subotal IVA "+ f.getCalculos().get("sub_total_iva") );
            System.out.println("Subotal 0 "+ f.getCalculos().get("sub_total_0") );
            System.out.println("      IVA "+ f.getCalculos().get("iva") );
            System.out.println("    TOTAL "+ f.getCalculos().get("total") );
            System.out.println("===================================================");
        }
        
        
    }
}
