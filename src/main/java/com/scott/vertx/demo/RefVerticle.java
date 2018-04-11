package com.scott.vertx.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class RefVerticle extends AbstractVerticle{

	private int pingScore;
	private int pongScore;
	
	public boolean isEnd(){
		if((pingScore >= 11 || pongScore >= 11) && (Math.abs(pingScore - pongScore) >= 2))return true;
		return false;
	}
	
	
	@Override
	public void start(Future<Void> startFuture) throws Exception{
		EventBus eb = vertx.eventBus();
		eb.consumer("miss", message ->{
			if(message.body().equals("ping"))pongScore++;
			else if(message.body().equals("pong"))pingScore++;
			else System.out.println(message.body());
			
			if(isEnd()){
				System.out.println("Ping get score : " + pingScore);
				System.out.println("Pong get score : " + pongScore);
				System.out.println("game end");
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				System.out.println(message.body() + " missed");
				System.out.println("Ping get score : " + pingScore);
				System.out.println("Pong get score : " + pongScore);
				eb.publish("ping", "ping");
			}
		});
		eb.publish("ping", "ping");
		//System.out.println("ball served to ping!");
		
	}
	

}
