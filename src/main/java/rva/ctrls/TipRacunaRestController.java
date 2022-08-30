package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Kredit;
import rva.jpa.TipRacuna;
import rva.repositories.TipRacunaRepository;

@CrossOrigin
@RestController
public class TipRacunaRestController {
	
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TipRacunaRepository tipRacunaRepository;
	
	@GetMapping("tipRacuna")
	public Collection<TipRacuna> getTipoviRacuna() {
		
		return tipRacunaRepository.findAll();
		
	}
	
	@GetMapping("tipRacuna/{id}")
	public TipRacuna getTipRacuna(@PathVariable("id") Integer id) {
		
		return tipRacunaRepository.getOne(id);
		
	}
	
	@GetMapping("tipRacunaNaziv/{naziv}")
	public Collection<TipRacuna> getTipRacunaByNaziv(@PathVariable("naziv") String naziv){
		
		return tipRacunaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@PostMapping("tipRacuna")
	public ResponseEntity<TipRacuna> insertTipRacuna(@RequestBody TipRacuna tipRacuna) {
	
		if(!tipRacunaRepository.existsById(tipRacuna.getId())) {
			
			tipRacunaRepository.save(tipRacuna);
			return new ResponseEntity<TipRacuna>(HttpStatus.OK);	
		}
		return new ResponseEntity<TipRacuna>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("tipRacuna")
	public ResponseEntity<TipRacuna> updateTipRacuna(@RequestBody TipRacuna tipRacuna) {
		if(!tipRacunaRepository.existsById(tipRacuna.getId())) {
			
			return new ResponseEntity<TipRacuna>(HttpStatus.NO_CONTENT);
	    }
		
	    tipRacunaRepository.save(tipRacuna);
		return new ResponseEntity<TipRacuna>(HttpStatus.OK);	
	
	}
	
	@DeleteMapping("tipRacuna/{id}")
	
	public ResponseEntity<TipRacuna> deleteTipRacuna(@PathVariable("id") Integer id) {
		
		if(!tipRacunaRepository.existsById(id)) {
			return new ResponseEntity<TipRacuna>(HttpStatus.NO_CONTENT);
		}
		
		tipRacunaRepository.deleteById(id);
		
		if (id==-100) {
			jdbcTemplate.execute(
					"INSERT INTO \"kredit\"(\"id\", \"naziv\", \"oznaka\", \"opis\")"
					+ "VALUES(-100, 'TEST', 'OZNAKATEST', 'OPISTEST')" 
					
			);
		}
		
		return new ResponseEntity<TipRacuna>(HttpStatus.OK);	


	}

}
