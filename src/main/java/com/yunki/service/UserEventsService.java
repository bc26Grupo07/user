package com.yunki.service;

import com.yunki.entity.User;
import com.yunki.events.UserCreatedEvent;
import com.yunki.events.Event;
import com.yunki.events.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class UserEventsService {
	
	@Autowired
	private KafkaTemplate<String, Event<?>> producer;
	
	@Value("${topic.user.name:users}")
	private String topicUser;
	
	public void publish(User user) {

		UserCreatedEvent created = new UserCreatedEvent();
		created.setData(user);
		created.setId(UUID.randomUUID().toString());
		created.setType(EventType.CREATED);
		created.setDate(new Date());

		this.producer.send(topicUser, created);
	}
	
	

}
