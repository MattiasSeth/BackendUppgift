package com.example.backenduppgift.Email;

import com.example.backenduppgift.Configurations.IntegrationProperties;
import com.rabbitmq.client.impl.Environment;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class SpringMailConfig implements ApplicationContextAware, EnvironmentAware {

    private ApplicationContext applicationContext;
    private Environment environment;

    @Autowired
    IntegrationProperties properties;

    @Bean
    public JavaMailSender mailSender() throws IOException {

        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(properties.getMail().getHost());
        mailSender.setPort(properties.getMail().getPort());
        mailSender.setProtocol("smtp");
        mailSender.setUsername(properties.getMail().getEmail());
        mailSender.setPassword(properties.getMail().getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.debug","true");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.quitwait", "false");

        return mailSender;
    }

    @Bean
    public ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("mail/MailMessages");
        return messageSource;
    }

    @Bean
    public TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        // Resolver for TEXT emails
        templateEngine.addTemplateResolver(textTemplateResolver());
        // Resolver for HTML emails (except the editable one)
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        // Resolver for HTML editable emails (which will be treated as a String)
        templateEngine.addTemplateResolver(stringTemplateResolver());
        // Message source, internationalization specific to emails
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }

    private ITemplateResolver textTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(1));
        templateResolver.setResolvablePatterns(Collections.singleton("text/*"));
        templateResolver.setPrefix("/mail/");
        templateResolver.setSuffix(".txt");
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    private ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(2));
        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
        templateResolver.setPrefix("/mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    private ITemplateResolver stringTemplateResolver() {
        final StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(3));
        // No resolvable pattern, will simply process as a String template everything not previously matched
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void setEnvironment(org.springframework.core.env.Environment environment) {

    }


}


