package com.reportes.reporte.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingManager {
    private Logger logger;

    public LoggingManager(Class c) {
        this.logger  = LogManager.getLogger(c);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void warn(String message) {logger.warn(message);}
}
