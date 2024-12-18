package com.example.employee.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    public class LoggingController {

        private static final Logger logger = LogManager.getLogger(LoggingController.class);

        @GetMapping("/log")
        public String logExample() {
            logger.info("Info level log example");
            logger.debug("Debug level log example");
            logger.error("Error level log example", new Exception("Example exception"));
            return "Logging has been demonstrated. Check the console and the log file!";
        }
    }

