package io.swagger.api;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T19:26:09.389Z[GMT]")
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

    public ResponseEntity<List<Transaction>> getTransactions(@ApiParam(value = "get all transaction") @Valid @RequestParam(value = "iban", required = false) String iban
,@ApiParam(value = "show transaction depending from sender") @Valid @RequestParam(value = "Sender", required = false) String sender
,@ApiParam(value = "show transaction depending to recipient") @Valid @RequestParam(value = "Recipient", required = false) String recipient
,@ApiParam(value = "show transaction based on date") @Valid @RequestParam(value = "date", required = false) String date
,@ApiParam(value = "show transaction based on max amount") @Valid @RequestParam(value = "max-amount", required = false) String maxAmount
,@ApiParam(value = "show transaction based on min amount") @Valid @RequestParam(value = "min-amount", required = false) String minAmount
,@ApiParam(value = "show transaction based on the user performing") @Valid @RequestParam(value = "user-performing", required = false) String userPerforming
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Transaction>>(objectMapper.readValue("[ {\n  \"transactionType\" : \"transaction\",\n  \"transaction_id\" : 488558,\n  \"amount\" : 500.73,\n  \"userPerforming\" : 1,\n  \"sender\" : \"NL01INHO0000000001\",\n  \"recipient\" : \"NL53INHO0858545222\"\n}, {\n  \"transactionType\" : \"transaction\",\n  \"transaction_id\" : 488558,\n  \"amount\" : 500.73,\n  \"userPerforming\" : 1,\n  \"sender\" : \"NL01INHO0000000001\",\n  \"recipient\" : \"NL53INHO0858545222\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        //return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
        System.out.println(iban);
        List<Transaction> listT =  new ArrayList<Transaction>();
        listT.add(new Transaction("NL01INHO1",
                "NL02INHO2",
                500.73,
                Transaction.TransactionTypeEnum.TRANSFER,
                488558,
                1));
        listT.add(new Transaction("NL01INHO1",
                "NL02INHO2",
                500.73,
                Transaction.TransactionTypeEnum.TRANSFER,
                488558,
                2));

        ResponseEntity<List<Transaction>> x = new ResponseEntity<List<Transaction>>(listT,HttpStatus.OK);
        return x;
    }

    public ResponseEntity<Transaction> getTransactionsById(@ApiParam(value = "",required=true) @PathVariable("transactionId") Integer transactionId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Transaction>(objectMapper.readValue("{\n  \"transactionType\" : \"transaction\",\n  \"transaction_id\" : 488558,\n  \"amount\" : 500.73,\n  \"userPerforming\" : 1,\n  \"sender\" : \"NL01INHO0000000001\",\n  \"recipient\" : \"NL53INHO0858545222\"\n}", Transaction.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        //return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
        ResponseEntity<Transaction> x = new ResponseEntity<Transaction>(
                new Transaction("NL01INHO1",
                        "NL02INHO2",
                        500.73,
                        Transaction.TransactionTypeEnum.TRANSFER,
                        488558,
                        1),HttpStatus.OK);
        return x;
    }

    public ResponseEntity<Transaction> transfer(@ApiParam(value = ""  )  @Valid @RequestBody Transaction body
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Transaction>(objectMapper.readValue("{\n  \"transactionType\" : \"transaction\",\n  \"transaction_id\" : 488558,\n  \"amount\" : 500.73,\n  \"userPerforming\" : 1,\n  \"sender\" : \"NL01INHO0000000001\",\n  \"recipient\" : \"NL53INHO0858545222\"\n}", Transaction.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        System.out.println("print the body");
        System.out.println(body);
        return new ResponseEntity<Transaction>(HttpStatus.OK);
    }

}
