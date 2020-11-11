package mx.edu.upsrj.helper;

import java.util.HashMap;

public class Response<T> {

	private int status;
	
	private String status_text;
	
	private HashMap<String, String> errors;
		
	private String message;
	
	private String path;
	
	private T data; // T = tipo de dato generico

	
	//Getters and Setters
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatus_text() {
		return status_text;
	}

	public void setStatus_text(String status_text) {
		this.status_text = status_text;
	}

	public HashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
