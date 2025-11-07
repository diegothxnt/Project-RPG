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
   
    static class Entidad {
        String nombre;
        int vida, vidaMaxima, ataque, defensa, nivel;
        
        Entidad(String nombre, int vida, int ataque, int defensa) {
            this.nombre = nombre;
            this.vida = this.vidaMaxima = vida;
            this.ataque = ataque;
            this.defensa = defensa;
            this.nivel = 1;
        }
        
        boolean estaVivo() { return vida > 0; }
        
        int atacar() {
            return (int)(ataque * (0.8 + Math.random() * 0.4));
        }
        
        void recibirDanio(int danio) {
            danio = Math.max(1, danio - defensa);
            vida = Math.max(0, vida - danio);
        }
    }
     static class Jugador extends Entidad {
        Clase clase;
        int experiencia, experienciaNecesaria;
        ArrayList<String> inventario;
        String armaEquipada;
        int oro;
        
        Jugador(Clase clase) {
            super("Héroe", clase.vidaMaxima, clase.ataque, clase.defensa);
            this.clase = clase;
            this.experiencia = 0;
            this.experienciaNecesaria = 100;
            this.inventario = new ArrayList<>();
            this.armaEquipada = clase.armaInicial;
            this.oro = 50;
            this.inventario.add(armaEquipada);
        }
        
        @Override
        int atacar() {
            int danioBase = super.atacar();
            
            if (armaEquipada.contains("Legendaria")) danioBase += 15;
            else if (armaEquipada.contains("Épica")) danioBase += 10;
            else if (armaEquipada.contains("Rara")) danioBase += 5;
            return danioBase;
        }
        
        void ganarExperiencia(int exp) {
            experiencia += exp;
            System.out.println("¡Ganaste " + exp + " puntos de experiencia!");
            
            while (experiencia >= experienciaNecesaria) {
                subirNivel();
            }
        }
        
        void subirNivel() {
            nivel++;
            experiencia -= experienciaNecesaria;
            experienciaNecesaria = (int)(experienciaNecesaria * 1.5);
            
            vidaMaxima += 20;
            vida = vidaMaxima;
            ataque += 5;
            defensa += 2;
            
            System.out.println("\n¡ SUBISTE AL NIVEL " + nivel + "!");
            System.out.println("Vida: " + vidaMaxima + " | Ataque: " + ataque + " | Defensa: " + defensa);
        }
        
        void usarPocion() {
            if (inventario.contains("Poción de Vida")) {
                int curacion = vidaMaxima / 2;
                vida = Math.min(vidaMaxima, vida + curacion);
                inventario.remove("Poción de Vida");
                System.out.println("¡Usaste una poción! Vida recuperada: +" + curacion);
            } else {
                System.out.println("No tienes pociones...");
            }
        }
    }
    
    static class Sala {
        String descripcion;
        String evento; 
        Entidad enemigo;
        String tesoro;
        
        Sala(String desc, String evento) {
            this.descripcion = desc;
            this.evento = evento;
        }
    }

   


