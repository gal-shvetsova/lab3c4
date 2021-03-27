package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.data.Node;
import org.example.data.Way;
import org.example.data.dto.WayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/way", consumes = "application/json", produces = "application/json")
public class WayRestController {
    private final WayRepository wayRepository;

    @PostMapping(path = "/new")
    public Way create(@RequestBody Way way) {
        return wayRepository.save(way);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<Way> read(@RequestParam long id) {
        Optional<Way> wayOptional = wayRepository.findById(BigInteger.valueOf(id));
        if (wayOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wayOptional.get(), HttpStatus.OK);
    }

    @PatchMapping(path = "/update")
    public ResponseEntity<Way> update(@RequestBody Way way) {
        Optional<Way> wayOptional = wayRepository.findById(way.getId());
        if (wayOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Way existingWay = wayOptional.get();
        if (way.getChangeset() != null) {
            existingWay.setChangeset(way.getChangeset());
        }
        if (way.getTimestamp() != null) {
            existingWay.setTimestamp(way.getTimestamp());
        }
        if (way.getUid() != null) {
            existingWay.setUid(way.getUid());
        }
        if (way.getUser() != null) {
            existingWay.setUser(way.getUser());
        }
        if (way.getVersion() != null) {
            existingWay.setVersion(way.getVersion());
        }
        if (way.getVisible() != null) {
            existingWay.setVisible(way.getVisible());
        }
        if (way.getTags() != null) {
            existingWay.setTags(way.getTags());
        }
        return new ResponseEntity<>(wayRepository.save(existingWay), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Node> delete(@RequestBody Way way) {
        if (way.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        wayRepository.deleteById(way.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
