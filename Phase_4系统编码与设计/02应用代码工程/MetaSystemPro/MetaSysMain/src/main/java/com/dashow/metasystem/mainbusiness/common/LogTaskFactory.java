package com.dashow.metasystem.main.common;

import com.dashow.metasystem.main.connector.DBLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

/**
 * MetaSystemPro
 *
 * @author: Create LogTaskFactory  by Fuqifeng on 2021 2021/1/23;
 * Function:
 */
public class LogTaskFactory {
    private static Logger logger = LoggerFactory.getLogger(LogTaskFactory.class);

    private static final ThreadLocal<Stack<String>> keyWord = new ThreadLocal<>();

    public static void setKeyWord(String key) {
        if (null == keyWord.get()) {
            Stack stack = new Stack();
            stack.push(key);
            keyWord.set(stack);
        }
        keyWord.get().push(key);
    }

    public static String getKeyWord() {
        if(keyWord.get()!=null&&!keyWord.get().isEmpty()){
            return keyWord.get().pop();
        }
        return null;
    }

//    public static TimerTask writeOrderLog(final DBLogger opt) {
//        return new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    DBLoggerSender.sendLogger(opt);
//                } catch (Exception e) {
//                    logger.error("调用日志平台异常!", e);
//                }
//            }
//        };
//    }
}
