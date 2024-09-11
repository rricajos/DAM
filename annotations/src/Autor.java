import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Autor {
	String nombre() default "Telefónica";
	String direccion() default "Distrito Telefónica";

}