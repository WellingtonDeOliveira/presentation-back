package br.com.tv.controllers.files.v1;

import br.com.base.shared.annotations.web.ApiController;
import br.com.base.shared.annotations.web.OpenApiController;
import br.com.tv.controllers.files.v1.annotations.GetFilesEndpoint;
import br.com.tv.controllers.files.v1.models.DTOs.FileRequestDTO;
import br.com.tv.controllers.files.v1.annotations.CreateFileEndpoint;
import br.com.tv.controllers.files.v1.annotations.DeleteFileEndpoint;
import br.com.tv.controllers.files.v1.models.DTOs.GetFileRequestDTO;
import br.com.tv.controllers.files.v1.models.DTOs.GetFileResponseDTO;
import br.com.tv.domain.services.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@OpenApiController(name = "Files")
@AllArgsConstructor
@ApiController(path = "/v1/files")
public class FileController {
    private final FileService fileService;

    @GetFilesEndpoint
    public ResponseEntity<GetFileResponseDTO> search(@ModelAttribute GetFileRequestDTO request) {
        var response = fileService.search(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CreateFileEndpoint
    public ResponseEntity<Void> uploadToDirectory(@ModelAttribute FileRequestDTO request) {
        fileService.uploadToDirectory(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteFileEndpoint
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        fileService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
