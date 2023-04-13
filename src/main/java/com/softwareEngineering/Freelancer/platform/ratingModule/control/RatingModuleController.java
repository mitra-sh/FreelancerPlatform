
package com.softwareEngineering.Freelancer.platform.ratingModule.control;

import com.softwareEngineering.Freelancer.platform.common.controller.BaseController;
import com.softwareEngineering.Freelancer.platform.request.ServiceProviderRatingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RatingModuleController extends BaseController {
    @RequestMapping("/rateAServiceProvider")
    public ResponseEntity rateAServiceProvider(@RequestBody ServiceProviderRatingRequest request) {
        baseService.rateAServiceProvider(request);
        baseService.log(request.getUsername(),"rate a service provider","username of service provider: "+request.getUsername());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The rating for the service provider was successfully submitted" );
    }
}
