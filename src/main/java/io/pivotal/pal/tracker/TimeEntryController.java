package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    TimeEntryRepository inMemoryTimeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.inMemoryTimeEntryRepository=timeEntryRepository;

    }
    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity(inMemoryTimeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
        TimeEntry timeEntry = inMemoryTimeEntryRepository.find(timeEntryId);
        if (timeEntry==null){
            return new ResponseEntity( HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity(timeEntry, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(inMemoryTimeEntryRepository.list(), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry update = inMemoryTimeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        if (update==null){
            return new ResponseEntity( HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity(update,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long timeEntryId) {
        inMemoryTimeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
