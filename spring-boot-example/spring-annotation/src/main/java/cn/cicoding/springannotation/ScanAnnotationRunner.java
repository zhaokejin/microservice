package cn.cicoding.springannotation;

import org.reflections.Reflections;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.lang.annotation.Annotation;

@Component
public class ScanAnnotationRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		new Reflections("org.springframework")
				.getSubTypesOf(Annotation.class)
				.stream()
				.map(clazz->clazz.getName())
				.sorted()
				.forEach(System.out::println);
	}
}