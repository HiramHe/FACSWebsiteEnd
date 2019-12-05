package FACSWebsiteEnd;

import FACSWebsiteEnd.utils.CommandUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2019/12/5 18:19
 * QQ:776748935
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandUtilsTest {

    @Test
    public void testBuildShellCommand(){

        String command = "";

        Map<String,Object> commandParams = new HashMap<String, Object>();
        commandParams.put("--fasta","fasta");
        commandParams.put("--mode","p");
        commandParams.put("-t",1);
        commandParams.put("--block","10MB");
        commandParams.put("--outfolder","outfolder");

        command = CommandUtils.buildShellCommand(commandParams);

        System.out.println(command);
    }
}
