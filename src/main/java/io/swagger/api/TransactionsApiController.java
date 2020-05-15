package io.swagger.api;

import io.swagger.model.ExtendedTransaction;
import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-15T11:53:10.455Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<ExtendedTransaction>> getTransactions(@ApiParam(value = "The number of items to skip before starting to collect the result set") @Valid @RequestParam(value = "offset", required = false) Integer offset
,@ApiParam(value = "The numbers of items to return") @Valid @RequestParam(value = "limit", required = false) Integer limit
,@ApiParam(value = "get all transaction") @Valid @RequestParam(value = "iban", required = false) String iban
,@ApiParam(value = "show transaction depending from account") @Valid @RequestParam(value = "From Account", required = false) String fromAccount
,@ApiParam(value = "show transaction depending to account") @Valid @RequestParam(value = "To Account", required = false) String toAccount
,@ApiParam(value = "show transaction based on date") @Valid @RequestParam(value = "date", required = false) String date
,@ApiParam(value = "show transaction based on max amount") @Valid @RequestParam(value = "max-amount", required = false) String maxAmount
,@ApiParam(value = "show transaction based on min amount") @Valid @RequestParam(value = "min-amount", required = false) String minAmount
,@ApiParam(value = "show transaction based on the user performing") @Valid @RequestParam(value = "user-performing", required = false) String userPerforming
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<ExtendedTransaction>>(objectMapper.readValue("[ {\n  \"userPerforming\" : 1,\n  \"id\" : 488558,\n  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"\n}, {\n  \"userPerforming\" : 1,\n  \"id\" : 488558,\n  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<ExtendedTransaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<ExtendedTransaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ExtendedTransaction> getTransactionsbyId(@ApiParam(value = "User id to get from the database",required=true) @PathVariable("transactionId") Integer transactionId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ExtendedTransaction>(objectMapper.readValue("{\n  \"userPerforming\" : 1,\n  \"id\" : 488558,\n  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"\n}", ExtendedTransaction.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ExtendedTransaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ExtendedTransaction>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Transaction> transferMoney(@ApiParam(value = ""  )  @Valid @RequestBody Transaction body
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Transaction>(objectMapper.readValue("{\n  \"transactionType\" : \"transaction\",\n  \"amount\" : 500.73,\n  \"sender\" : \"NL01INHO0000000001\",\n  \"recipient\" : \"NL53INHO0858545222\"\n}", Transaction.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
    }

}
