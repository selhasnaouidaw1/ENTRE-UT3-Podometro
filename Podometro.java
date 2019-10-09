/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author  - Sara El Hasnaoui - 
 * 
 */
public class Podometro {

    //Constantes
    final char HOMBRE = 'H';
    final char MUJER = 'M';
    final double ZANCADA_HOMBRE = 0.45;
    final double ZANCADA_MUJER = 0.41;
    final int SABADO = 6;
    final int DOMINGO = 7;
    //Atributos
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private double totalPasosLaborables;
    private double totalPasosSabado;
    private double totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        altura = 0;
        sexo = 'M';
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;

    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;

    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * 
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;

        if (queSexo == 'M'){
            longitudZancada = Math.floor(queAltura * 0.45);//si es mujer que multiplique la altura por 0,45
        }
        else if (queSexo == 'H') {
            longitudZancada = Math.ceil(queAltura * 0.41);//si es hombre que multiplique la altura por 0,41
        }

    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFina – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        if (dia <= 5) { //de lunes a viernes
            totalPasosLaborables += pasos;
        } else { //fin de semana
            if (dia == 6) {
                totalPasosSabado += pasos;
            } else {
                totalPasosDomingo += pasos;
            }

            totalDistanciaFinSemana += pasos * longitudZancada;
        }

        totalDistanciaSemana += pasos * longitudZancada;

        if (horaFin > 2100) { //si se esta andando mas tarde de las 21 horas
            caminatasNoche++;
        }

        int quehoraInicio = horaInicio / 100; 
        int queminutosInicio = horaInicio % 100; 
        int quehoraFin = horaFin / 100; 
        int queminutosFin = horaFin % 100; 

        int hora = horaFin - horaInicio; //las horas andadas
        int minutos = queminutosFin - queminutosInicio; //los minutos andados

        if (minutos < 0) { 
            minutos = minutos * -1;
            hora--;
        }

        int minuto = hora * 60 + minutos;

        tiempo += minuto;
    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     *  
     */
    public void printConfiguracion() {
        System.out.println("Configuración del podómetro");
        System.out.println("******");
        System.out.println("Altura: " + altura + " mtos");
        System.out.print("Sexo: ");

        if (sexo == 'M') {
            System.out.println("MUJER");
        } else if (sexo == 'H') {
            System.out.println("HOMBRE");
        }

        System.out.println("Longitud zancada: " + longitudZancada + " mtos");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     *  
     */
    public void printEstadísticas() {
        System.out.println("Estadisticas"); 
        System.out.println("Distancia recorrida toda la semana: " + totalDistanciaSemana + " Km");
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinSemana + " Km");
        System.out.println("Nº pasos dias laborables: " + totalPasosLaborables);
        System.out.println("Nº pasos SABADO: " + totalPasosSabado);
        System.out.println("Nº pasos DOMINGO: " + totalPasosDomingo);
        System.out.println("Nº caminatas realizadas a partir de las 21h: " + caminatasNoche);
        System.out.println("Tiempo total caminado en la semana: " + (tiempo / 60) + "h y " + (tiempo - (tiempo / 60)) + "m");
        System.out.println("Dias con más pasos caminados: " + diaMayorNumeroPasos());

    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String resultado = "";

        if (totalPasosLaborables >= totalPasosSabado && totalPasosLaborables >= totalPasosDomingo) {
            resultado = "LABORABLES";
        } else if (totalPasosSabado >= totalPasosLaborables && totalPasosSabado >= totalPasosDomingo) {
            resultado = "SABADO";
        } else if (totalPasosDomingo >= totalPasosLaborables && totalPasosDomingo >= totalPasosSabado) {
            resultado = "DOMINGO";
        }

        return resultado;
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
    // */    
    public void reset() {
        altura = 0;
        sexo = 'M';
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;

    }
}
