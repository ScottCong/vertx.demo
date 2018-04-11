package com.scott.vertx.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;

public class PongVerticle extends AbstractVerticle{

	float skill = (float) 0.7;
	
	public boolean isMiss(){
		return Math.random() >= skill;
	}
	
	@Override
	public void start(Future<Void> startFuture){
		
		EventBus eb = vertx.eventBus();
		eb.consumer("pong", message ->{
			System.out.println(message.body().toString());
			if(!isMiss())eb.publish("ping","ping");
			else eb.publish("miss","pong");
		});
		System.out.println("In the pong verticle");
	}
}
