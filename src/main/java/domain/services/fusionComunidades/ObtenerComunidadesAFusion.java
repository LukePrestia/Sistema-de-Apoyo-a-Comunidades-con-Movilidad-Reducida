package domain.services.fusionComunidades;

import domain.persona.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public  class ObtenerComunidadesAFusion implements WithSimplePersistenceUnit {
    public List<ParDeComunidades> obtenerComunidadesAFucionar() throws IOException {

        SugerenciaDeFusion sugerenciaDeFusion = SugerenciaDeFusion.instancia();
        List<Comunidad> comunidadesReal = (List<Comunidad>) entityManager().createQuery("from "+ Comunidad.class.getName()).getResultList();
        System.out.println(comunidadesReal.get(0).getServiciosObservados());
        List<ComunidadParID> comundadesParID =sugerenciaDeFusion.ListaDeComunidadesAFusionar(comunidadesReal);
        if(comundadesParID==null){
            return null;
        }
        return this.parDeComunidades(comundadesParID,comunidadesReal);

    }
    private List<ParDeComunidades>parDeComunidades(List<ComunidadParID> parIdComunidades,List<Comunidad> comunidadesReales){
        List<ParDeComunidades> comunidadesSugeridas= new ArrayList<>();
            for(int i = 0; 0<parIdComunidades.size(); i++){
                ParDeComunidades nuevoPar = new ParDeComunidades();
                int id1 = parIdComunidades.get(i).getComunidad1();
                int id2 = parIdComunidades.get(i).getComunidad2();
                for (Comunidad comunidadCandidata : comunidadesReales) {
                    if (comunidadCandidata.getId() == id1) {
                        nuevoPar.setComunidad1(comunidadCandidata);
                        break; // No need to continue searching
                    }
                    if (comunidadCandidata.getId() == id2) {
                        nuevoPar.setComunidad2(comunidadCandidata);
                        break; // No need to continue searching
                    }
                }
                comunidadesSugeridas.add(nuevoPar);
            }
        return comunidadesSugeridas;
    }
    public void fusionarComunidades(ParDeComunidades comunidades){
        Comunidad comunidadFinal = comunidades.getComunidad1();
        Comunidad comunidadDisuelta= comunidades.getComunidad2();
        comunidadFinal.getMembresias().addAll(comunidadDisuelta.getMembresias());
    }
}
