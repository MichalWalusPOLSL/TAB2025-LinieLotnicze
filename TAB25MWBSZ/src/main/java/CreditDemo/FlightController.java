package CreditDemo;

import com.project.demo.repository.AirfieldRepository;
import com.project.demo.repository.FlightRepository;
import com.project.demo.repository.PlaneRepository;
import model.AirfieldEntity;
import model.FlightEntity;
import model.PlaneEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirfieldRepository airfieldRepository;

    @Autowired
    private PlaneRepository planeRepository;

    // POST /api/flightsAdd
    @PostMapping("/flightsAdd")
    public FlightEntity createFlight(@RequestBody FlightEntity flight) {

        // Wylot
        Long outAirfieldId = flight.getFlightsOutAirfield().getAirfieldId();
        AirfieldEntity outAirfield = airfieldRepository.findById(outAirfieldId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lotnisko wylotu o ID " + outAirfieldId + " nie istnieje."));

        // Przylot
        Long inAirfieldId = flight.getLandsInAirfield().getAirfieldId();
        AirfieldEntity inAirfield = airfieldRepository.findById(inAirfieldId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lotnisko przylotu o ID " + inAirfieldId + " nie istnieje."));

        // Samolot
        Long planeId = flight.getPlane().getPlaneId();
        PlaneEntity plane = planeRepository.findById(planeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Samolot o ID " + planeId + " nie istnieje."));

        // Przypisz pełne encje
        flight.setFlightsOutAirfield(outAirfield);
        flight.setLandsInAirfield(inAirfield);
        flight.setPlane(plane);

        return flightRepository.save(flight);
    }

    // DELETE /api/flightsDelete/{id}
    @DeleteMapping("/flightsDelete/{id}")
    public String deleteFlight(@PathVariable Long id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
            return "Usunięto lot o ID: " + id;
        } else {
            return "Lot o ID " + id + " nie istnieje.";
        }
    }

    // GET /api/flights
    @GetMapping("/flights")
    public List<FlightEntity> getAllFlights() {
        return flightRepository.findAll();
    }

    // GET /api/flights/{id}
    @GetMapping("/flights/{id}")
    public FlightEntity getFlightById(@PathVariable Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono lotu o ID: " + id));
    }
}
