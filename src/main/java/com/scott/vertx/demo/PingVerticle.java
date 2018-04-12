package com.scott.vertx.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;

public class PingVerticle extends AbstractVerticle {

	float skill = (float) 0.7;
	
	public boolean isMiss(){
		return Math.random() >= skill;
	}
	
	@Override
	public void start(Future<Void> future){
		EventBus eb = vertx.eventBus();
		eb.consumer("ping", message ->{
			System.out.println(message.body());
			if(!isMiss())eb.publish("pong","pong");
			else eb.publish("miss","ping");
			
		});
		System.out.println("In the ping verticle");
	}
}
