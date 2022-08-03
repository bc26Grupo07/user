package com.yunki.service;

import com.yunki.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private final UserEventsService userEventsService;

	public UserService(UserEventsService userEventsService) {
		super();
		this.userEventsService = userEventsService;
	}

	public User save(User user) {
		System.out.println("Received " + user);
		this.userEventsService.publish(user);
		return user;
	}

}
