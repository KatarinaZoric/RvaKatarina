package rva.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

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

import rva.jpa.Klijent;
import rva.repositories.KlijentRepository;

@CrossOrigin
@RestController
public class KlijentRestController {

	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private KlijentRepository klijentRepository;
	
	@GetMapping("klijent")
	public Collection<Klijent> getKlijenti() {
		
		return klijentRepository.findAll();
		
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("klijent/{id}")
	public Klijent getKlijent(@PathVariable("id") Integer id) {
		
		return klijentRepository.getOne(id);
		
	}
	
	@GetMapping("klijentIme/{ime}")
	public Collection<Klijent> getKreditByIme(@PathVariable("ime") String ime) {
		
		return klijentRepository.findByImeContainingIgnoreCase(ime);
	}
	
	@PostMapping("klijent")
	public ResponseEntity<Klijent> insertKlijent(@RequestBody Klijent klijent) {
	
		if(!klijentRepository.existsById(klijent.getId())) {
			
			klijentRepository.save(klijent);
			return new ResponseEntity<Klijent>(HttpStatus.OK);	
		}
		return new ResponseEntity<Klijent>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("klijent")
	public ResponseEntity<Klijent> updateKlijent(@RequestBody Klijent klijent) {
		if(!klijentRepository.existsById(klijent.getId())) {
			
			return new ResponseEntity<Klijent>(HttpStatus.NO_CONTENT);
	    }
		
	    klijentRepository.save(klijent);
		return new ResponseEntity<Klijent>(HttpStatus.OK);	
	
	}
	
	@Transactional
	@DeleteMapping("klijent/{id}")
	public ResponseEntity<Klijent> deleteKlijent(@PathVariable("id") Integer id) {
		
		if(!klijentRepository.existsById(id)) {
			return new ResponseEntity<Klijent>(HttpStatus.NO_CONTENT);
		}
		
		jdbcTemplate.execute("delete from racun where klijent= " + id);
		
		klijentRepository.deleteById(id);
		
		if (id==-100) {
			jdbcTemplate.execute(
					"INSERT INTO \"klijent\"(\"id\", \"ime\", \"prezime\", \"broj_lk\", \"kredit\")"
					+ "VALUES(-100, 'TESTime', 'Test prezime', '1','-100')" 
					
			);
		}
		
		return new ResponseEntity<Klijent>(HttpStatus.OK);	

	}
	
}
