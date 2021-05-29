package com.zavrsnirad.CodeFlow.util;

import com.zavrsnirad.CodeFlow.domain.TestCase;
import com.zavrsnirad.CodeFlow.dto.req.SolutionDtoReq;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@PropertySource("classpath:application.properties")
@Service
public class WebClientJugde0Util {

    private WebClient webClient;

    public WebClientJugde0Util(WebClient.Builder webClientBuilder, @Value("${judge0.url}") String url, @Value("${judge0.key}") String key, @Value("${judge0.host}") String host){
        this.webClient = webClientBuilder.baseUrl(url)
                .defaultHeader("x-rapidapi-host", host)
                .defaultHeader("x-rapidapi-key", key)
                .defaultHeader("content-type", "application/json")
                .defaultHeader("useQueryString", "true")
                .build();
    }

    public boolean validSolution(List<TestCase> testCases, SolutionDtoReq solutionDtoReq, Long judgeId) {
        if(testCases == null || testCases.isEmpty()) throw new IllegalArgumentException("Test cases can not be empty!");
        try {
            for(TestCase testCase : testCases) {
                Response response = webClient.post()
                        .uri("/submissions/?wait=true&base64_encoded=true")
                        .accept(MediaType.APPLICATION_JSON)
                        .body(BodyInserters
                                .fromFormData("source_code", base64(solutionDtoReq.getCode(), true))
                                .with("language_id", judgeId.toString())
                                .with("stdin", base64(testCase.getInput(), true))
                                .with("expected_output", base64(testCase.getOutput(), true))
                        ).retrieve().bodyToMono(Response.class).block();
                if(response.getStatus().get("description").equals("Wrong Answer")) return false;
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private static String base64(String input, boolean mode){
        if(mode) {
            return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
        }
        return new String(Base64.getDecoder().decode(input.getBytes()));
    }

    private static class Response{
        private String stdout;
        private String time;
        private Integer memory;
        private String stderr;
        private String token;
        private String compile_output;
        private String message;
        private Map<String, String> status;

        public Response(String stdout, String time, Integer memory, String stderr, String token, String compile_output, String message, Map<String, String> status) {
            this.stdout = stdout;
            this.time = time;
            this.memory = memory;
            this.stderr = stderr;
            this.token = token;
            this.compile_output = compile_output;
            this.message = message;
            this.status = status;
        }

        public String getStdout() {
            return stdout;
        }

        public void setStdout(String stdout) {
            this.stdout = stdout;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Integer getMemory() {
            return memory;
        }

        public void setMemory(Integer memory) {
            this.memory = memory;
        }

        public String getStderr() {
            return stderr;
        }

        public void setStderr(String stderr) {
            this.stderr = stderr;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCompile_output() {
            return compile_output;
        }

        public void setCompile_output(String compile_output) {
            this.compile_output = compile_output;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Map<String, String> getStatus() {
            return status;
        }

        public void setStatus(Map<String, String> status) {
            this.status = status;
        }
    }

}
