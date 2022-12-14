package rva.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Klijent;
import rva.jpa.Racun;
import rva.jpa.TipRacuna;

public interface RacunRepository extends JpaRepository<Racun, Integer> {
    
	Collection<Racun> findByNazivContainingIgnoreCase (String naziv);
	Collection<Racun> findByKlijent (Klijent k);
	Collection<Racun> findByTipRacuna (TipRacuna t);
	
}
