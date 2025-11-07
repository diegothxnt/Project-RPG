import java.util.*;
import java.util.concurrent.TimeUnit;

public class MazmorraRPG {
    // Sistema de clases del jugador
    enum Clase {
        CaballeroNegro("Caballero Negro", 120, 25, 15, "Espada de carton"),
        ELprogramador("El programador", 80, 35, 10, "Teclado magico"),
        AhstonHall("Ashton Hall", 100, 40, 40, "5 millones de dolares");
        
        final String nombre;
        final int vidaMaxima;
        final int ataque;
        final int defensa;
        final String armaInicial;
        
        Clase(String nombre, int vida, int ataque, int defensa, String arma) {
            this.nombre = nombre;
            this.vidaMaxima = vida;
            this.ataque = ataque;
            this.defensa = defensa;
            this.armaInicial = arma;
        }
    }
    
   