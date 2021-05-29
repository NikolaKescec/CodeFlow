package com.zavrsnirad.CodeFlow.controllers;

import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperList;
import com.zavrsnirad.CodeFlow.dto.mappers.MapperNotification;
import com.zavrsnirad.CodeFlow.service.NotificationService;
import com.zavrsnirad.CodeFlow.service.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/receive")
    public ResponseEntity<?> getNotifications(Principal principal) {
        Programmer programmer = programmerService.findByUsername(principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(MapperList.getList(programmer.getNotifications(), MapperNotification::NotificatioToJson));
    }

    @DeleteMapping("/remove-notification/{notificationId}")
    public ResponseEntity<?> removeNotification(@PathVariable("notificationId") Long notificationId) {
        notificationService.removeNotification(notificationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
