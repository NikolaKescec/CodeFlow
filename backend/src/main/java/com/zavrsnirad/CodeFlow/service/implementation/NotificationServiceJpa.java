package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.Follower;
import com.zavrsnirad.CodeFlow.domain.Notification;
import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.TimeAndUser;
import com.zavrsnirad.CodeFlow.repository.NotificationRepository;
import com.zavrsnirad.CodeFlow.repository.ProgrammerRepository;
import com.zavrsnirad.CodeFlow.service.FollowerService;
import com.zavrsnirad.CodeFlow.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class NotificationServiceJpa implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public Notification findById(Long notificationId) {
        try{
            return notificationRepository.findById(notificationId).get();
        }catch(NoSuchElementException ex) {
            throw new IllegalArgumentException("No such notification!");
        }
    }

    @Override
    public void removeNotification(Long notificationId) {
        Notification notification = findById(notificationId);
        notificationRepository.delete(notification);
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}
