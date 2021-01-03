package fileupload.fileuploadspring.controller;

import fileupload.fileuploadspring.domain.Files;
import fileupload.fileuploadspring.service.FilesService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class MungController {

    @Autowired
    FilesService filesService;

    @RequestMapping("mung/insert")
    public String Insert() {

        return "mung/insert";
    }

    @RequestMapping("mung/fileinsert")
    public String fileinsert(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception {
        Files file = new Files();

        String sourceFileName = files.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
        File destinationFile;
        String destinationFileName;
        String fileUrl = "D:/3차 프로젝트/fileupload-spring/src/main/resources/static/images/";

        do {
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);
        } while (destinationFile.exists());

        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);

        file.setFilename(destinationFileName);
        file.setFileOriName(sourceFileName);
        file.setFileurl(fileUrl);
        filesService.save(file);
        return "redirect:/mung/insert";
    }

    @RequestMapping("mung/index5")
    public String index5(Model model) {
        model.addAttribute("file", filesService.filesRepository.findByFno(1004));
        return "mung/index5";
    }
}
