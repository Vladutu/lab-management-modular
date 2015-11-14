package com.iquestint.configuration;

import com.iquestint.dto.StudentDto;
import com.iquestint.dto.UserDto;
import com.iquestint.model.Student;
import com.iquestint.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;
import java.util.Properties;

/**
 * @author vladu
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.iquestint")
public class WebAppConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));
        resolver.setCookieName("myLocaleCookie");
        resolver.setCookieMaxAge(4800);
        return resolver;
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r =
            new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        mappings.setProperty(CannotCreateTransactionException.class.getName(), "error");

        r.setExceptionMappings(mappings);  // None by default
        // r.setDefaultErrorView("error");    // No default
        r.setExceptionAttribute("ex");     // Default is "exception"
        r.setWarnLogCategory("example.MvcLogger");     // No default
        return r;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("mylocale");
        registry.addInterceptor(interceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/css/**")
            .addResourceLocations("/css/");
        registry.addResourceHandler("/img/**")
            .addResourceLocations("/img/");
        registry.addResourceHandler("/js/**")
            .addResourceLocations("/js/");
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        PropertyMap<Student, StudentDto> studentStudentDtoPropertyMap = new PropertyMap<Student, StudentDto>() {
            @Override
            protected void configure() {
                map(source.getPnc(), destination.getPnc());
                map(source.getFirstName(), destination.getFirstName());
                map(source.getLastName(), destination.getLastName());
                map(source.getSection().getName(), destination.getSection());
                map(source.getGroup().getName(), destination.getGroup());
                map(source.getSubgroup().getName(), destination.getSubgroup());
            }
        };

        PropertyMap<StudentDto, Student> studentDtoStudentPropertyMap = new PropertyMap<StudentDto, Student>() {
            @Override
            protected void configure() {
                map(source.getPnc(), destination.getPnc());
                map(source.getFirstName(), destination.getFirstName());
                map(source.getLastName(), destination.getLastName());
                map(source.getSection(), destination.getSection().getName());
                map(source.getGroup(), destination.getGroup().getName());
                map(source.getSubgroup(), destination.getSubgroup().getName());

            }
        };

        PropertyMap<User, UserDto> userUserDtoPropertyMap = new PropertyMap<User, UserDto>() {
            @Override
            protected void configure() {
                map(source.getPnc(), destination.getPnc());
                map(source.getPerson().getFirstName(), destination.getFirstName());
                map(source.getPerson().getLastName(), destination.getLastName());
                map(source.getEmail(), destination.getEmail());
                map(source.getPassword(), destination.getPassword());
                map(source.getUserType().getName(), destination.getUserType());
                map(source.getUserState().getName(), destination.getUserState());
            }
        };

        PropertyMap<UserDto, User> userDtoUserPropertyMap = new PropertyMap<UserDto, User>() {
            @Override
            protected void configure() {
                map(source.getPnc(), destination.getPnc());
                map(source.getFirstName(), destination.getPerson().getFirstName());
                map(source.getLastName(), destination.getPerson().getLastName());
                map(source.getEmail(), destination.getEmail());
                map(source.getPassword(), destination.getPassword());
                map(source.getUserType(), destination.getUserType().getName());
                map(source.getUserState(), destination.getUserState().getName());
            }
        };

        modelMapper.addMappings(studentStudentDtoPropertyMap);
        modelMapper.addMappings(studentDtoStudentPropertyMap);
        modelMapper.addMappings(userUserDtoPropertyMap);
        modelMapper.addMappings(userDtoUserPropertyMap);

        return modelMapper;
    }

}
