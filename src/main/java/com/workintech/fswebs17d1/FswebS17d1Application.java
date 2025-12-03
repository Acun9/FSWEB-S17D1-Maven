package com.workintech.fswebs17d1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FswebS17d1Application implements CommandLineRunner {

	@Value("${course.name}")
	private String courseName;

	@Value("${project.developer.fullname}")
	private String developerName;

	public static void main(String[] args) {
		SpringApplication.run(FswebS17d1Application.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Course: " + courseName + ", Developer: " + developerName);
	}
}
