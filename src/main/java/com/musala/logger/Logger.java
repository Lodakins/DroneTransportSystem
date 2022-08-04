package com.musala.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.Date;

import com.musala.dao.PeriodicTask;

public class Logger {

	public Logger() {

	}
	public static String name;

	public static void logClass(String message, Class<?> class_) {
		log(message, class_.getName());
	}

	public static void log(String message, String type) {
		Date d = new Date();
		Timestamp ts = new Timestamp(d.getTime());
		String ms = type == null ? "Logged: " + ts + " " + message : type + ": " + ts + " " + message;
		System.out.println(ms);
		String date = ts.toString().split(" ")[0];
		writeToFile(ms, date);
	}

	public static void log(String message) {
		log(message, null);
	}

	private static void writeToFile(String message, String date) {
		try {
			String userDirectory = System.getProperty("user.dir");
			
			File folder = new File(userDirectory+"\\logs");
		
			if (!folder.isDirectory()) {
				folder.mkdir();
			}
			
			File logfile = new File(userDirectory+"\\logs\\batterlogs-" + date + ".txt");
			logfile.setReadable(true);
			logfile.setWritable(true);
			
			if (logfile.createNewFile()) {
				System.out.println("File Created");
			}
			
			BufferedWriter fr1 = new BufferedWriter(new FileWriter(userDirectory+"\\logs\\batterlogs-" + date + ".txt", true));
			fr1.write(message + "\r\n");
			fr1.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error creating file. " + e.getMessage());
		}
	}
	
	public static void log(String message, Object obj) {
		log(obj.getClass().getName()+" => "+message);
	}
	
	public static void main(String[] args) {
		Logger.log("Just Testing");
	}
	
}
