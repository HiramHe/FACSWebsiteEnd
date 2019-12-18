package FACSWebsiteEnd.service.impl;

import FACSWebsiteEnd.Entity.FacsOutTsv;
import FACSWebsiteEnd.Entity.FileInfo;
import FACSWebsiteEnd.common.Constant;
import FACSWebsiteEnd.service.FacsService;
import FACSWebsiteEnd.service.FileService;
import FACSWebsiteEnd.utils.CommandUtils;
import FACSWebsiteEnd.utils.CommonUtils;
import FACSWebsiteEnd.utils.FacsUtils;
import FACSWebsiteEnd.utils.RemoteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2019/12/2 16:33
 * QQ:776748935
 */

@Service
public class FacsServiceImpl implements FacsService {

    @Autowired
    private FileService fileService;

    @Value("${facsHome}")
    private String facsHome;
    @Value("${enableRemote}")
    private Boolean enableRemote;
    @Value("${remote_server_ip}")
    private String ip;
    @Value("${remote_server_port}")
    private Integer port;
    @Value("${remote_server_username}")
    private String username;
    @Value("${remote_server_password}")
    private String password;

    @Override
    public String callShellScript(FileInfo fileInfo, String currentOutDir, String dataType) {

        String command = "";

        Map<String,Object> commandParams = new HashMap<String, Object>();
        String bash = Constant.BASH;
        String shellPath = facsHome + Constant.FACS_SHELL;

        String tempFolderName = Constant.FACS_TEMP_FOLDER_PREFIX + fileInfo.getFilenameWithOutExtension();

        String inputFilePath = fileInfo.getPath();
        String outputFilePath = "";

        if (Constant.PEPTIDES.equals(dataType)){
            // run FACS on peptides

            commandParams.put("--mode","p");
            this.commonParams(commandParams,inputFilePath,currentOutDir,tempFolderName);

            command = CommandUtils.buildShellCommand(bash,shellPath,commandParams);
//            System.out.println(command);

            this.execute(command);

            outputFilePath = currentOutDir + Constant.FACS_OUT_FILENAME;

        } else if(Constant.CONTIGS.equals(dataType)){
            // run FACS on contigs

            commandParams.put("--mode","c");
            this.commonParams(commandParams,inputFilePath,currentOutDir,tempFolderName);

            command = CommandUtils.buildShellCommand(bash,shellPath,commandParams);
//            System.out.println(command);

            this.execute(command);
            outputFilePath = currentOutDir + Constant.FACS_OUT_FILENAME;

        }

        return outputFilePath;
    }

    private void commonParams(Map<String,Object> commandParams,String inputFilePath,String currentOutDir, String tempFolderName){
        commandParams.put("--fasta",inputFilePath);
        commandParams.put("-t",1);
        commandParams.put("--block",100000);
        commandParams.put("--outfolder",currentOutDir);
        commandParams.put("--tmp",tempFolderName);
    }

    private void execute(String command){
        if (!enableRemote){
            // 本地执行
            CommandUtils.executeLocalCommand(command);
        } else {
            // todo：远程执行
            RemoteUtils.remoteInvokeShell(ip,port,username,password,command);
        }
    }

    @Override
    public List<Object> readLocalResults(String filePath) {

        // 读取tsv结果文件
        List<Object> objects = fileService.readLocalTsvGzToObject(filePath, new FacsOutTsv());
        return objects;
    }

    @Override
    public List<Object> readRemoteResults(String outfolderPath, String filename) {
        // todo
        return null;
    }
}
