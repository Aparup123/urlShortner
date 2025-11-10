package in.arc.urlShortner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<String> welcome(){
        return new ResponseEntity<>("This is the urlShortner api. Use '/url/create-short-url' for creating short urls.", HttpStatus.OK);
    }
}
