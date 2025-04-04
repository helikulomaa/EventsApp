package k25.events.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import k25.events.domain.Category;
import k25.events.domain.CategoryRepository;
import k25.events.domain.Event;

@RestController
public class CategoryRestController {

    @Autowired
    private CategoryRepository repository;

    @RequestMapping(value = "/api/categories", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> eventListRest() {
        try {
            List<Category> categories = (List<Category>) repository.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
