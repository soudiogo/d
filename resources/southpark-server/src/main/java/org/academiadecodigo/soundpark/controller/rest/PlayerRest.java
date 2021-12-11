package org.academiadecodigo.soundpark.controller.rest;


import org.academiadecodigo.soundpark.persistence.dao.PlayerDao;
import org.academiadecodigo.soundpark.persistence.model.Player;
import org.academiadecodigo.soundpark.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/player")
public class PlayerRest {

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Retrieves a representation of the given customer
     *
     * @param id the customer id
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Player> showCustomer(@PathVariable Integer id) {


        Player player = playerService.get(id);

        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    /**
     * Adds a customer
     *
     * @param player          the customer DTO
     * @param bindingResult        the binding result object
     * @param uriComponentsBuilder the uri components builder
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Player player, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || player.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Player savedPlayer = playerService.save(player);

        // get help from the framework building the path for the newly created resource
        UriComponents uriComponents = uriComponentsBuilder.path("/api/player/" + savedPlayer.getId()).build();

        // set headers with the created path
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Player> editCustomer(@Valid @RequestBody Player player, BindingResult bindingResult, @PathVariable Integer id) {


        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (player.getId() != null && !player.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (playerService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        player.setId(id);

        playerService.save(player);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
