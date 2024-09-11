public class App {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException {

        /*
         * Creamos un objeto de la clase Coche y después mostramos su estado, invocando
         * al método toString() con esta línea:
         * 
         * System.out.println(miCoche);
         * 
         * Recuerda que toString() es el método al que se invoca por defecto cuando no
         * se especifica el método a ejecutar.
         */
        Coche miCoche = new Coche("Ford", "Fiesta");
        System.out.println(miCoche);

        // Accediendo a los datos de la anotación del método.
        /*
         * La expresión miCoche.getClass() devuelve un objeto de tipo Class que provee
         * información sobre la clase a la que pertenece el objeto. De esta forma,
         * estamos también obteniendo información sobre el método acelerar(), usando la
         * expresión getMethod("acelerar"). Concretamente, estamos accediendo a los
         * datos de la anotación.
         */
        Autor a1 = miCoche.getClass().getMethod("acelerar").getAnnotation(Autor.class);
        System.out.println("Nombre autor: " + a1.nombre());
        System.out.println("Dirección autor: " + a1.direccion());

        // Accediendo a los datos de la anotación de la clase.
        Autor a2 = miCoche.getClass().getAnnotation(Autor.class);
        System.out.println("Nombre autor: " + a2.nombre());
        System.out.println("Dirección autor: " + a2.direccion());
    }

}
