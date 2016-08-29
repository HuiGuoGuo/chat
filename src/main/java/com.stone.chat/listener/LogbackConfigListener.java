package com.stone.chat.listener;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 */
public class LogbackConfigListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(LogbackConfigListener.class);

    private static final String CONFIG_LOCATION = "logbackConfigLocation";

    public void contextInitialized(ServletContextEvent event) {
        // 从web.xml中加载指定文件名的日志配置文件
        String logbackConfigLocation = event.getServletContext().getInitParameter(CONFIG_LOCATION);
        String fn = event.getServletContext().getRealPath(logbackConfigLocation);
        System.out.println(fn);
        try {
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            loggerContext.reset();
            JoranConfigurator joranConfigurator = new JoranConfigurator();
            joranConfigurator.setContext(loggerContext);
            joranConfigurator.doConfigure(fn);
            logger.debug("loaded slf4j configure file from {}", fn);
        } catch (JoranException e) {
            logger.error("can loading slf4j configure file from " + fn, e);
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
    }
}