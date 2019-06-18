package com.adrrriannn.issue.manager.exception;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by adrian on 18/06/19.
 */
@Builder
@Getter
public class ErrorMessage {
    public Class exception;
    public String message;
    public int status;
    public long timestamp;
    public String url;

}
