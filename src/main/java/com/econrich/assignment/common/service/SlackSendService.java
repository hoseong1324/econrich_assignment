package com.econrich.assignment.common.service;

import com.econrich.assignment.common.response.CommonResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class SlackSendService {

    @Value("${slack.channels.id}")
    private String channelId;
    @Value("${slack.bot.token}")
    private String botToken;



    @Transactional
    public void sendSlackMessage(HttpServletRequest request, CommonResult result){

        WebClient webClient = WebClient.builder()
                .baseUrl("https://slack.com/api/chat.postMessage")
                .defaultHeader("Authorization", "Bearer " + botToken)
                .build();
        StringBuilder message = new StringBuilder();
        message.append("===== Slack Start Message =====").append(System.lineSeparator()).append(System.lineSeparator());
        message.append("DispatcherType : ").append(request.getDispatcherType()).append(System.lineSeparator());
        message.append("URL : ").append(request.getRequestURL()).append(System.lineSeparator());
        message.append("status : ").append(result.getStatus()).append(System.lineSeparator());
        message.append("code : ").append(result.getCode()).append(System.lineSeparator());
        message.append("message : ").append(result.getMessage()).append(System.lineSeparator()).append(System.lineSeparator());
        message.append("===== Slack End Message =====").append(System.lineSeparator()).append(System.lineSeparator());
        try {
            log.info(webClient.post()
                    .uri(uriBuilder ->
                            uriBuilder.queryParam("channel", channelId)
                                    .queryParam("text", message)
                                    .build()
                    )
                    .retrieve()
                    .bodyToMono(String.class)
                    .block());
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
