package com.example.nlt.config;

import com.example.nlt.util.MyValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class BeanConfiguration {
    @Bean(name = "default")
    @Primary //ако не сме оказали Qualifier, то винаги ползвай тази инстанция
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean(name = "withLocalDate")
    public ModelMapper getAlternativeModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> context) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(context.getSource(), format);
            }
        });

        return modelMapper;
    }

    @Bean   //Indicates that a method produces a bean to be managed by the Spring container
    public Gson gson() {
        return new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public MyValidator getValidator(){
        return new MyValidator();
    }

}
