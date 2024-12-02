package com.portfolio.server.portfolio.controller;

import com.portfolio.server.common.response.CommonResult;
import com.portfolio.server.common.response.ResponseService;
import com.portfolio.server.common.service.SlackSendService;
import com.portfolio.server.portfolio.dto.PortfolioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/portfolio")
public class PortfolioController {

    private final ResponseService responseService;
    private final SlackSendService slackSendService;

    @PostMapping("/message")
    public CommonResult message(@RequestBody PortfolioDto.MessageDto messageDto) {
        slackSendService.sendSlackMessageForPortfolio(messageDto);
        return responseService.getSuccessResult();
    }
}
