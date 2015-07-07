package org.beadle.pojo;

public class Message {
	
	public Message(boolean result){
		this.result=result;
	}
	public Message(boolean result,String message){
		this.result=result;
		this.message=message;
	}
	public Message(boolean result,String message,Object obj){
		this.result=result;
		this.message=message;
		this.obj=obj;
	}
	
	private boolean result;
	private String message;
	private Object obj;
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
