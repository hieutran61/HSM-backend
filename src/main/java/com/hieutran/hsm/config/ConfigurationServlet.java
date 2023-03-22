package com.hieutran.hsm.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.hieutran.hsm")
class ConfigurationServlet implements WebMvcConfigurer{
//    @Bean
//    public ViewResolver viewResolver(){
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/views/");
//        viewResolver.setSuffix(".jsp");
//        
//        return viewResolver;
//    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // loại driver đang dùng
        dataSource.setUrl("jdbc:sqlserver://HIEUTRAN:1433;databaseName=HospitalManagermentSystemDB"); // csdl đang dùng
        dataSource.setUsername("sa"); // tài khoản sql
        dataSource.setPassword("Changtrailaucaak"); // mật khẩu sql
        return dataSource;
    }
    
//    @Bean
//    public ITodoDAO getContactDAO(){
//        return new TodoDAOImpl(dataSource());
//    }
    
    @Bean
    public JdbcTemplate setDataSource(){
        return new JdbcTemplate(dataSource());
    }
}
