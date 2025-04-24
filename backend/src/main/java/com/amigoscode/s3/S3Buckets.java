package com.amigoscode.s3;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix = "aws.s3.buckets") //refers to the application.yml file


public class S3Buckets {
        private String customer;

        public String getCustomer(){
            return customer;
        }
        public void setCustomer(String customer){
            this.customer = customer;
        }
    }

