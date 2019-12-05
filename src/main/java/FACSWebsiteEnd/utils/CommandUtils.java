package FACSWebsiteEnd.utils;

import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2019/12/5 17:59
 * QQ:776748935
 */
public class CommandUtils {

    public static String buildShellCommand(Map<String,Object> commandParams){

        String bash = "bash";
        String space = " ";
        String command;

        command = bash;
        for (Map.Entry<String,Object> entry:commandParams.entrySet()){
            command += space + entry.getKey() + space + entry.getValue();
        }

        return command;
    }
}
