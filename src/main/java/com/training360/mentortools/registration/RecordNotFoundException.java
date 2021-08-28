package com.training360.mentortools.registration;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class RecordNotFoundException extends AbstractThrowableProblem {
    public RecordNotFoundException(URI uri, String message) {
        super(uri, "Error", Status.NOT_FOUND, message);
    }
}
