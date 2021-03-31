package com.example.TaskManager.entities;

public enum Severity{
	LOW
	{
		public String toString() {
	
        return "LOW";
    }},
	MEDIUM
	{
		public String toString() {
			
	        return "MEDIUM";
	    }},
	HIGH{
		public String toString() {
			
	        return "HIGH";
	    }}
}