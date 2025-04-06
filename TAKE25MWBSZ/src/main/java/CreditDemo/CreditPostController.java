package CreditDemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/CreditPost")
public class CreditPostController {

    private double obliczRate(double kwota, double procent, double rat) {
        double m = 1 - 1 / Math.pow(1 + procent/12, rat);
        return kwota * (procent/12) / m;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Double> countCredit(@RequestBody CreditRequest req) {
        double result = obliczRate(req.getKwota(), req.getProcent(), req.getRat());
        return ResponseEntity.ok(result);
    }
}
