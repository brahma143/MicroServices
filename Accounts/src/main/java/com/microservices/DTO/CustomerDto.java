package com.microservices.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    //private Long customerId;
    @Schema(
            description = "Name of the customer\", example = \"Eazy Bytes\""
    )
    @NotEmpty(message = "name should not be null or empty")
    @Size(min = 5, max = 20, message = "Name should be max length of the String ")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "tutor@eazybytes.com"

    )
    @NotEmpty(message = "Email should not be a null or empty")
    @Email(message = "Email should be valid format")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    @Pattern(regexp = ("^$|[0-9]{10}"),message = "mobileNumber must be 10 digits")
    private String mobileNumber;
    @Schema(
            description = "Account details of the Customer"
    )
    private AccountDto accountDto;
}
