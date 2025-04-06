package CreditDemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CreditGet")
public class CreditGetController {

	private double obliczRate(double kwota, double procent, double rat) {
        double m = 1 - 1 / Math.pow(1 + procent/12, rat);
        return kwota * (procent/12) / m;
    }

    @GetMapping
    public ResponseEntity<Double> countCreditGet(
            @RequestParam double kwota,
            @RequestParam double procent,
            @RequestParam double rat) {

        double result = obliczRate(kwota, procent, rat);
        return ResponseEntity.ok(result);
    }
}
