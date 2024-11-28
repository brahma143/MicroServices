package com.loans.microServices.Controller;

import com.loans.microServices.Constants.LoansConstants;
import com.loans.microServices.DTO.ErrorResponseDto;
import com.loans.microServices.DTO.LoansDto;
import com.loans.microServices.DTO.ResponseDto;
import com.loans.microServices.Service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Brahma reddy Eazy Bytes
 */

@Tag(
        name = "CRUD REST APIs for Loans in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE loan details"
)

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class LoansController {

     ILoanService iLoanService;

    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new loan inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
     @PostMapping("/create")
     public ResponseEntity<ResponseDto> createLoans(@RequestParam String mobileNumber){

         iLoanService.createLoanAccount(mobileNumber);
         return  ResponseEntity.status(HttpStatus.OK)
                 .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));

     }

    @Operation(
            summary = "Fetch Loan Details REST API",
            description = "REST API to fetch loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
     @GetMapping("/get")
     public ResponseEntity<LoansDto> getLoans(@RequestParam String mobileNumber){

         LoansDto loansDto=iLoanService.getLoan(mobileNumber);

         return ResponseEntity.status(HttpStatus.FOUND)
                 .body(loansDto);

     }

    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API to update loan details based on a loan number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
     @PutMapping("/update")
     public ResponseEntity<ResponseDto> updateLoans(@RequestBody LoansDto loansDto){

        boolean isUpdated= iLoanService.updateLoanAccount(loansDto);
        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
     }

    @Operation(
            summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
     @DeleteMapping("/delete")
     public ResponseEntity<ResponseDto> deleteLoans(@RequestParam String mobileNumber){

         boolean isDeleted=iLoanService.deleteLoanAccount(mobileNumber);

         if(isDeleted){
             return ResponseEntity.status(HttpStatus.FOUND)
                     .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
         }else{
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body(new ResponseDto(LoansConstants.MESSAGE_417_DELETE, LoansConstants.MESSAGE_417_DELETE));
         }
     }
}
