package domain.persistence;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class Persistente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}

