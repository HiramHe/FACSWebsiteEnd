package FACSWebsiteEnd.service.impl;

import FACSWebsiteEnd.Entity.FileInfo;
import FACSWebsiteEnd.common.Constant;
import FACSWebsiteEnd.service.FacsService;
import FACSWebsiteEnd.service.FileService;
import FACSWebsiteEnd.utils.CommandUtils;
import FACSWebsiteEnd.utils.RemoteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2019/12/2 16:33
 * QQ:776748935
 */

@Service
public class FacsServiceImpl implements FacsService {

    @Autowired
    FileService fileService;

    @Override
    public FileInfo saveSequenceToFile(String sequence) {

        // 将序列文本输出为指定文件
        String extension = Constant.FASTTQ;
        FileInfo fileInfo = fileService.saveTextToFile(sequence, extension);
        return fileInfo;

    }

    @Override
    public FileInfo saveFile(MultipartFile multipartFile) {
        FileInfo fileInfo = fileService.upload(multipartFile);
        return fileInfo;
    }

    @Override
    public Boolean callShell(FileInfo fileInfo, String dataType) {

        String command = "";

        Map<String,Object> commandParams = new HashMap<String, Object>();


        if (Constant.PEPTIDES.equals(dataType)){
            commandParams.put("--fasta",fileInfo.getFullpath());
            commandParams.put("--mode","p");
            commandParams.put("-t",1);
            commandParams.put("--block","10MB");
            commandParams.put("--outfolder","outfolder");

            command = CommandUtils.buildShellCommand(commandParams);
        }

        String ip = "39.106.68.204";
        int port = 22;
        String username = "HiramHe";
        String password = "hiram1024";

        RemoteUtils.remoteInvokeShell(ip,port,username,password,command);

        return null;
    }
}
