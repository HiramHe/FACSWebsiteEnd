package FACSWebsiteEnd.controller;

import FACSWebsiteEnd.Entity.PredictionForm;
import FACSWebsiteEnd.Entity.FileInfo;
import FACSWebsiteEnd.common.Constant;
import FACSWebsiteEnd.common.ResultCode;
import FACSWebsiteEnd.common.ResultObject;
import FACSWebsiteEnd.service.FacsService;
import FACSWebsiteEnd.utils.EffectiveCheckUtils;
import FACSWebsiteEnd.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2019/11/29 11:09
 * QQ:776748935
 */

@RestController
@CrossOrigin
@RequestMapping("/facs")
public class FacsController {

    @Autowired
    private FacsService facsService;

    @PostMapping("/prediction")
    public ResultObject analysis(PredictionForm predictionForm){

        FileInfo fileInfo;

        // 校验上传的文本和文件
        if (!EffectiveCheckUtils.strEffectiveCheck(predictionForm.getTextData())
                && !EffectiveCheckUtils.fileEffectiveCheck(predictionForm.getFile())){
            return ResultObject.failure(ResultCode.DATA_IS_EMPTY);
        }

        // 上传的是文本
        if (EffectiveCheckUtils.strEffectiveCheck(predictionForm.getTextData())){
            fileInfo = facsService.saveSequenceToFile(predictionForm.getTextData());
        } else {
            // 上传的是文件
            MultipartFile file = predictionForm.getFile();
            Map fileInformation = FileUtils.getFileInformation(file);

            String type = fileInformation.get("type").toString();

            if (type != null){
                // 判断是否是指定类型的文件
                if (Constant.FASTTQ.equals(type)){
                    fileInfo = facsService.saveFile(file);
                }else {
                    return ResultObject.failure(ResultCode.FILETYPE_NOT_FASTQ_ERROR);
                }
            } else {
                return ResultObject.failure(ResultCode.FILETYPE_UNKNOWN_ERROR);
            }

        }

        // 校验数据类型是否为空
        if (!EffectiveCheckUtils.strEffectiveCheck(predictionForm.getDataType())){
            return ResultObject.failure(ResultCode.DATATYPE_UNSPECIFIED);
        }

        facsService.callShell(fileInfo,predictionForm.getDataType());

        return ResultObject.success(fileInfo);
    }

}
