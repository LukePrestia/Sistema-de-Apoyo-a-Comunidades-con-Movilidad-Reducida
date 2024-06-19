package domain.persona;
import java.util.ArrayList;
import java.util.List;

import domain.persistence.Persistente;
import lombok.Setter;
import lombok.Getter;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;

@Setter @Getter
@Entity
public class Usuario extends Persistente {

    @Column
    private String nombreUsuario;
    @Column
    private String contraseña;
    @Column
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @Transient
    private List<Requisitos> chequeos = new ArrayList<>();
    @OneToOne(cascade=CascadeType.PERSIST)
    private Persona persona;



    private void iniciarRequisitos(List<Requisitos> req){
        chequeos.addAll(req);
    }
    public void crearUsuario(String contrasena, String usuario , List<Requisitos> requi){
        this.iniciarRequisitos(requi);
        this.nombreUsuario = usuario;
        if(chequeos.stream().allMatch(requisitos-> requisitos.evaluarContrasena(contrasena))){
            this.contraseña=contrasena;
        }
    }

}