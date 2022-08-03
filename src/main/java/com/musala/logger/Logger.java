package com.musala.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.Date;

public class Logger {

	public Logger() {

	}

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
			Path path = FileSystems.getDefault().getPath(".").toAbsolutePath();
			System.out.println(path);
			File folder = new File("C://log");
			File folder1 = new File(path.toString()+"/logs");
			if (!folder.isDirectory()) {
				folder.mkdir();
			}
			if (!folder1.isDirectory()) {
				folder.mkdir();
			}
			
			File logfile = new File("C://log/publicportal-" + date + ".txt");
			File logfile1 = new File(path.toString()+"logs/batterlogs-" + date + ".txt");
			logfile.setReadable(true);
			logfile.setWritable(true);
			if (logfile.createNewFile()) {
				System.out.println("File Created");
			}
			logfile1.setReadable(true);
			logfile1.setWritable(true);
			if (logfile1.createNewFile()) {
				System.out.println("File Created");
			}
			BufferedWriter fr = new BufferedWriter(new FileWriter("C://log/publicportal-" + date + ".txt", true));
			fr.write(message + "\r\n");
			fr.close();
			
			BufferedWriter fr1 = new BufferedWriter(new FileWriter(path.toString()+"/logs/batterlogs-" + date + ".txt", true));
			fr1.write(message + "\r\n");
			fr1.close();
		} catch (Exception e) {
			System.out.println("Error creating file. " + e.getMessage());
		}
	}
	
	public static void log(String message, Object obj) {
		log(obj.getClass().getName()+" => "+message);
	}
}
