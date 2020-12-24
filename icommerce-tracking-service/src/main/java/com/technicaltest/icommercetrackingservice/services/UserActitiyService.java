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
                HttpStatus.OK);
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

    @GetMapping("/user/user-activity")
    public ResponseEntity<List<UserActivity>> findByUserName(@RequestParam(name = "user")String userName) {
        List<UserActivity> result = userActivityServiceBean.findByUserName(userName);
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

    @GetMapping("/activity/user-activity/")
    public ResponseEntity<List<UserActivity>> findByActivityName(@RequestParam(name = "activity") String activityName) {
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
