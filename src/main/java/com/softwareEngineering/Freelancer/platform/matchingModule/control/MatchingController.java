
package com.softwareEngineering.Freelancer.platform.matchingModule.control;

import com.softwareEngineering.Freelancer.platform.common.controller.BaseController;
import com.softwareEngineering.Freelancer.platform.request.UsernameRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MatchingController extends BaseController {
    @RequestMapping("/findTheMostMatchedServiceProvider")
    public ResponseEntity findTheMostMatchedServiceProvider(@RequestBody UsernameRequest request) {
        baseService.log("system", "finding the most matched service provider", "username of service provider: " + request.getUsername());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body("The most matched service provider is : " + "\n"
                        + baseService.findTheMostMatchedServiceProviderForAUser(request.getUsername()));
    }
}
