import java.io.File;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class App {

    public static void main(String[] args) {
        System.out.println("[ 0 ] GrupoPersonas.java context -> XML data");
        System.out.println("[ 1 ] Persona.java context -> XML data");
        System.out.println("[ 2 ] XML context -> JAVA data");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {
            case "0":
                JAVAStoXML();
            case "1":
                JAVAtoXML();
            case "2":
                XMLtoJAVA();
        }
        scanner.close();
    }

    public static void XMLtoJAVA() {
        try {
            JAXBContext contexto = JAXBContext.newInstance(Persona.class);
            Unmarshaller u = contexto.createUnmarshaller();
            File fichero = new File("Homer.xml");
            if (fichero.exists()) {
                Persona p = (Persona) u.unmarshal(fichero);
                System.out.println(p);
            } else {
                System.out.println("Fichero XML Homer.xml no encontrado");
            }

        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void JAVAtoXML() {
        JAXBContext contexto;
        try {
            /*
             * Obtiene el contexto asociado a la clase Persona. Provoca una excepción de
             * tipo
             * JAXBException si la clase Persona no cumple los requisitos para la
             * conversión a XML, es decir, contener las anotaciones necesarias y contar
             * con un constructor sin argumentos.
             */
            contexto = JAXBContext.newInstance(Persona.class);

        } catch (JAXBException e) {
            System.out.println("Error creando el contexto");
            System.out.println(e.getMessage());
            return;
        }

        Marshaller m;
        try {

            // Obtiene el objeto Marshaller asociado al contexto.
            m = contexto.createMarshaller();

            /*
             * Establecer la propiedad JAXB_FORMATTED_OUTPUT con el valor true permite
             * que en la conversión a formato XML se incluyan retornos de carro e
             * indentación (sangrado del texto). Prueba a ejecutar el programa con los
             * valores true y false para ver la diferencia.
             */
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Crea una objeto persona, lo convierte a XML y devuelve el resultado a la
            // consola (System.out).
            // Persona p = new Persona(1, "Homer", "Simpson", 48);
            // m.marshal(p, System.out);

            // Si lo deseas, también puedes enviar el resultado a un fichero externo de la siguiente manera:
            Persona p = new Persona(1, "Homer", "Simpson", 48);
            m.marshal(p, new File("Homer.xml"));

        } catch (JAXBException e) {
            System.out.println("Error convertiendo el objeto a formato XML");
            System.out.println(e.getMessage());
        }
    }

    public static void JAVAStoXML() {
        JAXBContext contexto;
		try {
			contexto = JAXBContext.newInstance(GrupoPersonas.class);
		} catch (JAXBException e) {
			System.out.println("Error creando el contexto");
			System.out.println(e.getMessage());
			return;
		}
		
		Marshaller m;
		try {
			m = contexto.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			GrupoPersonas grupo = new GrupoPersonas();
			grupo.getPersonas().add(new Persona(1, "Homer", "Simpson", 48));
			grupo.getPersonas().add(new Persona(2, "Lisa", "Simpson", 12));
			grupo.getPersonas().add(new Persona(3, "Bart", "Simpson", 13));
			grupo.getPersonas().add(new Persona(4, "Marge", "Simpson", 40));
			
			m.marshal(grupo, new File("Simpson.xml"));
			System.out.println("El archivo Simpson.xml ha sido creado con éxito");
		} catch (JAXBException e) {
			System.out.println("Error convertiendo el objeto a formato XML");
			System.out.println(e.getMessage());
		}
    }
}