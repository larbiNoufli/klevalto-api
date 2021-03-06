package klevalto.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import klevalto.domain.Bien;
import klevalto.repository.BienRepository;
import klevalto.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Bien.
 */
@RestController
@RequestMapping("/api")
public class BienResource {

    private final Logger log = LoggerFactory.getLogger(BienResource.class);

    private static final String ENTITY_NAME = "bien";

    private final BienRepository bienRepository;

    public BienResource(BienRepository bienRepository) {
        this.bienRepository = bienRepository;
    }

    /**
     * POST  /biens : Create a new bien.
     *
     * @param bien the bien to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bien, or with status 400 (Bad Request) if the bien has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/biens")
    @Timed
    public ResponseEntity<Bien> createBien(@RequestBody Bien bien) throws URISyntaxException {
        log.debug("REST request to save Bien : {}", bien);
        if (bien.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new bien cannot already have an ID")).body(null);
        }
        Bien result = bienRepository.save(bien);
        return ResponseEntity.created(new URI("/api/biens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /biens : Updates an existing bien.
     *
     * @param bien the bien to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bien,
     * or with status 400 (Bad Request) if the bien is not valid,
     * or with status 500 (Internal Server Error) if the bien couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/biens")
    @Timed
    public ResponseEntity<Bien> updateBien(@RequestBody Bien bien) throws URISyntaxException {
        log.debug("REST request to update Bien : {}", bien);
        if (bien.getId() == null) {
            return createBien(bien);
        }
        Bien result = bienRepository.save(bien);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bien.getId().toString()))
            .body(result);
    }

    /**
     * GET  /biens : get all the biens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of biens in body
     */
    @GetMapping("/biens")
    @Timed
    public List<Bien> getAllBiens() {
        log.debug("REST request to get all Biens");
        return bienRepository.findAll();
        }

    /**
     * GET  /biens/:id : get the "id" bien.
     *
     * @param id the id of the bien to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bien, or with status 404 (Not Found)
     */
    @GetMapping("/biens/{id}")
    @Timed
    public ResponseEntity<Bien> getBien(@PathVariable Long id) {
        log.debug("REST request to get Bien : {}", id);
        Bien bien = bienRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bien));
    }

    /**
     * DELETE  /biens/:id : delete the "id" bien.
     *
     * @param id the id of the bien to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/biens/{id}")
    @Timed
    public ResponseEntity<Void> deleteBien(@PathVariable Long id) {
        log.debug("REST request to delete Bien : {}", id);
        bienRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
