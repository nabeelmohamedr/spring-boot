package com.optimus.omnitrix;

import com.optimus.omnitrix.entity.asmith_entity;
import com.optimus.omnitrix.excptiom.omn_excp;
import com.optimus.omnitrix.repo.asmith_repositary;

import com.optimus.omnitrix.spec.asmith_specification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController/// rest apis controller to handle every request responce api contain request controller and responce bofdy
@Slf4j
@RequestMapping //("/triger")//to handel the requests method
public class triger {

    @Autowired
    private asmith_repositary asmith_repositary;

    @Autowired
    private asmith_specification asmith_specification;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("/home")  // This endpoint will respond to GET requests at /triger
    public String trig() {
        try {
           // int x = 10 / 0;
           // throw new IllegalArgumentException("Invalid argument passed!");
            return "It's Hero Time kev";
        } catch (Exception e) {
            log.error("An error occurred in /triger endpoint", e);
            return "Something went wrong!";
        }
    }


    @GetMapping("/omn/trigerD")  // This endpoint will respond to GET requests at /triger
    public String trigD(@RequestParam("alen") String al) {
        return "It's Hero Time kev "+ al;
    }

//    @GetMapping("/triger") // This endpoint will respond to GET requests at /triger
//    public List<getter> trig() {
//        return Arrays.asList(new getter(1,"shae"),
//                             new getter(2,"nobita"),
//                             new getter(3,"hasse"));
//    }
    @GetMapping("/omn/trigerA") // This endpoint will respond to GET requests at /triger
    public List<asmith_entity> trigA() {

        return asmith_repositary.findAll();
    }

    @GetMapping("/omn/trigerp")
    public Page<asmith_entity> pages(@RequestParam int page ,@RequestParam int size)
    {
        Pageable p= PageRequest.of(page,size);
        return asmith_repositary.findAll(p);

    }

    @PostMapping("/omn/triger")
    public List<asmith_entity> set(@RequestBody List<asmith_entity> entities) {
        for (asmith_entity entity : entities) {
            // encode each password before saving
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
        return asmith_repositary.saveAll(entities);
    }

    @GetMapping("/omn/triger/{id}")
    public asmith_entity getById(@PathVariable Long id) {
        return asmith_repositary.findById(id).orElseThrow(()-> new omn_excp(" not found "+ id));

    }

    @PutMapping("/omn/triger/{id}")
    public asmith_entity PutById(@RequestBody asmith_entity entities,@PathVariable Long id) {
         asmith_entity obj=asmith_repositary.findById(id).orElseThrow(()-> new omn_excp(" not found "+ id));
         obj.setName(entities.getName());
         obj.setPower(entities.getPower());
         return asmith_repositary.save(obj);
    }
    @DeleteMapping ("/omn/triger/{id}")
    public  ResponseEntity<?> delById(@PathVariable Long id) {
        asmith_entity obj = asmith_repositary.findById(id).orElseThrow(() -> new omn_excp(" not found " + id));

         asmith_repositary.delete(obj);
         return ResponseEntity.ok().build();
    }
    @GetMapping("/byPower")
    public List<asmith_entity> getByPowerRange(@RequestParam(required = false) Integer min,
                                               @RequestParam(required = false) Integer max) {
        if (min == null && max == null) {
            throw new IllegalArgumentException("Please provide min or max parameter");
        }
     //   Specification<asmith_entity> spec = Specification.where(null);
        Specification<asmith_entity> spec = (root, query, cb) -> cb.conjunction();
        if (min != null) {
            spec = spec.and(asmith_specification.hasPowerGreaterThan(min));
        }
        if (max != null) {
            spec = spec.and(asmith_specification.hasPowerLessThan(max));
        }

        return asmith_repositary.findAll(spec);
    }
//    root → represents the entity’s table (asmith_entity).
//
//    query → represents the query being built.
//
//            cb → is a CriteriaBuilder, used to build conditions like =, <, >, AND, OR.
//
//            cb.conjunction() means always true (like SQL WHERE 1=1).

    // Example: GET /omn/exactPower?value=900
    @GetMapping("/exactPower")
    public List<asmith_entity> getByExactPower(@RequestParam int value) {
        return asmith_repositary.findAll(asmith_specification.hasExactPower(value));
    }
}


