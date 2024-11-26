package com.ikerpc123.tarea3dwesiker.repositorios;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;
import com.ikerpc123.tarea3dwesiker.modelo.*;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long>{


}
