
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Contactos;
import java.util.List;


public interface ContactosService {
    
    List<Contactos> getContactos();

    public void save(Contactos contactos);

    public void delete(Contactos contactos);
}