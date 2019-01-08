package proyectofecha1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FechasLib {

    

    public FechasLib() {
        // TODO Auto-generated constructor stub
    }

    public static final String[] MESES = {
        "Enero",
        "Febrero",
        "Marzo",
        "Abril",
        "Mayo",
        "Junio",
        "Julio",
        "Agosto",
        "Septiembre",
        "Octubre",
        "Noviembre",
        "Diciembre"
    };
    public static final String[] DIA_SEM = {
        "Domingo",
        "Lunes",
        "Martes",
        "Miercoles",
        "Jueves",
        "Viernes",
        "Sabado"
        
    };
    
    public static String fechaLarga(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        
                
        return DIA_SEM[cal.get(Calendar.DAY_OF_WEEK)-1] + ", " +
               cal.get(Calendar.DATE) + " de " +
               MESES[cal.get(Calendar.MONTH)] + " de " +
               cal.get(Calendar.YEAR);
    }
    
    public static boolean esFechaValida(Integer a, Integer m, Integer d) {
        boolean valida = a > 1800 && 1 <= m && m <= 12;
        if (valida) {
            switch (m) {
                case 4:
                case 6:
                case 9:
                case 11:
                    valida = 1 <= d && d <= 30;
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    valida = 1 <= d && d <= 31;
                    break;
                case 2:
                    if (!esBisiesto(a)) {
                        valida = 1 <= d && d <= 28;
                    } else {
                        valida = 1 <= d && d <= 29;
                    }
                    break;
            }
        }
        return valida;
    }

    /**
     * Retorna si un año es bisiesto.
     *
     * @param a año
     * @return cierto si a es bisiesto, esto es, se cumple (a % 4 == 0 && !(a %
     * 100 == 0 && a % 400 == 0))
     */
    private static boolean esBisiesto(int a) {
        return (a % 4 == 0 && !(a % 100 == 0 && a % 400 == 0));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map getDiferencia(java.util.Date fecha1, java.util.Date fecha2) {
        java.util.Date fechaMayor;
        java.util.Date fechaMenor;
        Map resultadoMap = new HashMap();

        /* Verificamos cual es la mayor de las dos fechas, para no tener sorpresas al momento
         * de realizar la resta.
         */
        if (fecha1.compareTo(fecha2) > 0) {
            fechaMayor = fecha1;
            fechaMenor = fecha2;
        } else {
            fechaMayor = fecha2;
            fechaMenor = fecha1;
        }

        //los milisegundos
        long diferenciaMils = fechaMayor.getTime() - fechaMenor.getTime();

        //obtenemos los segundos
        long segundos = diferenciaMils / 1000;

        //obtenemos las horas
        long horas = segundos / 3600;

        // obtenemos minutos
        long minutos = segundos / 60;

        // calcular la diferencia en dias
        long dias = diferenciaMils / (24 * 60 * 60 * 1000);

        // Instanciar dos calendarios
        Calendar f1 = Calendar.getInstance();
        Calendar f2 = Calendar.getInstance();

        // Asignar las fechas
        f1.setTime(fechaMenor);
        f2.setTime(fechaMayor);



        // Calcular anos
        Integer anos = f2.get(Calendar.YEAR) - f1.get(Calendar.YEAR);

        // Calcular meses
        Integer meses = anos * 12 + f2.get(Calendar.MONTH) - f1.get(Calendar.MONTH);


        //ponemos los resultados en un mapa :-)

        resultadoMap.put("anos", Integer.toString(anos));
        resultadoMap.put("meses", Integer.toString(meses));
        resultadoMap.put("dias", Long.toString(dias));
        resultadoMap.put("horas", Long.toString(horas));
        resultadoMap.put("minutos", Long.toString(minutos));
        resultadoMap.put("segundos", Long.toString(segundos));

        return resultadoMap;
    }

    /**
     * @param args
     */
    @SuppressWarnings({"unused", "rawtypes"})
    public static void main(String[] args) {
        
        System.out.println("Fecha Larga: "+fechaLarga(new Date()));
        
        
        // TODO Auto-generated method stub
		/*
         java.util.Date utilDate = new java.util.Date();
         // Toma fecha actual usando java.util.Date y retorna un long hacia java.sql.Date
         java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
         System.out.println("Usando util.Date " + utilDate);
         System.out.println("Usando getTime() " + utilDate.getTime());
         System.out.println("Usando sql.Date " + sqlDate);
         */

        // Metodo 1 
        Calendar calendar1 = Calendar.getInstance();

        // mostramos la fecha actual
        int y = calendar1.get(Calendar.YEAR);
        int m = calendar1.get(Calendar.MONTH);
        int d = calendar1.get(Calendar.DATE);

        // modificamos atributos
        calendar1.set(Calendar.YEAR, 2020);
        // OJO: Recueda que los valores de los meses comienzan por 0.
        calendar1.set(Calendar.MONTH, 00); // Enero
        calendar1.set(Calendar.DATE, 30);

        // Asignamos ano, mes y dia.
        calendar1.set(2020, 02, 30);
        // Asignamos a�o, mes, d�a, horas y minutos.
        calendar1.set(2020, 02, 30, 19, 00);
        // Asignamos ano, mes, dia, horas, minutos y segundos.
        calendar1.set(2020, 02, 30, 19, 00, 55);

        // Sumamos 30 minutos a la fecha actual.
        calendar1.add(Calendar.MINUTE, 30);
        // Sumamos 100 d�as a la fecha actual.
        calendar1.add(Calendar.DATE, 100);
        // Restamos 10 a�os a la fecha actual.
        calendar1.add(Calendar.YEAR, -10);



        // metodo 2
        Calendar calendar2 = new GregorianCalendar();

        // Establecer formato de fecha para impresion 
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Formateando con SimpleDateFormat");
        System.out.println(formato.format(calendar1.getTime()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar2.getTime()));

        /*
         calendar1.set(2013, 00, 01);
         calendar1.add(Calendar.DATE, 90);
         System.out.println(formato.format(calendar1.getTime()));

         calendar2.set(2013, 04, 03);

         // Buscar la diferencia entre fechas 
         Map resultado = getDiferencia(calendar1.getTime(), calendar2.getTime());
		
         System.out.println("Diferencia en anos " + resultado.get("anos"));
         System.out.println("Diferencia en meses " + resultado.get("meses"));
         System.out.println("Diferencia en dias " + resultado.get("dias"));
         System.out.println("Diferencia en horas " + resultado.get("horas"));
         */

    }
}
