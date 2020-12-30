package com.technicaltest.icommercetrackingservice.services;

import com.technicaltest.icommercetrackingservice.bean.UserActivityServiceBean;
import com.technicaltest.icommercetrackingservice.dto.CommonResponseBody;
import com.technicaltest.icommercetrackingservice.dto.UserActivity;
import com.technicaltest.icommercetrackingservice.support.HTTPDataHelper;
import com.technicaltest.icommercetrackingservice.support.HeaderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 *  Provide API to fetching raw data that storage in MONGO DB serve for tracking user activity
 * @Author Dat Tran
 */
@RestController
@RequestMapping("/tracking-service")
public class UserActitiyService {

    @Autowired
    private UserActivityServiceBean userActivityServiceBean;

    @Autowired
    private HeaderGenerator headerGenerator;

    @PostMapping("/user-activity")
    public Object addActivity(@RequestBody UserActivity userActivity) {
        userActivityServiceBean.addTrackingActivity(userActivity);
        return new ResponseEntity<CommonResponseBody>(
                HTTPDataHelper.createSuccess(),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.CREATED);
    }

    @GetMapping("/user-activity-all")
    public ResponseEntity<List<UserActivity>> findAll() {
        List<UserActivity> result = userActivityServiceBean.findAll();
        if(result != null) {
            return new ResponseEntity<List<UserActivity>>(
                    result,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<List<UserActivity>>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    @GetMapping("/by-customer/{customerName}")
    public ResponseEntity<List<UserActivity>> findByUserName(@PathVariable(name = "customerName") String customerName) {
        List<UserActivity> result = userActivityServiceBean.findByUserName(customerName);
        if(result != null) {
            return new ResponseEntity<List<UserActivity>>(
                    result,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<List<UserActivity>>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    @GetMapping("/by-activity/{activity}")
    public ResponseEntity<List<UserActivity>> findByActivityName(@PathVariable(name = "activity") String activityName) {
        List<UserActivity> result = userActivityServiceBean.findByUserActivity(activityName);
        if(result != null) {
            return new ResponseEntity<List<UserActivity>>(
                    result,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<List<UserActivity>>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }
}
