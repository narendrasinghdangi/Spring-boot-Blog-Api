package com.blog.blog_apis.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {


    private int categoryId;

    @NotEmpty
    @Size(min = 4,max=100,message = "Category Title must be in between 4 to 100 char!!.")
    private String categoryTitle;

    @NotEmpty
    @Size(min=4,max=500,message = "Category Description Must in between 4 to 500 char!!.")
    private String categoryDescription;
}
