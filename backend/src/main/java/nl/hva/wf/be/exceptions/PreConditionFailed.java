package nl.hva.wf.be.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class PreConditionFailed extends RuntimeException{
    public PreConditionFailed(String s){super(s);}
}
