package com.seolgi.detector.domain.common.config;

import com.seolgi.detector.domain.DomainApplication;
import com.seolgi.detector.domain.utils.FullBeanNameGenerator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan(basePackageClasses = DomainApplication.class , nameGenerator = FullBeanNameGenerator.class)
@PropertySource(value = "classpath:application-${spring.profiles.active}.core.properties")
public class DomainApplicationConfig implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSourceNamingConvention(NamingConventions.NONE);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.getConfiguration().setDestinationNamingConvention(NamingConventions.NONE);

        return modelMapper;
    }
}
