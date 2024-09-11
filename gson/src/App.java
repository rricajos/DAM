
import java.util.Scanner;



import com.google.gson.Gson;

public class App {

    public static void main(String[] args) {
        System.out.println("[ 1 ] Persona.java context -> GSON data");
        System.out.println("[ 2 ] GSON context -> JAVA data");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {
   
            case "1":
                JAVAtoGSON();
            case "2":
                GSONtoJAVA();
        }
        scanner.close();
    }

    public static void JAVAtoGSON() {
        try {
            GrupoPersonas grupo = new GrupoPersonas();
            grupo.getPersonas().add(new Persona(1, "Homer", "Simpson", 48));
            grupo.getPersonas().add(new Persona(2, "Lisa", "Simpson", 12));
            grupo.getPersonas().add(new Persona(3, "Bart", "Simpson", 13));
            grupo.getPersonas().add(new Persona(4, "Marge", "Simpson", 40));

            Gson gson = new Gson();

            /*
             * El método toJson() devuelve un objeto String con el contenido del documento
             * JSON que simplemente hemos mostrado en pantalla, pero también podríamos
             * guardarlo en disco, transferirlo a través de la red o cualquier otra cosa que
             * deseemos hacer con él.
             */
            String json = gson.toJson(grupo);

            System.out.println(json);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void GSONtoJAVA() {

        try {
            String json = "{'idPersona':5,'nombre':'Montgomery','apellido':'Burns','edad':80}";
            Gson gson = new Gson();
            Persona p = gson.fromJson(json, Persona.class);
            System.out.println(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());1
        }
    }
}