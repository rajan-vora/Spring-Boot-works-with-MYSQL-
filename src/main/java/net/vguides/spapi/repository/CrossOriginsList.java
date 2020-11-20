package net.vguides.spapi.repository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.CrossOrigin;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@CrossOrigin
public @interface CrossOriginsList {
	 public String[] crossOrigins() default  {
         "http://localhost:4200", "http://localhost:8080"
         // Pass as many as you want
 };
}
