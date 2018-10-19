package spothero.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty("response")
    private final String response;

    public Response(String response) {

        this.response = response;
    }
}
