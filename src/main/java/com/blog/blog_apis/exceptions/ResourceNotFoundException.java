package com.blog.blog_apis.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String feildName;
    Integer feildValue;
    public ResourceNotFoundException(String resourceName, String feildName, Integer feildValue) {
        super(String.format("%s not found  %s :%s",resourceName,feildName,feildValue));
        this.resourceName = resourceName;
        this.feildName = feildName;
        this.feildValue = feildValue;
    }
    

    
}
