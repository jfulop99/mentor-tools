package com.training360.mentortools.registration;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class RecordAlreadyExistsException extends AbstractThrowableProblem {
    public RecordAlreadyExistsException(URI uri, String message) {
        super(uri, "Error", Status.BAD_REQUEST, message);
    }
}
