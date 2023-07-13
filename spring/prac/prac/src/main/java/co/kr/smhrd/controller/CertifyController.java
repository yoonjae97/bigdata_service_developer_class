package co.kr.smhrd.controller;

import java.io.File;
import java.io.PrintStream;
import org.springframework.http.HttpStatus;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Block;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageContext;
import com.google.cloud.vision.v1.Page;
import com.google.cloud.vision.v1.Paragraph;
import com.google.cloud.vision.v1.Symbol;
import com.google.cloud.vision.v1.TextAnnotation;
import com.google.cloud.vision.v1.Word;
import com.google.protobuf.ByteString;
import org.springframework.http.ResponseEntity;
@Controller
@RequestMapping("/certify")
public class CertifyController {
	
	@GetMapping("/certifyFrm")
	public String certifyFrm() {
		return "certify/certifyFrm";
	}
	
	@PostMapping("imgCertify")
	@ResponseBody
	public ResponseEntity<String> imgCertify(HttpSession session, @RequestParam("image") MultipartFile image) {
		try {
	        String path = session.getServletContext().getRealPath("/photo");
	        
	        // MultipartFile을 파일로 저장
	        String filename = image.getOriginalFilename();
	        String imagePath = path + "/" + filename;
	        image.transferTo(new File(imagePath));
	        
	        // detectHandwrittenOcr 메서드 호출하여 결과 반환
	        String result = detectHandwrittenOcr(imagePath, System.out);
	        System.out.println(result+"hi");
	        return ResponseEntity.ok(result);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
    public static String detectHandwrittenOcr(String filePath, PrintStream out) throws Exception {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        byte[] imgBytes = Files.readAllBytes(Paths.get(filePath));

        ByteString imgData = ByteString.copyFrom(imgBytes);

        Image img = Image.newBuilder().setContent(imgData).build();
        Feature feat = Feature.newBuilder().setType(Type.DOCUMENT_TEXT_DETECTION).build();
        // Set the Language Hint codes for handwritten OCR
        ImageContext imageContext = ImageContext.newBuilder().addLanguageHints("en-t-i0-handwrit").build();

        AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img)
                .setImageContext(imageContext).build();
        requests.add(request);

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    out.printf("Error: %s\n", res.getError().getMessage());
                    return "none1";
                }

                // For full list of available annotations, see http://g.co/cloud/vision/docs
                TextAnnotation annotation = res.getFullTextAnnotation();
                for (Page page : annotation.getPagesList()) {
                    String pageText = "";
                    for (Block block : page.getBlocksList()) {
                        String blockText = "";
                        for (Paragraph para : block.getParagraphsList()) {
                            String paraText = "";
                            for (Word word : para.getWordsList()) {
                                String wordText = "";
                                for (Symbol symbol : word.getSymbolsList()) {
                                    wordText = wordText + symbol.getText();
                                    out.format("Symbol text: %s (confidence: %f)\n", symbol.getText(),
                                            symbol.getConfidence());
                                }
                                out.format("Word text: %s (confidence: %f)\n\n", wordText, word.getConfidence());
                                paraText = String.format("%s %s", paraText, wordText);
                            }
                            // Output Example using Paragraph:
                            out.println("\nParagraph: \n" + paraText);
                            out.format("Paragraph Confidence: %f\n", para.getConfidence());
                            blockText = blockText + paraText;
                        }
                        pageText = pageText + blockText;
                    }
                }
                out.println("\nComplete annotation:");
                out.println(annotation.getText());
                String result = annotation.getText();
                return result;
            }
        }
		return "none2";
    }
}
